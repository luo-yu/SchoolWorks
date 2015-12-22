#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <termios.h>

#include "csapp.h"
#define	BUFFSIZE 4096	
int main ( int argc, char *argv[] )
{

    int clientfd, port;
    char *host, buf[MAXLINE];
    rio_t rio;
    char usrNameBuf[40];
    char usrPassBuf[40];
    char cmdBuf[128];
    char *quit = "quit\n"; 
    char sendLine[BUFFSIZE], recvLine[BUFFSIZE];

    if (argc != 3) {
	fprintf(stderr, "usage: %s <host> <port>\n", argv[0]);
	exit(0);
    }
    host = argv[1];
    port = atoi(argv[2]);


   printf("Username:\n");
   Fgets(usrNameBuf, 39, stdin);
   usrNameBuf[39]='\n';
   printf("Password:\n");
   Fgets(usrPassBuf, 39, stdin);
   usrPassBuf[39]='\n';
	    
   clientfd = Open_clientfd(host, port);
   Rio_readinitb(&rio, clientfd);
   Rio_writen(clientfd, usrNameBuf, strlen(usrNameBuf));
   Rio_writen(clientfd, usrPassBuf, strlen(usrPassBuf));
   printf("User Name = %s", usrNameBuf);
   printf("User Pass = %s", usrPassBuf);
 
   //the client sends the user's password 
   //Rio_writen(clientfd, usrPassBuf, strlen(usrNameBuf));

   Rio_readlineb(&rio, buf, MAXLINE);
   printf("Login status: %s\n", buf);

   if(!strcmp(buf, "Login Approved\n")){


	while(!strcmp(quit, "quit\n")){
//		printf("rrsh >");
		Fgets(cmdBuf, 127, stdin);
		Rio_writen(clientfd, cmdBuf, strlen(cmdBuf));
		Rio_readlineb(&rio, recvLine, BUFFSIZE);
//		Fputs(recvLine, stdout);
		write(fileno(stdout), recvLine, strlen(recvLine));
	}

   }


    Close(clientfd); //line:netp:echoclient:close
    exit(0);
}

