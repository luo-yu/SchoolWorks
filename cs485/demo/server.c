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


#define	remoteMode 2			/* this user in commend mode */
#define	offLine -1			/*  this user is off-line */

#define	BUFFSIZE 4096			/*  */
#define	totalUsr 13			/* total users 0, 1, 2, is stdIO */
#define totalCmd 15
#define	inputAccount 0			/* this user should send account */
#define	inputPasswd 1			/* this user should send passwd */




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


void initialize();
int login( int connfd, const char *name );
int handleRequest( int sockerId, int connfd, char *inputs, char* ip, int port );
void getAccount();
void getCmd();





User allUsers[totalUsr];
NameAndPasswd legalUsers[totalUsr];
Commands legalCmds[totalCmd];
int usrCount = 0;
int cmdCount =0;

const char loginName[BUFFSIZE] = "Login:";
const char loginPasswd[BUFFSIZE] = "Password:";
const char illegalUser[BUFFSIZE] = "Login Failed\n";
const char loggingOut[BUFFSIZE] = "Logout\n"; 
const char loggedIn[BUFFSIZE] = "Login Approved\n";

const char cmdPrompt[BUFFSIZE] = ">>\0";

int isLegalCmd(char* msg);

void getAccount ( )
{
		int fp, i;
		char account[BUFFSIZE];
		char *userList[BUFFSIZE], *userName, *userPasswd;




 //Open list of users and read information into array account. Then break account into tokens
		fp = open("rrshusers.txt", O_RDWR);	
		read(fp, account, BUFFSIZE);
		userList[usrCount] = strtok(account, "\n"); 
		while ( NULL != userList[usrCount] ) {
				usrCount++;
				userList[usrCount] = strtok(NULL, "\n");
		}


// Set userName equal to the i token in userList, then get the password into userPasswd, copy those strings into struct legalUsers.
		for ( i=0 ; i < usrCount ; i++ ) {
				userName = strtok( userList[i], " "  );
				userPasswd = strtok( NULL, " " );
				strcpy(legalUsers[i].name, userName);
				strcpy(legalUsers[i].passwd, userPasswd);
		}
		close(fp);
		return ;
}	


void getCmd ( )
{
                int fp, i;
                char cmds[BUFFSIZE];
                char *cmdList[BUFFSIZE], *program;



//Open the text file containing the allowed commands, read into array cmds, break cmds into tokens and put into cmdList
                fp = open("rrshcommands.txt", O_RDWR);
                read(fp, cmds, BUFFSIZE);
                cmdList[cmdCount] = strtok(cmds, "\n");

 //Get the Number of commands.
                while ( NULL != cmdList[cmdCount] ) {
                                cmdCount++;
                                cmdList[cmdCount] = strtok(NULL, "\n");
                }


//Put the commands into structure legalCmds

                for ( i=0 ; i < cmdCount ; i++ ) {
                                program = strtok(cmdList[i], "\n"  );
                                strcpy(legalCmds[i].cmdname, program);
                }
                close(fp);
                return ;
}              



int isLegalCmd(char* msg){

	int i;
	char* lCmd;
//Compare msg to available legal commands.
printf("The input command is %s\n", msg);
	lCmd = strtok(msg, " ");
printf("The accepting cmd is %s\n", lCmd);
	for(i =0; i<cmdCount; i++){

		if(!strcmp(legalCmds[i].cmdname, msg))
			return 1;
	}

	return -1;


}


void initialize ( )
{
		int i = 0;

 //Get all of the avaiable commands and accounts and store.
		getCmd();
		getAccount();


//Set all users as offline, and sockets as closed.
		for ( i = 0; i<totalUsr ; i++ ) {
				allUsers[i].stage = offLine;
				allUsers[i].socket = -1;
				allUsers[i].serverCache = i ;
		}
		return ;
}		


int handleRequest( int listenfd, int connfd, char *inputs, char* ip, int port)
{
		char name[BUFFSIZE], passwd[BUFFSIZE], cmdBuf[BUFFSIZE];
		char cmd[BUFFSIZE], userSay[BUFFSIZE];
		int i = 0, j = 0;
		pid_t pid;
		int fd[2];
		if ( offLine == allUsers[connfd].stage ) { /* request client inpu */
				write(connfd, loginName, strlen(loginName));
				allUsers[connfd].stage = inputAccount;
				return 1;
		}
		if ( inputAccount == allUsers[connfd].stage ) { /* it get legal user, send password */
				strcpy(name, inputs);
				write(connfd, loginPasswd, strlen(loginPasswd));
				allUsers[connfd].stage = inputPasswd; 
				return 1;
		}

		if ( inputPasswd == allUsers[connfd].stage ) { /* match user data */
				strcpy(passwd, inputs);

				for ( i=0 ; i<usrCount ; i++ ) {

						if ( !strcmp(legalUsers[i].name, name) && !strcmp(legalUsers[i].passwd, passwd) ) {
								printf ( "User %s loggin from %s at TCP port %i\n", name, ip, port);


								  //Accept the login attempt. Put the user online.   

								write(connfd, loggedIn, strlen(loggedIn));
								allUsers[connfd].stage = remoteMode;
								strcpy(allUsers[connfd].name, name);
								strcpy(allUsers[connfd].passwd, passwd);
								allUsers[connfd].socket = connfd;
								allUsers[connfd].serverCache = i;


					
								printf("User %s successfully logged in\n", name);
								return 1;
						}
				}

				 //Fail the login attempt      
				allUsers[connfd].stage = offLine;
				printf("User %s denied access\n", name);
				write(connfd, illegalUser, strlen(illegalUser));
		}
		if ( remoteMode == allUsers[connfd].stage) {
				printf("User %s sent the command %s to be executed\n", name, inputs);
				if ( !strcmp("quit", inputs) ) {
						allUsers[connfd].stage = offLine;
						write(connfd, loggingOut, strlen(loggingOut));
						strcpy(allUsers[connfd].name, "offLine");
						strcpy(allUsers[connfd].passwd, "offLine");
						close(connfd);
						return ;
				}
				else {
					if(isLegalCmd(inputs)>0){
					
						if ( strcmp(inputs, "\n") ) { 
								if ( 0 > pipe(fd) ) {
										fprintf(stderr, "pipe errer");
								}

								if ( 0 > ( pid = fork() ) ) {
										printf ( "fork error\n" );
								}
								else {
										printf("Forking/Execing the command %s on behalf of %s\n", inputs, name);
										if ( 0 <  pid  ) { //parent
												close(fd[1]);
												bzero(cmdBuf, BUFFSIZE);
												while ( 0 < read(fd[0], cmdBuf, BUFFSIZE) ) {
														cmdBuf[strlen(cmdBuf)] = '\0';
														write(connfd, cmdBuf, strlen(cmdBuf));
												}
												pid_t pid;
												int stat;
												pid = waitpid(-1, &stat, 0);
											
										}
										else {                 //child

												printf ( "Child exec %s\n", inputs );
												close(connfd);
												close(fd[0]);
												if ( 0 >  dup2(fd[1], fileno(stdout)) ) {
														printf ( "error for dup2\n" );
												
												}
											    if(isLegalCmd(inputs)>0){
													execl("/bin/bash", "bash", "-c", inputs, NULL );
													exit(0);
										        }
												else{
													printf("The command %s is not allowed", inputs);
												}
										}
								}
						}
					}
					else{
						
						printf("The command %s is not allowed\n", inputs);
					}
				}
		}
		else{
			
			printf("The command %s is not allowed\n", inputs);
		}
	
		return 0;
}




int main ( int argc, char *argv[] )
{
		struct sockaddr_in myAddr;
		struct sockaddr_in clientInfo;
		socklen_t len = sizeof(clientInfo);
		int listenfd = socket(AF_INET, SOCK_STREAM, 0);
		int port = atoi(argv[1]);
		int bindId, connfd, forkId;
		int i, n, maxi, maxfd, sockfd;
		int nready, client[FD_SETSIZE];
		fd_set rset, allset;
		char buf[BUFFSIZE];
		char ip[64];


		


		if ( !listenfd ) {
				fprintf(stderr, "socket failed");
		}
		
//Fill myAddr with 0's		
		bzero(&myAddr, sizeof(myAddr));

 //Set Address Family, allow any address for the host, set port       
		myAddr.sin_family = AF_INET;
		myAddr.sin_addr.s_addr = htonl(INADDR_ANY); //any ip
		myAddr.sin_port = htons(port);

//Bind the socketID

		bindId = bind( listenfd, (struct sockaddr *)&myAddr, sizeof(myAddr));
		
		if ( 0 > bindId  ) {
				fprintf(stderr, "bind failed\n");
				return 0;
		}


//If the connection failed (Because listen returned  -1))
		if ( 0 > listen( listenfd, 10) ) {
				fprintf(stderr, "listening failed\n");
				return 0;
		}
		maxfd = listenfd;
		maxi = -1;

		for ( i=0; i<FD_SETSIZE ; i++ ) {
				client[i] = -1;
		}
		FD_ZERO(&allset);
		FD_SET(listenfd, &allset);

		initialize();                              /* to initialize setting */
		while( 1 ) {
				rset = allset;
				nready = select(maxfd+1, &rset, NULL, NULL, NULL);


 //Ensure that socketID is being pointed to by rset     
				if ( FD_ISSET(listenfd, &rset) ) {
						connfd = accept(listenfd, (struct sockaddr *)&clientInfo, &len);
						strcpy(ip, inet_ntoa(clientInfo.sin_addr));
						printf ( "new client: %s\n", ip );

//Set negative elements in client to acceptID
						for ( i=0; i<FD_SETSIZE ; i++ ) {

								if ( client[i] < 0 ) {
										client[i] = connfd;
										break;
								}
						}

						if ( FD_SETSIZE == i )
								fprintf(stderr, "too many users");


//Point allset to acceptID, ensure acceptID is not larger than the maxfd
						FD_SET(connfd, &allset);
						if ( connfd > maxfd )
								maxfd = connfd;

						if ( i > maxi )
								maxi = i;

						if ( 0 >= --nready )
								continue;
				}


//Loop through the ID's      
				for ( i=0; i <= maxi ; i++ ) {
						if ( 0 > ( client[i] ) )
								continue;

						if ( FD_ISSET( client[i], &rset) ) {
//If the  client is no longer open for writing, send logout to handleRequest to logout i client, then close the socket and set i client to -1   	
							bzero(buf, BUFFSIZE);
								if ( 0 == ( n = read(client[i], buf, BUFFSIZE) ) ) {
										strcpy(buf, "logout");
										printf ( "client sockfd :%i terminated \n", client[i] );
										handleRequest( listenfd, client[i], buf, ip, port);
										close(client[i]);
										FD_CLR(client[i], &allset);
										client[i] = -1;
								}
								else {
										handleRequest( listenfd, client[i], buf, ip, port);
								}

								if ( 0 >= --nready )
										break;
						}
				}
		}
		printf("User %s disconnected\n", buf);		
		close(listenfd);	
		close(connfd);
		return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
