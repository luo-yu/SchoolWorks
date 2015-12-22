/*
ls function
Takes no arguments, lists out all of the files/directories in the current directory.
 */

#include <dirent.h>
#include <stdio.h>
typedef enum {false,true} bool;

 struct NewBuiltIn {
     char CommandName[64];   /* Name of the Built-in command users can type */
     char FunctionName[64];  /* Name of the function in the code */
     char AnalyzerName[64];  /* Name of an analyzer function to call on every command */
 };



int ls(char ** argv)
{
	//Variables
	bool morefiles = true;
	DIR *current;
	current = opendir(".");
	struct dirent *directoryName;


	//main loop
	while(morefiles)
	{
		if((directoryName = readdir(current)) != NULL)
		{
			/*get the name of the directory, print it.*/
			char* name;
			name = directoryName->d_name;
			printf("%s\n",name);
			
			
		}

		else //There must not be more files or directories if it gets to else.
		{
			
			morefiles = false;
		}
	}
	

	//Clean up
	closedir(current);

return (0);
}




//initilize the global variable 
struct NewBuiltIn pluggin_method = {"ls", "ls", ""}; /* Description of a pluggin's built-in command */
