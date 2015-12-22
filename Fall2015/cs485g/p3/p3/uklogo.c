#include <stdio.h>

struct NewBuiltIn {
    char CommandName[64];   /* Name of the Built-in command users can type */
    char FunctionName[64];  /* Name of the function in the code */
    char AnalyzerName[64];  /* Name of an analyzer function to call on every command */
};

/* Description of a pluggin's built-in command functions and command to type */
struct NewBuiltIn pluggin_method = { "uklogo", "uklogo", "" };

int uklogo(char **argv) {
  printf("\n");
  printf("U     U     K    K   \n");
  printf("U     U     K  K     \n");
  printf("U     U     K K      \n");
  printf(" U   U      K  K     \n");
  printf("  UUU       K    K   \n");
  printf("\n");
  return(0);
}
