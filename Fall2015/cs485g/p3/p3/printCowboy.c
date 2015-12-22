/*
To generate a shared library you need first to compile your C code with the -fPIC (position independent code) flag.

gcc -c -fPIC hello.c -o hello.o
This will generate an object file (.o), now you take it and create the .so file:

gcc hello.o -shared -o libhello.so


*/



#include <stdio.h>
#define BUFF



struct NewBuiltIn {
     char CommandName[64];   /* Name of the Built-in command users can type */
     char FunctionName[64];  /* Name of the function in the code */
     char AnalyzerName[64];  /* Name of an analyzer function to call on every command */
 };

int printCowboy(char** argv){
//Variables

    char side = '\\';
    printf("           _ ___\n");
    printf("          | V  |\n");
    printf("          |    |\n");
    printf("          |    |\n");
    printf("     \\____T____T_______/\n");
    //printf("\t\t \n");
    printf("\t___    ___    \n");
    printf("\t O\tO\n");
    printf("\t     \n");
    printf("\t    <\n");
    printf("\tb~~~~~~~~~d\t Howdy Partner!\n");
    printf("\t___________\n");
    printf("\t%c_________/\n",side);
    printf("\t  \n");

    return 0;
}




//initilize the global variable 
struct NewBuiltIn pluggin_method = {"printCowboy", "printCowboy", ""}; /* Description of a pluggin's built-in command */





  





