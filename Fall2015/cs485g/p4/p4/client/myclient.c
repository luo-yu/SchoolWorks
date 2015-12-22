
const char loginName[40]=">>User Name:";
const char loginPass[40]=">>Password:";



#include "csapp.h"

int main(int argc, char **argv) 
{
    int clientfd, port;
    char *host, buf[MAXLINE];
    rio_t rio;
    char sendBuf[2*MAXLINE];
    char recvBuf[MAXLINE];
    char usrNameBuf[40];
    char usrPassBuf[40];

    int byteSend=0;
    int byteRecv=0;


    bzero(sendBuf, MAXLINE);
    bzero(recvBuf, MAXLINE);

    if (argc != 3) {
	fprintf(stderr, "usage: %s <host> <port>\n", argv[0]);
	exit(0);
    }
    host = argv[1];
    port = atoi(argv[2]);

	

    
    clientfd = Open_clientfd(host, port);


   /*The client sends the username followed by a '\n'*/
    Rio_readinitb(&rio, clientfd);
    printf(loginName);
    Fgets(usrNameBuf, MAXLINE, stdin);
    printf("Sending user name = %s", usrNameBuf);
    Rio_writen(clientfd, usrNameBuf, strlen(usrNameBuf));

    /*The client then sends the password followed by a '\n'*/
    printf(loginPass);
    Fgets(usrPassBuf, MAXLINE, stdin);
    printf("Sending user pass = %s", usrPassBuf);
    Rio_writen(clientfd, usrPassBuf, strlen(usrPassBuf));
   

    

/*
    while (Fgets(buf, MAXLINE, stdin) != NULL) {
	Rio_writen(clientfd, buf, strlen(buf));
	Rio_readlineb(&rio, buf, MAXLINE);
	Fputs(buf, stdout);
    }*/
    Close(clientfd); //line:netp:echoclient:close
    exit(0);
}
/* $end echoclientmain */
