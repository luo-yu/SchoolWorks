//course: cs216-002
//Project: Programming assignment2
//Date: 10/05/2014
//Purpose: This program store a text file
//         into linked list. The user has
//         options to edit the linked list
//         and store the list back to the
//         text file
//Author: Yu Luo


#include "LinkedLineList.h"
#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;


int main(int argc, char* argv[]){
        

	fstream inputFile;
	inputFile.open(argv[1], ios::in);

	//Linked list to store the lines
        // in the text file
	LinkedLineList editList;

	
	string toList ="";

	//use to track line number
	int lineNum =0;

	//used to track if user want to quit
	//the editor
	bool quit = false;

	//used to track user choices
	string choice="";

	if(inputFile.is_open()){
		while (getline(inputFile, toList))
		{
			editList.insert(lineNum, toList);
			lineNum++;
		}
                
                //display the original file
		editList.displayList();
                
                //use to track the editor line
                //when user is typing 
                int editLineNum = editList.size()+1;

      		//as long as the user doesn't quit
		while (!quit){
		
			cout <<editLineNum<<"> ";
			//get user choice
			getline(cin, choice);
			
		         
                        	
			// if the user want to insert line
			if(choice.substr(0,1)=="I"){
				
				//extract the line number that the 
				//user wants to insert to
				int insertToLineNum;
				istringstream(choice.substr(2)) >> insertToLineNum;

                                //if line number is too big
			        if(insertToLineNum>editList.size()){
                                	cout<<insertToLineNum<<"> The line numer is big"<<endl;
                                }	
                                else{
                                	
					cout<<insertToLineNum<<"> ";
					//Insert the next cin line into the linked list
					string toBeInsert="";
					getline(cin, toBeInsert);
					editList.insert(insertToLineNum-1, toBeInsert);
                                        editLineNum++;
				}
			}

			//if the user want to delete line
			else if(choice.substr(0,1)=="D"){
				//extract the line number that the 
				//user wants to delete
				int deleteLineNum;
				istringstream(choice.substr(2)) >> deleteLineNum;
				if(deleteLineNum<=editList.size())
 				{
				editList.remove(deleteLineNum-1);
			        editLineNum--;
                                }
			}

			//if the user want to list all lines
			else if(choice.substr(0,1)=="L")
				editList.displayList();
			else
			{
			        //If the user wants to quit, close the 
                                // input file. Clear the input file
                                // Write the data on the linked list
                                // to the file
                                inputFile.close();
                                fstream outData;
                                outData.open(argv[1], ios::out);
                                for(int i =0; i<editList.size(); i++){
                                	outData <<editList.get(i)<<endl;
                                }
				quit = true;
                                cout<<"Thank you for using my editor."<<endl;			
       				outData.close();
			}

		}



	}
	else{
		cout << "File cannot be open!"<<endl;
		return 0;
	}

  return 0;
}
