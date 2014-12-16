//course: cs216-002
//Project: Programming assignment2
//Date: 10/05/2014
//Purpose: This program define an
//         interface for LinkedList
//Author: Yu Luo


#ifndef LINKEDLINELIST_H_
#define LINKEDLINELIST_H_
#include <iostream>
#include <string>
#include "List.h"
class LinkedLineList : public List
{
private:

	//create a Node for the link list
	struct Node{
		std::string line;
		Node* next;
		Node* prev;
	};


public:
	
	//Return the number of items currently in the list
	virtual unsigned long size();

	//Get the value at index i, and return it.
	// if the list does not contain i items, throw a string exception
	virtual std::string get(unsigned long i);

	//Set the value at index i to x
	//if list does not contain i items, throw a string exception
	virtual void set(unsigned long i, std::string x);

	//Insert a new item, x, at position i. All items that were
	// originally at position i or higher get moved forward 1 to
	// make room.
	// If list does not contain i items, throw a string exception
	virtual void insert(unsigned long i, std::string x);

	//Remove the item at position i. All items that were originally
	// at position i+1 or higher get moved backwards 1 to fill the gap.
	// If list does not contain i items, throw a string exception
	virtual void remove(unsigned long i);

	// display all the Nodes in the link list
	virtual void displayList();
	LinkedLineList();
	~LinkedLineList();

private:
	//Pointer to the dummy node
	Node* dummyNode;

	//return a pointer to item i.
	// if i==numItems, return a pointer to the dummyNode
	//If i is an invalid number, throw a string exception
	Node* find(unsigned long i);

	//Number of items in the list
	unsigned long numItems;



};




#endif /*LINKEDLINELIST_H_*/
