#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <signal.h>
#include <fcntl.h>
#include <time.h>

#define	BUFFSIZE 4096			/*  */
#define	usrCanHandle 13			/* total usrs 0, 1, 2, is stdIO */
#define cmdCanHandle 15
#define	inputAccount 0			/* this usr should send account */
#define	inputPasswd 1			/* this usr should send pass */
#define	cmdMode 2			/* this usr in commend mode */
#define	offLine -1			/*  this usr is off-line */



struct NamePass {
                char name[BUFFSIZE];
                char pass[BUFFSIZE];
};                              /* ----------  end of struct NamePass  ---------- */

struct Command{

		char cmdname[BUFFSIZE];
};



typedef struct NamePass NameAndPasswd;
typedef struct Command Commands;

struct usr {
                char name[BUFFSIZE];
                char pass[BUFFSIZE];
                int socket;
                int stage;
                int serverCache;
};                              /* ----------  end of struct usr  ---------- */

typedef struct usr Usr;


void initial();
int login( int connfd, const char *name );
int requestHandler( int listenfd, int connfd, char *recvMsg );
void getAccount();
void getCmd();





Usr allUsrs[usrCanHandle];
NameAndPasswd allowedUsrs[usrCanHandle];
Commands legalCmds[cmdCanHandle];
int howManyUsrs = 0;
int howManyCmds =0;

const char yourName[BUFFSIZE] = ">>Login:";
const char yourPasswd[BUFFSIZE] = ">>Password:";
const char youAreNotUsr[BUFFSIZE] = ">>There is no this account\n";
const char yourAreLogout[BUFFSIZE] = "Your Are Logout\n"; 
const char youAreOnline[BUFFSIZE] = "\n>>Your Are on-line, in cmd mode\n";
const char youHaveBeenOnline[BUFFSIZE] = "\nYou have been on-line\n";
const char cmdPrompt[BUFFSIZE] = ">>\0";

int isLegalCmd(char* msg);
/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  getAccount
 *  Description:  
 * =====================================================================================
 */
void getAccount ( )
{
		int fp, i;
		char account[BUFFSIZE];
		char *usrList[BUFFSIZE], *usrName, *usrPasswd;

		fp = open("account/rrshusrs.txt", O_RDWR);	
		read(fp, account, BUFFSIZE);
		usrList[howManyUsrs] = strtok(account, "\n"); 
		while ( NULL != usrList[howManyUsrs] ) {
				howManyUsrs++;
				usrList[howManyUsrs] = strtok(NULL, "\n");
		}

		for ( i=0 ; i < howManyUsrs ; i++ ) {
				usrName = strtok( usrList[i], " "  );
				usrPasswd = strtok( NULL, " " );
				strcpy(allowedUsrs[i].name, usrName);
				strcpy(allowedUsrs[i].pass, usrPasswd);
		}
		close(fp);
		return ;
}		/* -----  end of function getAccount  ----- */


void getCmd ( )
{
                int fp, i;
                char cmds[BUFFSIZE];
                char *cmdList[BUFFSIZE], *program;

                fp = open("rrshCommand.txt", O_RDWR);
                read(fp, cmds, BUFFSIZE);
                cmdList[howManyCmds] = strtok(cmds, "\n");
                while ( NULL != cmdList[howManyCmds] ) {
                                howManyCmds++;
                                cmdList[howManyCmds] = strtok(NULL, "\n");
                }

                for ( i=0 ; i < howManyCmds ; i++ ) {
                                program = strtok(cmdList[i], "\n"  );
                                strcpy(legalCmds[i].cmdname, program);
                }
                close(fp);
                return ;
}               /* -----  end of function getAccount  ----- */



int isLegalCmd(char* msg){

	int i;

	for(i =0; i<howManyCmds; i++){

		if(!strcmp(legalCmds[i].cmdname, msg))
			return 1;
	}

	return -1;


}

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  initial
 *  Description:  
 * =====================================================================================
 */
		void
initial ( )
{
		int i = 0;
		printf ( "The initial step\n" );
		getCmd();
		getAccount();
		for ( i = 0; i<usrCanHandle ; i++ ) {
				allUsrs[i].stage = offLine;
				allUsrs[i].socket = -1;
				allUsrs[i].serverCache = i ;
		}



		return ;
}		/* -----  end of function initial  ----- */

/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  requestHandler
 *  Description:  
 * =====================================================================================
 */
		int
requestHandler( int socketId, int connfd, char *recvMsg)
{
		char name[BUFFSIZE], pass[BUFFSIZE], cmdBuf[BUFFSIZE];
		char cmd[BUFFSIZE], usrSay[BUFFSIZE];
		int i = 0, j = 0;
		pid_t pid;
		int fd[2];
		printf ( "%s from %d\n", recvMsg, connfd );
		if ( offLine == allUsrs[connfd].stage ) { /* request client to input account */
				write(connfd, yourName, strlen(yourName));
				printf("requested usr name\n");
				allUsrs[connfd].stage = inputAccount;
				return 1;
		}
		if ( inputAccount == allUsrs[connfd].stage ) { /* it get account, now client should send pass */
			printf ( "Now the recvMsg is account\n" );
				strcpy(name, recvMsg);
				write(connfd, yourPasswd, strlen(yourPasswd));
				allUsrs[connfd].stage = inputPasswd; 
				return 1;
		}

		if ( inputPasswd == allUsrs[connfd].stage ) { /* to match account data */
			printf ( "Now your should input pass\n" );
				strcpy(pass, recvMsg);

				for ( i=0 ; i<howManyUsrs ; i++ ) {

						if ( !strcmp(allowedUsrs[i].name, name) && !strcmp(allowedUsrs[i].pass, pass) ) { /* the usr is exist on prebuilt file */
								printf ( "hello %s, %s\n", name, pass );

								for ( j=0; j<usrCanHandle ; j++ ) { /* check repeat log-in */

										if ( !strcmp( allUsrs[j].name, name) && ( cmdMode == allUsrs[j].stage )) {
												write(connfd, youHaveBeenOnline, strlen(youHaveBeenOnline));
												return 1;
										}
								}
								write(connfd, youAreOnline, strlen(youAreOnline));
								allUsrs[connfd].stage = cmdMode;
								strcpy(allUsrs[connfd].name, name);
								strcpy(allUsrs[connfd].pass, pass);
								allUsrs[connfd].socket = connfd;
								allUsrs[connfd].serverCache = i; /* the index of usr on the account data  */


								printf ( "%s %d\n", allUsrs[connfd].name, connfd );
								return 1;
						}
				}
				allUsrs[connfd].stage = offLine;
				write(connfd, youAreNotUsr, strlen(youAreNotUsr));
		}
		if ( cmdMode == allUsrs[connfd].stage ) { /* the cmd mode */

				if ( !strcmp("logout", recvMsg) ) {
						allUsrs[connfd].stage = offLine;
						write(connfd, yourAreLogout, strlen(yourAreLogout));
						strcpy(allUsrs[connfd].name, "offLine");
						strcpy(allUsrs[connfd].pass, "offLine");
						return ;
				}
				else {
						if ( strcmp(recvMsg, "\n") ) { /* space do not rum any cmd */
								if ( 0 > pipe(fd) ) {
										fprintf(stderr, "pipe errer");
										return -1;
										exit(0);
								}

								if ( 0 > ( pid = fork() ) ) {
										printf ( "fork error\n" );
										return -1;
										exit(0);
								}
								else {
										if ( 0 <  pid  ) { /* parent */
												close(fd[1]);
												bzero(cmdBuf, BUFFSIZE);
												while ( 0 < read(fd[0], cmdBuf, BUFFSIZE) ) {
														cmdBuf[strlen(cmdBuf)] = '\0';
														write(connfd, cmdBuf, strlen(cmdBuf));
												}
												pid_t pid;
												int stat;
												pid = waitpid(-1, &stat, 0);
												printf("pid_t: %d , stat: %d\n", pid, stat);
												printf ( "I am father %d\n", getpid() );
										}
										else {                  /* son */

												printf ( "Son exec %s\n", recvMsg );
												close(connfd);
												close(fd[0]);
												if ( 0 >  dup2(fd[1], fileno(stdout)) ) {
														printf ( "error for dup2\n" );
												
												}
												if(isLegalCmd(recvMsg)>0){
													execl("/bin/bash", "bash", "-c", recvMsg, NULL );
													exit(0);
												}
												else{

													printf("The command is not allowed");
												}
										}
								}
						}
				}
		}
		printf ( "who am i %d\n", getpid() );
		return 0;
}

