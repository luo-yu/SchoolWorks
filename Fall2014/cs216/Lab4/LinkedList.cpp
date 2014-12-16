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
  
   if(prevPtr!=NULL)
      prevPtr->next = newNodePtr;
   head = newNodePtr;;     
}

void LinkedList::makeList()
{
   int InValue;
   ListElement *currPtr;
   ListElement *newNodePtr;   
   
   cout << "Enter values for a linked list, one per line." << endl
	<< "Enter 999 to end the list." << endl;

   cin >> InValue;
   //Adding elements to end so that "next" will be NULL
   newNodePtr=new ListElement(InValue, NULL);
   head=newNodePtr;  //assign head to the first Node
   currPtr=head; 
   cin >> InValue;

   while ( InValue != 999)
   {
      newNodePtr=new ListElement(InValue, NULL);
      currPtr->next=newNodePtr;     //link the new node to list
      currPtr=newNodePtr;           //move the currPtr so it is at end of list
      cin >> InValue;
   }
              
}

void LinkedList::appendItem (int item)
{
   ListElement *currPtr;
   ListElement *newNodePtr;
   ListElement *prevPtr = currPtr=head;
   currPtr = head;

   //Find the end of the list...
   while (currPtr->next != NULL)
   {
      currPtr = currPtr->next;
   }

   //Now current-pointer points to the last node...
   newNodePtr = new ListElement(item, NULL);	       
   currPtr->next = newNodePtr;

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
      while (currPtr->next !=NULL && currPtr->next->datum != item)
      {
	 currPtr = currPtr->next;
      }
      //Save its location
      delPtr = currPtr->next;
      if(delPtr!=NULL)
      //Route the list around the node to be deleted
         currPtr->next = delPtr->next;
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



