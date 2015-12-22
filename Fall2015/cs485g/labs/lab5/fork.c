
#include <stdio.h>
#include <unistd.h>


main() {

  int x = 1;
  pid_t pid;

  
  pid = fork();

  if (pid ==0) {
    x++;
    printf("If Child, X is incremented = %i\n", x);
    printf("hello from child\n");
    printf("The process ID in Child = %d\n", getpid());
  }
  else {
    x--;
    printf("If Parent, X is decremented = %i\n", x);
    sleep(1);
    printf("The process ID in Parent =%d\n", getpid());
    printf("The process ID of the Child = %i\n", pid);
    printf("hello from parent\n");
  }

}
