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
#define	BUFSIZE 4096			/*  */

static struct termios stored_settings;

void echo_off(void)
{
    struct termios new_settings;

    tcgetattr(0,&stored_settings);
    new_settings = stored_settings;
    new_settings.c_lflag &= (~ECHO);
    tcsetattr(0,TCSANOW,&new_settings);
    return;
}

void echo_on(void)
{

    tcsetattr(0,TCSANOW,&stored_settings);
    return;
}


/* 
 * ===  FUNCTION  ======================================================================
 *         Name:  str_cli
 *  Description:  
 * =====================================================================================
 */
void str_cli ( int out, int in, int sockfd )
{
		int n, maxfd, isPasswd = 0;
		fd_set rset;
		char sendLine[BUFSIZE], recvLine[BUFSIZE];

		bzero(recvLine, BUFSIZE);
		bzero(sendLine, BUFSIZE);


	        int a = write(sockfd, "connectReq", strlen("connectReq") ); /* send connect req to server */
		printf("I wrote sockfd to server\n");

		while(1) {
			if ( 0 == read(sockfd, recvLine, BUFSIZE) ) {
				fprintf(stderr, "server terminated\n");
				return ;
			}

			printf("I received acceptID from server for my user name\n");

			write(out, recvLine, strlen(recvLine));

			if ( 0 == read(in, sendLine, BUFSIZE) ) {
				return ;	
			}	
		       if ( !strcmp(sendLine, "\n") ) {
		 		printf ( "n = %i\n", strlen(sendLine) );
				sendLine[1] = '\0';		
                       }
		       else {
				sendLine[strlen(sendLine)-1] = '\0';           /* to elimate '\n' */
	         	}
	     	      write(sockfd, sendLine, strlen(sendLine));
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
