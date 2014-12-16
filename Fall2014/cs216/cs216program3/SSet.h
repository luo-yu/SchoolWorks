//course: cs216-002
//Project: Programming assignment3
//Date: 11/01/2014
//Purpose: This program define a set
//Author: Yu Luo

#ifndef SSET_H_
#define SSET_H_

#include <string>
using namespace std;

class SSet {
 public:
  //Return the number of items currently in the SSet
  virtual unsigned long size() = 0;

  //Add a new item, with value year and user_score, with string title.
  // If an item with title already exists, overwrite it
  virtual void add(string title, int year, double user_score) = 0;

  //Remove the item with string title. If there is no such item, do nothing.
  virtual void remove(string title) = 0;

  //Return the title of the game with title. 
  // If there is no such item, throw an exception.
  virtual int findYear(string title) = 0;

  //Return the user_score of the the game with title
  // If there is no such item, throw an exception
  virtual double findScore(string title)=0;
  //Return true if there is an item with title in the table. If not,
  // return false
  virtual bool keyExists(string title) = 0;

  //If there is a string in the set that is > k,
  // return the first such key. If not, return k
  virtual string next(string k) = 0;
  //If there is a string in the set that is < k,
  // return the first such key. If not, return k
  virtual string prev(string k) = 0;
};
#endif /* SSET_H_*/
