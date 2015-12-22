#include<math.h>
#include<stdio.h>
#include<unistd.h>
#include<fcntl.h>
#include<stdlib.h>
//SVM, read a binary file and excute the
//instructions on that file
int main (int argc, char *argv[]){

	//An array allocated for memory address
	//for the virtual machine
        unsigned int mem[4096];
	for(int i =0; i<4096; i++){
		mem[i]=0;
	}

	//open the binary file, 
	int fd = open(argv[1], O_RDONLY);
	
	//check for file open error
	if(fd < 0){
		perror("Open source failed:");
		exit(2);
	}
	
	//num:read in argument value,
	//arg1, arg2, arg3: the instruction arguments
	//index: to indicate the location in the memory addr array
	unsigned int num, arg1, arg2, arg3, msb, index;
	//number of bytes read
	int len;
	
	//keep reading the file 
	while((len = read(fd,&num, 4))){
		//if the opcode is add
		if(num==0x0){
			//read arg1
			len = read(fd, &num, 4);
			//get most significant bit
			msb = num>>31;
			//determine if it's a constant or a address
			//if it's a address, arg1 is the value in the mem address
			if(msb==1)
				arg1=mem[num-(int)pow(2, 31)];
			else
				arg1 = num;
			
		
			len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;
			len = read(fd, &num, 4);
			
			index  = num - (int)pow(2, 31);
			mem[index]=arg1 + arg2;
              }        
		//if the opcode is sub
	      else if(num==0x1){
                       
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
                        mem[index]=arg1 - arg2;
              }
		//if the opcode is or
              else if(num==0x2){
			//or
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
                        mem[index]=arg1 | arg2;
              }
		//if the opcode is and
              else if(num==0x3){
                        //or
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
                        mem[index]=arg1 & arg2;
              }
		//if the opcode is xor
              else if(num==0x4){
                        //xor
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
                        mem[index]=arg1 ^ arg2;
              }
		//if the opcode is not
              else if(num==0x5){
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
                        mem[index]=~arg1;
              }
		//if the opcode is slr
              else if(num==0x6){
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
			 
		 	mem[index]=arg1>>arg2;
			
	      }
		//if the opcode is sll
              else if(num==0x7){

                  //sll
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb==1)
                                arg1=mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;


                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);

                        index = num - (int)pow(2, 31);
                        mem[index]=arg1<<arg2;
              }
		//if the opcode is print
              else if(num==0x8){
                        len = read(fd, &num, 4);
			msb = num>>31;
                        if(msb==1) {

                                arg1=mem[num-(int)pow(2, 31)];
                        }
                        else{
                                arg1 = num;

                        }
                        len = read(fd, &num, 4);
                        len = read(fd, &num, 4);

			 printf("0x%x\n", arg1);
              }
		//if the opcode is printchars
              else if(num==0x9){
	                len = read(fd, &num, 4);
			msb = num>>31;
                        if(msb==1) {

                                arg1=mem[num-(int)pow(2, 31)];
                        }
			else{
                                arg1 = num;

			}
			printf("%c%c%c%c", arg1, arg1>>8, arg1>>16, arg1>>24);
                        len = read(fd, &num, 4);


                        len = read(fd, &num, 4);

              }
             //if the opcode is jgt
	     else if(num==0xa){
			len = read(fd, &num, 4);

			msb = num>>31;


			if(msb==1)

				arg1 = mem[num-(int)pow(2, 31)];
			
			else
				arg1 = num;
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;
			
			len = read(fd, &num, 4);
			arg3 = num;
			if(arg1>arg2){
				lseek(fd, arg3*16, SEEK_SET);
			}
	     }


		//if the opcode is jlt
             else if(num==0xb){

                        len = read(fd, &num, 4);
			msb = num>>31;
			
                        if(msb==1)
                                arg1 = mem[num-(int)pow(2, 31)];
                        else
                                arg1 = num;

			
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;
                        
			len = read(fd, &num, 4);
                        arg3 = num;
                        if(arg1<arg2){
                               lseek(fd, arg3*16, SEEK_SET);
                        }
             }
		//if the opcode is jeq
             else if(num==0xc){
                        len = read(fd, &num, 4);

                        msb = num>>31;


                        if(msb==1)

                                arg1 = mem[num-(int)pow(2, 31)];

                        else
                                arg1 = num;
                        len = read(fd, &num, 4);
                        msb = num>>31;
                        if(msb>0)
                                arg2=mem[num-(int)pow(2, 31)];
                        else
                                arg2 = num;

                        len = read(fd, &num, 4);
                        arg3 = num;
                        if(arg1==arg2){
                                lseek(fd, arg3*16, SEEK_SET);
                        }
             }
		//if the opcode is stop
             else if(num==0xd){
                        lseek(fd, arg3*16, SEEK_END);
             }






	} //end while
	close(fd);

}//end main
