//course: cs216-002
//Project: Programming assignment3
//Date: 11/01/2014
//Purpose: This program store a list of computer games using
//         binary search tree.
//Author: Yu Luo


#include "BST.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

/*
 Insert a node into the binary search tree
 */
void insert(string title, int year, double score, BST& tree){
	tree.add(title, year, score);
}


/*Inorder traversal*/
void inOrder(Node* root){
	if(root != NULL){
		inOrder(root->left);
		cout<<"\nName: "<<root->title<<"\nYear: "<<root->year<<"\nUser Score: "<<root->user_score<<endl;
		inOrder(root->right);
	}
	return;
}


/*Preorder traversal*/
void preOrder(Node* root){

	
	if(root != NULL){
		cout<<"\nName: "<<root->title<<"\nYear: "<<root->year<<"\nUser Score: "<<root->user_score<<endl;
		preOrder(root->left);
		preOrder(root->right);
	}

	return;

}



/*Postorder traversal*/
void postOrder(Node* root){
	if(root != NULL){
	
		postOrder(root->left);
		postOrder(root->right);
		cout<<"\nName: "<<root->title<<"\nYear: "<<root->year<<"\nUser Score: "<<root->user_score<<endl;
	}

	return;

}

/*Search binary search tree Nodes with partial key
 string k, and print out the related Nodes. The variable
 c tracks the number of related node found*/
void searchPartial(string k,Node* root, int& c){

	
	if(root !=NULL){
		if((root->title).find(k) !=std::string::npos){
			cout<<"\nName: "<<root->title<<"\nYear: "<<root->year<<"\nUser Score: "<<root->user_score<<endl;
			c++;
		}
		searchPartial(k, root->left, c);
		searchPartial(k, root->right, c);
	}
}




/*Read the information from a text file that is passed
 by a commind line argument, and store the information
 in the text file into the binary search tree.
 Display menu for user to interact with the binary
 search tree*/
int main(int argc, char* argv[]){
	//Binary search tree to store the
	//games
	BST myTree;
	

	
	//use to track if the user
	//wants to quit
	bool quit = false;
	
	//used to track user choices
	string choice="";
	
	ifstream gameFile;

	gameFile.open(argv[1], ios::in);
	//If the text file is open
	if (gameFile.is_open())
	{
		
		string line="";
		string titleToInsert="";
		
		//populate the information to a binary search tree
		while (getline(gameFile,line) )
		{
			//extract the title
			titleToInsert = line.substr(0, line.find(":"));
			
			
			//extract year and scores
			string a= line.substr(line.find(":")+1);
			
			istringstream iss(a);
			int year;
			double score;
			
			iss>>year>>score;
			
		
			
			//insert a line of information into a binary
			//tree
			insert(titleToInsert, year, score, myTree);

			
		}
		

	

		
		

		cout<<"This application stores a user defined list of video games\n"
				<<" in a binary search tree, and allows the user to perform\n"
				<<" various actions on that tree. Please choose an option by\n"
				<<" entering a number.\n"<<endl;
		
		//as long as the user does not choose to quit
		while (!quit) {
			
			cout<<"1. Insert new game"<<endl;
			cout<<"2. Search games"<<endl;
			cout<<"3. List games"<<endl;
			cout<<"4. Modify user scores"<<endl;
			cout<<"5. Quit"<<endl;
			
			//get user choice
			getline(cin, choice);
			
			//if user decide to insert a new game
			if (choice=="1") {
				cout<<"Please enter the game information: "<<endl;
				
				//extract title, year, score from user inputs
				string title="";
				cout<<"Name: ";
				getline(cin, title);
				
				
				string y="";
				int year = 0;
				
				cout<<"Year published (YYYY):  ";
				getline(cin, y);
				istringstream(y)>>year;
			
				
				
				string u ="";
				double score=0.0;
				cout<<"User score (it is in the rage[0.0, 10.0]): ";
				getline(cin, u);
				istringstream(u)>>score;
			
				cout<<"You are about to insert: "<<title << "  "<<year<<"  "<<score<<endl;	
				//insert user inputs into myTree
				insert(title, year, score, myTree);
				
			}
			
			//If the user choose to search games
			else if (choice=="2"){

				cout<<"Plese enter a key to search for: ";
				string key="";
				getline(cin, key);

				int item = 0;
				searchPartial(key, myTree.returnRoot(), item);
				
				cout<<"\nYour search returned "<< item <<" results"<<endl;
				
			}
			
			//If user choose to list games
			else if (choice=="3"){
				cout<<"Please indicate (by number"
					<<" the method\nby which you would like to list the games: "<<endl;
				
				//use to track user chocies for listing games
				string option ="";
				bool back = false;
				
				//as long as user does not choose to
				// go back to the main menu
				while (!back) {
					
					cout<<"1. Preorder List"<<endl;
					cout<<"2. Postorder List"<<endl;
					cout<<"3. Inorder list"<<endl;
					cout<<"4. Back to main"<<endl;
					
					getline(cin, option);
					
					//if user chooses preorder list
					if (option=="1") {
						cout<<"Game List (Preorder): "<<endl;;
						preOrder(myTree.returnRoot());
					}
					
					//if user chooses postorder list
					else if (option=="2"){
						cout<<"Game List (Postorder): "<<endl;
						postOrder(myTree.returnRoot());
					}
					
					//if user chooses inorder list
					else if (option=="3"){
						cout<<"Game List (Inorder): "<<endl;
						inOrder(myTree.returnRoot());
					}
					
					//if user chooses to back to the main menu
					else{
						
						back =true;
					}
					

				}
				
			}
			
			//if user choses to modify user scores
			else if (choice=="4"){
				cout<<"Please enter the game information for modification: "<<endl;

				string name="";
				cout<<"Name: ";
				getline(cin, name);

				string scoreS="";
				double score=0.0;
				cout<<"The latest User Score (it is in the range[0.0, 10.0]): ";
				getline(cin, scoreS);
				istringstream(scoreS)>>score;

				string searchFor="";
				cout<<"Searching for: ";
				getline(cin, searchFor);
				
				if (myTree.keyExists(name)) {
					//the add method will overwrite orignal values
					myTree.add(name, myTree.findYear(searchFor), score);
				}
				else{
					cout<<"The key doesn't exists"<<endl;
				}

			
		
				

			}
			
			//If the user choses to quit
			else{
				
				quit=true;
				cout<<"Thank you for using the program"<<endl;
				

				
				
				
		
			}
			
		}
		
		
		gameFile.close();
	

	}
   
	else cout << "Unable to open file"<<endl;

	

	return 0;
}


