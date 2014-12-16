//course: cs216-002
//Project: Programming assignment2
//Date: 10/05/2014
//Purpose: This program define a doublely
//         linked list
//Author: Yu Luo


#include "LinkedLineList.h"

/*The constructor of a doubly linked 
  list. The head is a dummyNode*/
LinkedLineList::LinkedLineList(){
  numItems = 0;
  dummyNode = new Node();
  dummyNode->next = dummyNode;
  dummyNode->prev = dummyNode;
}

/*The destructor of the linked list*/
LinkedLineList::~LinkedLineList() {
  while(numItems>0){
	remove(0);
  }
  delete dummyNode;
}

/*Return a pointer to the index i.
  If index is the same as the size of
  the linked list, return a pointer
  to the dummyNode
  This will throw a string exception if 
  i is an invalid number*/
LinkedLineList::Node* LinkedLineList::find(unsigned long i){
  if(i<0 || i>numItems){
	throw (std::string)"This item does not exist in the list";
  }
 
  if(i == (unsigned long)(numItems)){
	return dummyNode;
 }
 else{
  Node* foundNode = dummyNode->next;
  for(unsigned int j =0; j<i; j++){
	foundNode = foundNode->next;
 }
  return foundNode;
  }
}

/*Set the value at index i to string x */
void LinkedLineList::set(unsigned long i, std::string x){
	find(i)->line = x;
}

/*Insert a new item, x, at index i. All items that were
originally at position i or higher get moved forward 1 to 
make room. If the list does not contain i tems, a string
exception will be throw */
void LinkedLineList::insert(unsigned long i, std::string x){
  Node* original = find(i);
  Node* addedNode = new Node();
  addedNode->line = x;
  addedNode->next = original;
  addedNode->prev = original->prev;
  addedNode->prev->next = addedNode;
  addedNode->next->prev = addedNode;
  numItems++;
  
}

/*Remove the item at index i. All items that
  were originally at position i+1 or higher get
  moved backwards 1 to fill the gap */
void LinkedLineList::remove(unsigned long i){
  Node* removedNode = find(i);
  if(removedNode == dummyNode)
	throw (std::string)"The list is empty";
  removedNode->prev->next = removedNode->next;
  removedNode->next->prev = removedNode->prev;
  delete removedNode;
  numItems--;
}

/*Return the line of string at index i */
std::string LinkedLineList::get(unsigned long i){
	return find(i)->line;
}

/*Print the entire linke list */
void LinkedLineList::displayList(){
	if(numItems!=0){
		Node* currPtr;
		int i =1;
		currPtr = dummyNode->next;
		while ((currPtr != dummyNode)){
			std::cout<<i<<"> "<<currPtr->line<<std::endl;
			currPtr=currPtr->next;
			i++;
		}
	}
}

/*Return the size of the linked list */
unsigned long LinkedLineList::size(){
  return numItems;
}
