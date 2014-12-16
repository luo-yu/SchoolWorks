//course: cs216-002
//Project: Programming assignment3
//Date: 11/01/2014
//Purpose: This program defines a binary
//         search tree.
//Author: Yu Luo


#ifndef BST_H_
#define BST_H_

#include "SSet.h"
#include <string>
#include <iostream>
using namespace std;

//defines a Node of a
//binary search tree
class Node {
	public: 
		  string title;
		  int year;
		  double user_score;
		  Node* left;
		  Node* right;
};


//defines the binary search tree
class BST : public SSet {


 public:
  BST();
  ~BST();
  
  //Return the number of items currently in the SSet
  virtual unsigned long size();

  //Add a new item, year, user_score, with stirng title.
  virtual void add(string title, int year, double user_score);

  //Remove the item with string title. If there is no such item, do nothing.
  virtual void remove(string title);

  //Return the game year of game title.
  //If there is no such item, throw an exception.
  virtual int findYear(string title);

  //Return the game score of game title
  //If there is no such item, throw an exception.
  virtual double findScore(string title);


  //Return true if there is an item with string title in the table. If not,
  // return false
  virtual bool keyExists(string title);

  //If there is a string in the set that is > k,
  // return the first such key. If not, return k
  virtual string next(string k);
  //If there is a string in the set that is < k,
  // return the first such string. If not, return k
  virtual string prev(string k);

  //Return the root node of the binary serach tree
  virtual Node* returnRoot();


  

private:
  Node* root;



  //return the size of binary search tree
  virtual unsigned long size(Node* r);
  //These are the recursive versions of each of the methods in
  //the public section.
  virtual Node* add(string title, int year, double score,  Node* r);
  virtual Node* remove(string title, Node* r);

  //This one returns the address of the found node, NULL
  // if not found
  virtual Node* find(string title, Node* r);

  //Find the item in the sub-tree rooted at r which has the smallest key
  virtual Node* min(Node* r);

  //Find the item in the sub-tree rooted at r which has the largest key
  virtual Node* max(Node* r);

  //Find the next/prev node, and return its address
  virtual Node* next(string title, Node* r);
  virtual Node* prev(string title, Node* r);
  

};

#endif /* BST_H_*/
