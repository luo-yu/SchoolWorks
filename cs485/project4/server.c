#include "csapp.h"
#define totalUser 5
#define totalCmd 5
#define BUFFSIZE 4096
#define offLine -1   //user offline

#define cmdMode 2  //user in cmd mode
void initialsetup();
void getAccount();
void getCmd();
int isLegalCmd(char *msg);
int handleRequest(int socketId, int acceptId, char* msg);
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
    char buf[BUFFSIZE];
    char usrNameBuf[40];
    char usrPassBuf[40];
    char cmdBuf[MAXLINE];
    char msg[128];
    char* usrName;
    char* usrPass; 
    rio_t rio;
    pid_t pid;





    int fd;
    if (argc != 2) {
	fprintf(stderr, "usage: %s <port>\n", argv[0]);
	exit(0);
    }
   
    port = atoi(argv[1]);

    listenfd = Open_listenfd(port);
 

    //get the accounts and the commands 
    initialsetup();
    


    clientlen = sizeof(clientaddr);
    connfd = Accept(listenfd, (SA *)&clientaddr, &clientlen);
         /* Determine the domain name and IP address of the client */
     hp = Gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr,
                           sizeof(clientaddr.sin_addr.s_addr), AF_INET);
    haddrp = inet_ntoa(clientaddr.sin_addr);
    printf("server connected to %s (%s)\n", hp->h_name, haddrp);

 Rio_readinitb(&rio, connfd);
 
  int a =  Rio_readlineb(&rio, usrNameBuf, MAXLINE);
  printf("read %i bytes\n", a);
//   Rio_readlineb(&rio, usrPassBuf, strlen(usrPassBuf));
 printf("username = %s", usrNameBuf);

 int b = Rio_readlineb(&rio, usrPassBuf, MAXLINE);
 printf ("Read %i bytes\n", b);
 printf("Pass = %s", usrPassBuf);

 int aa = isLegalUsr(usrNameBuf, usrPassBuf);
  if(aa){
	Rio_writen(connfd, "Login Approved\n", strlen("Login Approved\n"));
	printf("Login Approved\n");
        while(Rio_readlineb(&rio, msg, MAXLINE)){
	
//		Rio_readlineb(&rio, msg, MAXLINE);
//		int bb = isLegalCmd(msg);
//		if(bb){
		

//			printf("legal cmd\n");
			if((pid=fork())<0){
				printf("Fork error\n");
		
			}
			else{
				if(pid>0){
					

				//	bzero(cmdBuf, BUFFSIZE);
					while(Rio_readlineb(&rio, cmdBuf, BUFFSIZE)){
						cmdBuf[strlen(cmdBuf)]='\0';
						Rio_writen(connfd, cmdBuf, strlen(cmdBuf));
					}
					
					pid_t pid;
					int stat;
					pid = waitpid(-1, &stat, 0);
					

				
					printf("pid_t: %i,  stat: %i\n", pid, stat);
					printf("I am father %d/n", getpid());
				}
				else{
					
					if(dup2(connfd, fileno(stdout))<0){
						printf("Error for dup2\n");
					}
					close(connfd);

					execl("/bin/bash", "bash", "-c", msg, NULL);
					exit(0);
				}

			}	
//		}
//		else{
			printf("illegal cmd\n");
//		}
	
  	}//end while

   }
   else{
	printf("Login Failed\n");
	Rio_writen(connfd, "Login Failed\n", strlen("Login Failed\n"));
	close(connfd);
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
		strcat(legalUsers[i].name, "\n");
		strcat(legalUsers[i].passwd, "\n");
		printf("Legal user: %s", legalUsers[i].name);
		printf("Legal pass: %s", legalUsers[i].passwd);
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
                program = strtok(cmdList[i], "\n"  );
                strcpy(legalCmds[i].cmdname, program);
		strcat(legalCmds[i].cmdname, "\n");
		printf("Legal command: %s", legalCmds[i].cmdname);
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
		printf("Legal User name = %s   userName = %s", legalUsers[i].name, usrName);
		printf("Legal user pass = %s   userPass = %s", legalUsers[i].passwd, usrPass);
		if(!strcmp(legalUsers[i].name,usrName) && !strcmp(legalUsers[i].passwd,usrPass))
			return 1;
		
	}//end for 

	return 0;

}//end isLegalUsr




int handleRequest(int socketId, int acceptId, char* msg){

	printf ( "%s from %i\n", msg, acceptId );

}//end handleRequest













