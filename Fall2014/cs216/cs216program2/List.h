//course: cs216-002
//Project: Programming assignment2
//Date: 10/05/2014
//Purpose: This program define an 
//         interface for List
//Author: Yu Luo

#ifndef LIST_H_
#define LIST_H_
#include <string>
class List {

 
 public:

  //Return the number of items currently in the List
  virtual unsigned long size() = 0;

  //Get the value at index i, and return it.
  // If list does not contain at least i+1 items, throw a string exception
  virtual std::string get(unsigned long i) = 0;

  //Set the value at index i to x
  // If list does not contain at least i+1 items, throw a string exception
  virtual void set(unsigned long i, std::string x) = 0;

  //Add a new item, x, at position i. All items that were originally
  // at position i or higher get moved forward 1 to make room.
  // If list does not contain at least i items, throw a string exception
  virtual void insert(unsigned long i, std::string x) = 0;

  //Remove the item at position i. All items that were originally
  // at position i+1 or higher get moved backwards 1 to fill the gap.
  // If list does not contain at least i+1 items, throw a string exception
  virtual void remove(unsigned long i) = 0;
};
#endif /* LIST_H_*/