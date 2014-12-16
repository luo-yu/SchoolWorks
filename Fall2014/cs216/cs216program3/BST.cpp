//course: cs216-002
//Project: Programming assignment3
//Date: 11/01/2014
//Purpose: This program implements a binary
//         search tree that's defined in BST.h
//Author: Yu Luo



#include "BST.h"

#include <string>
using namespace std;



BST::BST(){
  
  root = NULL;
}

BST::~BST(){
  
   while (root!=NULL) 
	   root = remove(root->title, root);
	
}
  
//Return the number of items currently in the SSet
unsigned long BST::size(){
  return size(root);
}

//Return the size of the binary search tree
unsigned long BST::size(Node* r){
   if (r == NULL) 
	return 0;
   return 1 + size(r->left) + size(r->right);
}

//Return the root of current tree
Node* BST::returnRoot(){
	return root;
}


//Add a new item that has value year, score, with string title.
void BST::add(string title, int year, double score){
  
  root = add(title, year, score, root);
}

//Remove the item that has key title. If there is no such item, do nothing.
void BST::remove(string title){
  remove(title, root);
}


//Return game year with string title.
// If there is no such game, throw an exception.
int BST::findYear(string title){
  
  if(root==NULL)
	throw (std::string) "The Node is Null";
  return find(title, root)->year;
}


//Return the game score with string title.
// If there is no such game, throw an exception.
double BST::findScore(string title){
  if(root==NULL)
	throw (std::string) "The Node is Null";
  return find(title, root)->user_score;
}



//Return true if there is an item with strint title in the table. If not,
// return false
bool BST::keyExists(string title){
  
	if (find(title, root) == NULL)
	  return false;
  return true;
}


//If there is a string title in the set that is > title,
// return the first such string. If not, return title
string BST::next(string title){
  
	Node* temp = next(title, root);
  if(temp == NULL)
	return title;
  return temp->title;
}

//Return Node r's next node
Node* BST::next(string title, Node* r){

  if(r==NULL)
	return NULL;

// if the key is greater that the root key, the look in the right.
// if the the right node is null, return null, otherwise, keep
// looking in the right
  else if((r->title)<title){
	if(r->right!=NULL)
		return next(title, r->right);
	else
		return NULL;
  }
  else if((r->title)>title){
	  if(next(title, r->left)==NULL)
		return r;
	else
		return next(title, r->left);
   }
  else{
	  return next(title, r->right);
  }
}

//If there is a string in the set that is < title,
// return the first such string. If not, return title
string BST::prev(string title){
  
	Node* temp = prev(title, root);
  if(temp==NULL)
	  return title;
  return temp->title;
}


//Return Node r's previous node
Node* BST::prev(string title, Node* r){
  
	
	// if the key is lesss than the root key, the look in the left.
	// if the the left node is null, return null, otherwise, keep
	// looking in the left
   if (r == NULL)
		return NULL;
   else if (title > (r->title)){ 
	   if(prev(title, r->right)==NULL) 
			return r;
		else
			return prev(title, r->right);;
	}
   else if (title < (r->title)) 
	   return prev(title, r->left);
	
	else{
		return prev(title, r->left);
	}
}



//Add a Node into the binary search tree
// Return the added Node
Node* BST::add(string title, int year, double score, Node* r){
  
  
  if(r==NULL){
	r = new Node();
	r->year=year;
	r->user_score=score;
	r->title=title;
	r->left = NULL;
	r->right = NULL;
  }
  else if(title.compare(r->title)==0){
	  r->year = year;
	  r->user_score=score;
  }
  else if(title< (r->title)){
	  r->left = add(title, year, score, r->left);
  }
  else{
	  r->right = add(title, year, score, r->right);
  }
  return r;
}


//Remove a Node from the binary search tree
// Return the removed Node
Node* BST::remove(string title, Node* r){
  
  if(r==NULL)
	return NULL;
  else if(r->title.compare(title)==0){
	if(r->left==NULL&&r->right==NULL){
		delete r;
		return NULL;
	}
	else if(r->left==NULL||r->right==NULL){
		Node* newN = r->left;
		if(newN==NULL){
			newN = r->right;
			delete r;
			return newN;
		}
		else{
			Node* maxN = max(r->left);
			string temp = maxN->title;
			maxN->title = r->title;
			r->title = temp;

			int tempD = maxN->year;
			maxN->year = r->year;
			r->year = tempD;


			double tempp= maxN->user_score;
			maxN->user_score = r->user_score;
			r->user_score = tempp;

			r->left = remove(title, r->left);
			return r;
		}
	}

	else if(title<r->title)
		r->left= remove(title, r->left);
	else
		r->right = remove(title, r->right);
  }
	
	return NULL;
}

//Find a Node in the tree with string title
// Return the found Node
Node* BST::find(string title, Node* r){
  
  
  if(r==NULL)
	return NULL;
  else if((r->title).compare(title)==0)
	return r;
  else if(title<r->title)
	  return find(title, r->left);
  else
	  return find(title, r->right);
 
}



//Find the Node in the sub-tree rooted at r which has the largest key
Node* BST::max(Node* r){
  
  if(r==NULL)
	return NULL;
  while(r->right !=NULL)
	r=r->right;
  return r;
}


//Find the Node in the sub-tree rooted at r which has the smallest key
Node* BST::min(Node* r){
  
if(r==NULL)
	 return NULL;
while(r->left != NULL)
	 r=r->left;
return r;
}
