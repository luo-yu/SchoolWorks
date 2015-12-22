#include "csapp.h"
#define totalUser 5
#define totalCmd 5
#define BUFFSIZE 4096
#define offLine -1   //user offline

#define cmdMode 2  //user in cmd mode
#define IsSpecialChar(x) ((x == '<') || (x == '>') || (x == '%') || (x == '&'))
#define IsWhiteSpace(x) ((x == ' ') || (x == '\t'))

#define HandleError(x) { printf(x); argv[0] = NULL; return 1; }  
#include <string.h>
extern char **environ;

char buf[MAXLINE]; 

int p3parseline(char *line, char **argv);
void initialsetup();
void getAccount();
void getCmd();
int isLegalCmd(char *msg);
int handleRequest(int connfd, int port, char* msg);
struct nameAndPasswd {
                char name[BUFFSIZE];
                char passwd[BUFFSIZE];
};                              /* ----------  end of struct nameAndPasswd  ---------- */

struct commands{

		char cmdname[BUFFSIZE];
};



typedef struct nameAndPasswd NameAndPasswd;
typedef struct commands Commands;

struct user {
                char name[BUFFSIZE];
                char passwd[BUFFSIZE];
                int socket;
                int stage;
                int serverCache;
};                              /* ----------  end of struct user  ---------- */

typedef struct user User;

User allUsers[totalUser];
NameAndPasswd legalUsers[totalUser];
Commands legalCmds[totalCmd];
int userCount = 0;
int cmdCount = 0;





int main(int argc, char **argv) 
{
    int listenfd, connfd, port;
    socklen_t clientlen;
    struct sockaddr_in clientaddr;
    struct hostent *hp;
    char *haddrp;
    if (argc != 2) {
	fprintf(stderr, "usage: %s <port>\n", argv[0]);
	exit(0);
    }
    port = atoi(argv[1]);


    initialsetup();

    listenfd = Open_listenfd(port);
    while (1) {
	clientlen = sizeof(clientaddr);
	connfd = Accept(listenfd, (SA *)&clientaddr, &clientlen);

	/* Determine the domain name and IP address of the client */
	hp = Gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr, 
			   sizeof(clientaddr.sin_addr.s_addr), AF_INET);
	haddrp = inet_ntoa(clientaddr.sin_addr);
	printf("server connected to %s (%s)\n", hp->h_name, haddrp);

	handleRequest(connfd, port, haddrp);
	Close(connfd);
    }
    exit(0);
}







void initialsetup(){

	int i = 0;
	printf ( "The initial step\n" );
	getCmd();
	getAccount();
	printf("Information gathered\n");
	for ( i = 0; i<totalUser ; i++ ) {
		allUsers[i].stage = offLine;
		allUsers[i].socket = -1;
		allUsers[i].serverCache = i ;
	}
}//end intialsetup



void getAccount ( )
{
	int fp, i;
	char account[BUFFSIZE];
	char *userList[BUFFSIZE], *userName, *userPasswd;

	fp = open("rrshusers.txt", O_RDWR);	
	read(fp, account, BUFFSIZE);
	userList[userCount] = strtok(account, "\n"); 
	while ( NULL != userList[userCount] ) {
		userCount++;
		userList[userCount] = strtok(NULL, "\n");
	}

	for ( i=0 ; i < userCount ; i++ ) {
		userName = strtok( userList[i], " "  );
		userPasswd = strtok( NULL, " " );
		strcpy(legalUsers[i].name, userName);
		strcpy(legalUsers[i].passwd, userPasswd);
//		strcat(legalUsers[i].name, "\n");
//		strcat(legalUsers[i].passwd, "\n");
	}

	close(fp);
}		/* -----  end of function getAccount  ----- */


void getCmd ( )
{
      	int fp, i;
        char cmds[BUFFSIZE];
        char *cmdList[BUFFSIZE], *program;

        fp = open("rrshcommands.txt", O_RDWR);
        read(fp, cmds, BUFFSIZE);
        cmdList[cmdCount] = strtok(cmds, "\n");
        while ( NULL != cmdList[cmdCount] ) {
                cmdCount++;
                cmdList[cmdCount] = strtok(NULL, "\n");
        }

        for ( i=0 ; i < cmdCount ; i++ ) {
                program = strtok(cmdList[i], " "  );
        	strtok(NULL, " ");
 	        strcpy(legalCmds[i].cmdname, program);
		printf("legalcmds %s", legalCmds[i].cmdname);
         }
        
        close(fp);

}//end getCmd;


int isLegalCmd(char* msg){

	int i;

	for(i =0; i<totalCmd; i++){

		if(!strcmp(legalCmds[i].cmdname, msg))
			return 1;
	}

	return 0;
}//end isLegalCmd

int isLegalUsr(char* usrName, char* usrPass){

	int i;


	for(i =0; i<totalUser; i++){
		if(!strcmp(legalUsers[i].name,usrName) && !strcmp(legalUsers[i].passwd,usrPass))
			return 1;
		
	}//end for 

	return 0;

}//end isLegalUsr




int handleRequest(int connfd, int port, char* haddrp){

	//read user name and pass
    char usrNameBuf[40];
    char usrPassBuf[40];
    char recvLine[MAXLINE];

    rio_t rio;

    pid_t pid;
    int status;
    char *argv[128];

    Rio_readinitb(&rio, connfd);

    //read user name
    int a =  Rio_readlineb(&rio, usrNameBuf, MAXLINE);
    

    //read password
    int b = Rio_readlineb(&rio, usrPassBuf, MAXLINE);

    usrNameBuf[strlen(usrNameBuf)-1]='\0';
    usrPassBuf[strlen(usrPassBuf)-1]='\0';
   
    printf("User %s logging in from %s at TCP port %u.\n", usrNameBuf, haddrp, port);

 

    if(isLegalUsr(usrNameBuf, usrPassBuf)){
	 printf("User %s successfully logged in.\n", usrNameBuf);

	//server respond to client
	Rio_writen(connfd, "Login Approved\n", strlen("Login Approved\n"));

	while (Rio_readlineb(&rio, recvLine, MAXLINE) > 0){
		p3parseline(recvLine, argv);
		recvLine[strlen(recvLine)-1]='\0';
		if(isLegalCmd(argv[0])){
			if((pid=fork())<0){
				printf("Fork error\n");
		
			}
			else{
				if(pid>0){
					
					//parent
					
					// Wait for the child process to complete; Output finished command
			            	waitpid(pid, &status, 0);
           				Rio_writen(connfd, "RRSH COMMAND COMPLETED\n", strlen("RRSH COMMAND COMPLETED\n"));  		

				}
				else{
					//child
					 printf("Forking/Execing the command ’%s’ on behalf of %s.\n", recvLine, usrNameBuf);	
					if(dup2(connfd, 1)<0){
						printf("Error for dup2\n");
					}
					close(connfd);

					if(execve(argv[0], argv, environ)<0){
						exit(0);
					}

				//	execl("/bin/bash", "bash", "-c", recvLine, NULL);
				//	exit(0);
				}


			}	

		}

		else{
		    printf("The command '%s' is not allowed.\n", recvLine);
                    close(connfd);
                    printf("Cannot execute ’%s’ on this server\n", recvLine);
                    exit(0); 
		}

		printf("User %s disconnected.\n", usrNameBuf);
	}
	
    }
    else{

	//if loggin failed
	Rio_writen(connfd, "Login Failed\n", strlen("Login Failed\n"));
	close(connfd);
    }



  


}//end handleRequest


/*--------------------------------------------------------------------------------------*/
/* p3parseline - Parse the command line and build the argv array for CS 485 project 3   */
/*               CS 485 project 3 support strings, and special characters < > % and & */
/*--------------------------------------------------------------------------------------*/
int p3parseline(char *line, char **argv) 
{
  char *bptr;        /* Pointer into the buffer buf */
  char *tptr;        /* Temp pointer */
  int intoken;       /* Are we in the middle of reading a token in? */
  int argc;          /* Number of args */
  int bg;            /* Background job? */

  if ((strlen(line)*2) > MAXLINE) {
    HandleError("Line too long for p3parseline\n");
  }

  argc = 0;
  intoken = 0;
  
  /* Copy line into buf character-by-character to create the argv strings */
  bptr = buf;
  tptr = line;
  while (*tptr != '\n') {
    
    if (IsWhiteSpace(*tptr)) {
      *bptr++ = '\0';                  /* insert \0 to terminate current token */
      intoken = 0;
    }
    else if (IsSpecialChar(*tptr)) {   /* stand-alone token -- add room for \0 */
      if (intoken) {  
	*bptr++ = '\0';
	intoken = 0;
      }
      argv[argc++] = bptr;
      *bptr++ = *tptr;
      *bptr++ = '\0';
    }
    else if (*tptr == '"') {            /* starting a string -- copy until matching \" */
      if (intoken) {  
	*bptr++ = '\0';
	intoken = 0;
      }
      argv[argc++] = bptr;
      tptr++;
      while ((*tptr != '\"') && (*tptr != '\0')) *bptr++ = *tptr++;
      if (*tptr == '\0') {
	HandleError("String not terminated -- missing double quote\n");
      }
      else {
        *bptr++ = '\0';
      }
    }
    else {                             /* non-special character */
      if (intoken) *bptr++ = *tptr;
      else {
	intoken = 1;
	argv[argc++] = bptr;
	*bptr++ = *tptr;
      }
    }
    
    tptr++;                             /* move to the next character */
    
  }
  *bptr = '\0';                         /* terminate the last token */

  argv[argc] = NULL;

  if (argc == 0)  /* Ignore blank line */
    return 1;

  /* Should the job run in the background? */
  if ((bg = (*argv[argc-1] == '&')) != 0)
    argv[--argc] = NULL;

#ifdef P3PARSELINE_DEBUG
  int i;
  for (i = 0; i < argc; i++) printf("argv[%d] = '%s'\n", i, argv[i]);
#endif
  

  return bg;
}











