#include <stdio.h>

void echo();

/* Main program */
main()
{
  while (1)
      echo();
}

/* My gets -- just like gets - Get a string from stdin */
char *mygets(char *dest)
{
  int c = getchar();
  char *p = dest;
  while (c != EOF && c != '\n') {
    *p++ = c;
    c = getchar();
  }
  *p = '\0';
  return dest;
}


/* Echo Line */
void echo()
{
  char buf[4];    /* Way too small */
  
  mygets(buf);
  puts(buf);
}
  
void not_called()
{
  printf("This routing is never called\n");
  printf("If you see this message, something bad has happend\n");
}
