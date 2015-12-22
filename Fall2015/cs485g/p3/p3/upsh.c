#include <stdio.h>
#include <dlfcn.h>
#include "csapp.h"


#define MAXARGS   128
#define MAXPATHNAME 100

int usr_cmd =0;

/* function declaration */
void eval(char *cmdline);
int parseline(char *buf, char **argv);
int p3parseline(char *buf, char **argv); /* new parseline function for cs485 project 3 */
int builtin_command(char **argv); 

void loadpluggin(char *argv);
void printBgjobs();
void signal_handler();


void redirect_file(char **argv);


//used by loadpluggin
void analyze_cmd(char* cmdl);





char *prompt ="> ";


int num_pid =0;

struct bgjob{
	char name[50];
	pid_t pid;
};

struct bgjob bgjobs[16];
void signal_handler();
void printBgjobs();


//used by loadpluggin
struct NewBuiltIn{
     char CommandName[64];   /* Name of the Built-in command users can type */
     char FunctionName[64];  /* Name of the function in the code */
     char AnalyzerName[64];  /* Name of an analyzer function to call on every command */
 };


//used by loadpluggin
struct NewBuiltIn *pluggin_method;

//used by loadpluggin
struct Pluggin
{
	char CommandName[64];
	int (*functionAddr)(char**);
};

//used by loadpluggin
char** (*analyzerP[15])(char *);

//used by loadpluggin
struct Pluggin pluggins[15];

//used by loadpluggin
int num_pluggin=0;



//used by loadpluggin
int num_analyzer = 0;


/* deletejob - delete a job from the job list */
int deletejob(struct bgjob *bgjobs, pid_t pid)
{
    int i;

    for (i = 0; i < 16; i++) {
        if (bgjobs[i].pid == pid) {
	    bgjobs->pid = 0;
   	    bgjobs->name[0] = '\0';
	    printf("job [%d] process %d  -Finished\n", i+1, pid); 
	    	 return 1;
        }
    }
    return 0;
}






int main() 
{
 

   char cmdline[MAXLINE]; /* Command line */
    char current_dir[MAXPATHNAME];

//    Signal(SIGCHLD,signal_handler);
    while (1) {
	getcwd(current_dir, MAXPATHNAME-1);
	printf("%s\n", current_dir);

	/* Read */
	printf("%s", prompt);                   
	fgets(cmdline, MAXLINE, stdin); 
	if (feof(stdin))
	    exit(0);

	/* Evaluate */
	eval(cmdline);
    } 
}
/* $end shellmain */
  
/* $begin eval */
/* eval - Evaluate a command line */
void eval(char *cmdline) 
{
   char *argv[MAXARGS]; /* Argument list execve() */
    char buf[MAXLINE];   /* Holds modified command line */
    int bg;              /* Should the job run in bg or fg? */
    pid_t pid;           /* Process id */
    
//    strcpy(buf,applyPluggins(cmdline));
    strcpy(buf, cmdline);
 

  //used by loadpluggin, executed before callsing p3parseline
   analyze_cmd(buf);



    /*    bg = parseline(buf, argv); */ 
    bg = p3parseline(buf, argv); /* call new parseline function for cs485 project 3 */
    if (argv[0] == NULL)  
	return;   /* Ignore empty lines */

/*Go through the pluggins, if found the pluggin name in our loaded pluggin,set usr_cmd to 
true which will be executed later by the shell */
        int i;
        for(i = 0; i<num_pluggin; i++){
                if(!strcmp(argv[0],pluggins[i].CommandName)){
                  (pluggins[i].functionAddr)(argv);
              	  usr_cmd =1;  
		}
        }


      
    if ((!builtin_command(argv))&&(!usr_cmd)) { 
	if ((pid = fork()) == 0) {   /* Child runs user job */
	    	redirect_file(argv);
		if (execve(argv[0], argv, environ) < 0) {
			printf("%s: Command not found.\n", argv[0]);
			exit(0);
	    	 }
	}

	/* Parent waits for foreground job to terminate */
	if (!bg) {
	    int status;
	    if (waitpid(pid, &status, 0) < 0)
		unix_error("waitfg: waitpid error");
	}
	/*If the command should be run in the background*/
	else{
            //handling terminated background process	
	    Signal(SIGCHLD,signal_handler);


	    printf("%d %s", pid, cmdline);
	    bgjobs[num_pid%16].pid=pid;
            strcpy(bgjobs[num_pid%16].name, cmdline);
            num_pid++;
	}
    }
    return;




}

/* If first arg is a builtin command, run it and return true */
int builtin_command(char **argv) 
{
    if (!strcmp(argv[0], "quit")) /* quit command */
	exit(0);  
    if (!strcmp(argv[0], "&"))    /* Ignore singleton & */
	return 1;
    if (!strcmp(argv[0], "%"))     /*Any text after the % will be ignored*/
	return 1;
    if (!strcmp(argv[0], "setprompt"))   /*Set the shell prompt to user defined promt*/
    {
	prompt = strcat(argv[1], "> ");
	return 1;
    }

    /*change the current directory to the directory provided by user at argv[1]
    does not hadle cd with no argments */
    if (!strcmp(argv[0], "cd"))
    {
	/*On success, zero is returned.*/
	int success = chdir(argv[1]);
	if(success==0)
		return 1;
    }


    /*load pluggin*/

   if (!strcmp(argv[0], "loadpluggin"))
   {
	void *handle;

	handle = dlopen(argv[1],RTLD_LAZY);

 	if(!handle){
                printf("dlopen error %s\n ", dlerror());
                return 1;
        }
	pluggin_method = dlsym(handle,"pluggin_method");

		
	strcpy(pluggins[num_pluggin].CommandName, pluggin_method->CommandName);

	pluggins[num_pluggin].functionAddr = dlsym(handle,pluggin_method->FunctionName);
	
	//if there's an analyzer in the pluggin
	if((strcmp(pluggin_method->AnalyzerName,"")) != 0)
	{
		analyzerP[num_analyzer] = dlsym(handle,pluggin_method->AnalyzerName);
		num_analyzer++;
	}	

	num_pluggin++;
	return 1;


   }//end loadpluggin
	

  /*Print the background jobs*/
  if(!strcmp(argv[0], "bgjobs")){
	int i, pids, bstatus;
        pids = (num_pid<16)?num_pid:16;

        for(i =num_pid-pids; i<num_pid; i++){

                if(waitpid(bgjobs[i].pid, &bstatus, WNOHANG)==0){
                        printf("[%d]    %d      %s\n", i+1, bgjobs[i].pid, bgjobs[i].name);
                }
        }
	return 1;
  }
  if(!strcmp(argv[0], "fg")){
	pid_t fpid;
	fpid = atoi(argv[1]);
	printf("The job id is %d\n", fpid);
	waitpid(bgjobs[fpid-1].pid, NULL, 0);
	return 1;
  }

  if(!strcmp(argv[0], "culater")||!strcmp(argv[0], "cntl-D")){
	printf("bye");
	exit(1);
  }

    return 0;                     /* Not a builtin command */
}





/* $end eval */

/* $begin parseline */
/* parseline - Parse the command line and build the argv array */
int parseline(char *buf, char **argv) 
{
    char *delim;         /* Points to first space delimiter */
    int argc;            /* Number of args */
    int bg;              /* Background job? */

    buf[strlen(buf)-1] = ' ';  /* Replace trailing '\n' with space */
    while (*buf && (*buf == ' ')) /* Ignore leading spaces */
	buf++;

    /* Build the argv list */
    argc = 0;
    while ((delim = strchr(buf, ' '))) {
	argv[argc++] = buf;
	*delim = '\0';
	buf = delim + 1;
	while (*buf && (*buf == ' ')) /* Ignore spaces */
	       buf++;
    }
    argv[argc] = NULL;
    
    if (argc == 0)  /* Ignore blank line */
	return 1;

    /* Should the job run in the background? */
    if ((bg = (*argv[argc-1] == '&')) != 0)
	argv[--argc] = NULL;

    return bg;
}



void signal_handler(){
    pid_t pid;
    int status;
  
    /* 
     * Reap any zombie jobs.
     */
    while ((pid = waitpid(-1, &status, WNOHANG)) > 0) { 
	deletejob(bgjobs, pid);
    }

    /* 
     * Check for normal loop termination. 
     */
    if (!((pid == 0) || (pid == -1 && errno == ECHILD)))
	unix_error("sigchld_handler wait error"); 
}




/*Redirection files*/
void redirect_file(char **argv){
//	int filed0;
	int filed1;
	int inFile=0;
	int outFile=0;


	char inputs[1024];
	char outputs[1024];


	int i =0;
	while(argv[i] !='\0'){

		if(strcmp(argv[i], "<") ==0){
			argv[i]=NULL;
			strcpy(inputs, argv[i+1]);
			inFile =1;
		}
		else if(strcmp(argv[i], ">")==0){
			argv[i]=NULL;
			strcpy(outputs, argv[i+1]);
			outFile =1;
		}

		i++;
	}

	if(inFile){
		FILE *myfile = NULL;
		char mystring[256];
		myfile = fopen(inputs,"r");
		if(myfile !=NULL)
		{
			while(fgets(mystring,sizeof(mystring),myfile))
			{
				eval(mystring);
			}
			fclose(myfile);
		}
			

		else
		{
		printf("File Read Error.\n");
		}	
	}


        if(outFile){
                if((filed1=creat(outputs,0644))<0){
                        exit(0);
                }
                dup2(filed1, STDOUT_FILENO);
                close(filed1);
        }

//	close(filed0);
        close(filed1);
}



//used by loadpluggin
void analyze_cmd(char* cmdl)
{
	int i;
	for(i=0; i<num_analyzer; i++){
		analyzerP[i](cmdl);
	}
}


