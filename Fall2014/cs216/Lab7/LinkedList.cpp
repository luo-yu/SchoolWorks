//LinkedList.cpp

// Note: This implementation of LinkedList is based on 
// Chapter 4 of Bruno R. Preiss's Textbook: 
// "Data Structures and Algorithms with Object-Oriented Design Patterns
//    in C++"

#include <cstdlib>
#include <cassert>
#include "LinkedList.h"
using namespace std;

//---------------------------------------------------
//List Element Members
//---------------------------------------------------
ListElement::ListElement(int _datum, ListElement * _next):
   datum (_datum), next (_next)
{}


int ListElement::getDatum () const
{ 
   return datum; 
}


ListElement const* ListElement::getNext () const
{ 
   return next; 
}



//---------------------------------------------------
//LinkedList Members
//---------------------------------------------------
LinkedList::LinkedList () :
    head (0)
{}

void LinkedList::insertItem(int item)
{
   ListElement *currPtr = head;
   ListElement *prevPtr = NULL;
   ListElement *newNodePtr;      //points to a new node
   
   while(currPtr != NULL && item > currPtr->datum)
   {
      prevPtr = currPtr;
      currPtr = currPtr->next;
   } 

   newNodePtr = new ListElement(item, currPtr);
   if (prevPtr == NULL)
	head = newNodePtr;
   else
	prevPtr->next = newNodePtr;
}

void LinkedList::makeList()
{
   int InValue;
   ListElement *currPtr;
   ListElement *newNodePtr;   
   
   cout << "Enter values for a linked list, one per line." << endl
	<< "Enter 999 to end the list." << endl;

   cin >> InValue;

   // validate user input, make sure user inputs positive numbers
   while(InValue <0){
      cout <<" Please try again: " <<InValue<<endl;
      cin>>InValue;

   }
   //Adding elements to end so that "next" will be NULL
   newNodePtr=new ListElement(InValue, NULL);
   head=newNodePtr;  //assign head to the first Node
   currPtr=head; 
  
   cin >> InValue;
// validate user input
   while(InValue <0){
	cout <<" Please try again: "<<InValue<<endl;
        cin >>InValue;
   }

   while ( InValue != 999)
   {
      newNodePtr=new ListElement(InValue, NULL);
      // if the current pointer has the value that's less
     // than the entered value, then linked the new node to
     // the list
      if(currPtr->datum <InValue){
      currPtr->next=newNodePtr;     //link the new node to list
      currPtr=newNodePtr;           //move the currPtr so it is at end of list
      }else{
	// if the current pointer has a bigger value
	// than the entered value, the insert the new
	// value in front of the current pointer
          insertItem(InValue);

      }
      cin >> InValue;
//    validate user input
      while(InValue <0){
	cout <<" Please try again: "<<InValue<<endl;
	cin >> InValue;
      }
   }
              
}


void LinkedList::deleteItem (int item)
{
   ListElement *delPtr;
   ListElement *currPtr;

   //Treat the first node as a special case... head needs to be updated
   if (head->datum == item)
   {
      delPtr = head;
      head = head->next;
   }
   else
   {
      currPtr = head;
      //Look for the node to deleted
      while (currPtr->next != NULL && currPtr->next->datum != item)
      {
	 currPtr = currPtr->next;
      }
      if (currPtr->next == NULL)
	cout << "The element to be deleted is not in the list!" << endl;
      else
      {
      	//Save its location
      	delPtr = currPtr->next;
      
      	//Route the list around the node to be deleted
      	currPtr->next = delPtr->next;
      }
   }

   //Delete the node
   delete delPtr;
}


void LinkedList::printList () 
{
   ListElement *currPtr;
   currPtr = head;

   while (currPtr != NULL)
   {
      cout << currPtr->datum << endl;
      currPtr = currPtr->next;
   }
}



