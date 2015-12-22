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
#define	userCanHandle 13			/* The total users in the user file */

#define	inputAccount 0			/* this user should send account */
#define	inputPasswd 1			/* this user should send passwd */
#define	cmdMode 2			/* this user in commend mode */
#define	offLine -1			/*  this user is off-line */



int main ( int argc, char *argv[] )
{
		struct sockaddr_in myAddr;
		struct sockaddr_in clientInfo;
		socklen_t len = sizeof(clientInfo);
		int socketId = socket(AF_INET, SOCK_STREAM, 0);
		int port = atoi(argv[1]);
		int bindId, acceptId, forkId;
		int i, n, maxi, maxfd, listenfd, sockfd;
		int nready, client;
		fd_set rset, allset;
		char buf[BUFFSIZE];
		char ip[64];
		int quit =0;

		printf ( "The server is running\n" );


		if ( !socketId ) {
				fprintf(stderr, "socket failed");
		}
		
		bzero(&myAddr, sizeof(myAddr));
		myAddr.sin_family = AF_INET;
		myAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* any ip for the host */
		myAddr.sin_port = htons(port);

		bindId = bind( socketId, (struct sockaddr *)&myAddr, sizeof(myAddr));
		
		if ( 0 > bindId  ) {
				fprintf(stderr, "bind failed\n");
				return 0;
		}

		if ( 0 > listen( socketId, 10) ) {
				fprintf(stderr, "listening failed\n");
				return 0;
		}


		initial();                             /* to initial setting */

		
		
		acceptId = accept(socketId, (struct sockaddr *)&clientInfo, &len);
		strcpy(ip, inet_ntoa(clientInfo.sin_addr));
		client = acceptId;
			

		while(!quit){
			bzero(buf, BUFFSIZE);
			if ( 0 == ( n = read(client, buf, BUFFSIZE) ) ) {
				strcpy(buf, "logout");
				printf ( "client sockfd :%d terminated \n", client);
				int i=	requestHandler( socketId, client, buf);
				if(i<0)
					quit =1;
				close(client);
				client =-1;
			}
			else {
				printf("going to call request handler\n");
				int i=	requestHandler( socketId, client, buf);
				if(i<0)
					quit =1;
				printf("returned from requesthandler, %i\n", i);
			  }
	
		}
		
		close(socketId);	
		close(acceptId);
		return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
