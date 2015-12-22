#include "csapp.h"

void echo(int connfd){
printf("echoing: /%i", connfd);

}

int main(int argc, char **argv) 
{
    int listenfd, connfd, port;
    rio_t rio;
    
    FILE *usrfp;   
    char usrNameBuf[40];
    char usrPassBuf[40];
    char checkUsrNameBuf[40];
    char checkUsrPassBuf[40];
    char* usrName;
    char* usrPass;
    char* usr;   

	char name[40];
	char pass[40];

    char fileUsrPass[40];
    socklen_t clientlen;

	/*Internet socket addresses are stored in 16-byte structures
	of the type sockaddr_in
	Internet-style socket address structure

	Internet-style socket address structure 
	struct sockaddr_in {
		unsigned short sin_family; Address family (always AF_INET) 
		unsigned short sin_port;  Port number in network byte order
		struct in_addr sin_addr; IP address in network byte order
		unsigned char sin_zero[8]; Pad to sizeof(struct sockaddr)
	};
	*/
    struct sockaddr_in clientaddr;



    struct hostent *hp;

    char *haddrp;


    printf("The server is running\n");
	
    if (argc != 2) {
	fprintf(stderr, "usage: %s <port>\n", argv[0]);
	exit(0);
    }


   usrfp = Fopen("user.txt", "r");
   Fread(checkUsrNameBuf, 1, 40, usrfp);
   usr = strtok(checkUsrNameBuf, "\n"); 
   usrName = strtok(usr , " ");
   usrPass = strtok( NULL, " " );
   strcpy(name, usrName);
   strcat(name, "\n");
   printf("name.....\n");
   int i;
   for(i =0; i <strlen(name); i++)
	printf("%c\n",name[i]);
   strcpy(pass, usrPass);
   strcat(pass, "\n");
   int j ;
   for(j =0; j<strlen(pass); j++)
   	printf("%c\n",pass[j]);


   Fclose(usrfp);





    port = atoi(argv[1]);



    /*opens and returns a listening descriptor that is 
      ready to receive connection requests on the port port*/
    listenfd = Open_listenfd(port);
  

   while (1) {  int i;
	clientlen = sizeof(clientaddr);
	connfd = Accept(listenfd, (SA *)&clientaddr, &clientlen);

	/* Determine the domain name and IP address of the client */
	hp = Gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr, 
			   sizeof(clientaddr.sin_addr.s_addr), AF_INET);
	haddrp = inet_ntoa(clientaddr.sin_addr);
	printf("server connected to %s (%s)\n", hp->h_name, haddrp);

//	echo(connfd);


       recv(connfd, &usrNameBuf, 40, 0);
       recv(connfd, &usrPassBuf, 40, 0);
  int k;
   for(k =0; k<strlen(usrNameBuf); k++)
        printf("%c\n", usrNameBuf[k]);
   int l ;
   for(l =0; l<strlen(usrPassBuf); l++)
        printf("%c\n", pass[l]);
      


 
       printf("recving usr name =%sand pass =%s", usrNameBuf, usrPassBuf);

       printf("Are the two are equal? %c", strcmp(name, usrNameBuf));   
       if(!strcmp(name, usrNameBuf) && !strcmp(pass, usrPassBuf)){
	   printf("You are authorized");
       }  

/*

       if(Fgets(checkUsrNameBuf,9, usrfp)!=NULL)
       {
   		tokenName = strtok(checkUsrNameBuf, " ");
		tokenPass = strtok(NULL, "\n");
//		strcat(tokenName, "\n");
//		strcat(tokenPass, "\n");
		printf("tokenName=%s", tokenName);
		printf("tokenPass=%s", tokenPass);
		printf("usrNameBuf=%s",strtok(usrNameBuf, "\n"));
		printf("usrPassBuf=%s", strtok(usrPassBuf, "\n"));		
		if(!strcmp(tokenName, strtok(usrNameBuf, "\n")) && !strcmp(tokenPass,strtok(usrPassBuf, "\n"))){
			printf("Authenticated");
		}

			
       }
*/
	Close(connfd);
    }
    exit(0);
}
