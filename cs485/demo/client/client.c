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
#define	BUFSIZE 128			/*  */


void str_cli ( int out, int in, int sockfd )
{
		int n, maxfd, isPasswd = 0;
		fd_set rset;
		char sendLine[BUFSIZE], recvLine[BUFSIZE];

		bzero(recvLine, BUFSIZE);
		bzero(sendLine, BUFSIZE);

		FD_ZERO(&rset);

		write(sockfd, "Connection Request", strlen("Connection Request") );
		while(1) {
				FD_SET(in, &rset);
				FD_SET(sockfd, &rset);
				maxfd = (sockfd>in)?(sockfd+1):(in+1);
				select(maxfd, &rset, NULL, NULL, NULL);

				if ( FD_ISSET(sockfd, &rset) ) {
						if ( 0 == read(sockfd, recvLine, BUFSIZE) ) {
								fprintf(stderr, "server terminated\n");
								return ;
						}
                                                   
						if ( !strcmp("Password:", recvLine) )
								isPasswd = 1;
						if(!strcmp("Login Approved\n", recvLine)){
						//	printf("rrsh >");
						//	write(out, recvLine, strlen(recvLine));
						}
					        write(out, recvLine, strlen(recvLine));

					
				}
				if ( FD_ISSET(in, &rset) ) {
						if ( 0 == read(in, sendLine, BUFSIZE) ) {
									return ;	
						}
						if ( 1 == isPasswd ) {
								isPasswd = 0;
						}

						if ( !strcmp(sendLine, "\n") ) {
//								printf ( "n = %d\n", strlen(sendLine) );
								sendLine[1] = '\0';
						}
						else {
								sendLine[strlen(sendLine)-1] = '\0';           /* to elimate '\n' */
						}
						write(sockfd, sendLine, strlen(sendLine));
				}
				bzero(recvLine, BUFSIZE);
				bzero(sendLine, BUFSIZE);
		}
		return ;
}		/* -----  end of function str_cli  ----- */

int main ( int argc, char *argv[] )
{



    int clientfd, port;
    char *host, buf[MAXLINE];
    rio_t rio;
    char usrNameBuf[40];
    char usrPassBuf[40];


    if (argc != 3) {
	fprintf(stderr, "usage: %s <host> <port>\n", argv[0]);
	exit(0);
    }
    host = argv[1];
    port = atoi(argv[2]);

	

    
    clientfd = Open_clientfd(host, port);


   
    str_cli(fileno(stdout), fileno(stdin), clientfd);
    
    Close(clientfd); //line:netp:echoclient:close
    exit(0);
}

