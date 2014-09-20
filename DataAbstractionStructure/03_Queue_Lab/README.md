Author
==========
"Luo, Yu", luoy6
03_Queue_Lab
============

Implement an array-based queue in C++

Note: When you create your project, do NOT add ArrayQueue.ipp to the list of source files, add it to the list of include files. You will see that ArrayQueue.ipp is #included at the bottom of ArrayQueue.h. See ArrayQueue.h for more explanation.

Requirements
------------

1. remove takes O(1) time. 
2. add takes O(1) time, unless it calls grow (in that case O(n) is okay). 
3. grow is only called if the number of items == backingArraySize, and the size of the array is doubled during grow
4. grow takes O(n) time. 
5. Do not leak memory (make sure grow and the destructor do the right thing)
6. getNumItems is O(1) time
7. add and remove throw excpetions as appropriate
8. You must use the array in a circular fashion. If you don't do this you probably won't be able to get #1, #2 and #3 to all be true.

Running Time Analysis
=======================
1. remove takes O(1) time. 
Each line of code, except function call of throw() method, in the remove() method takes constant time. The running time of throw() function call is unknown to me. Assuming that the function call to throw() takes constant time, then the remove() method will will have a running time of O(1). 

2. add takes O(1) time, unless it calls grow (in that case O(n) is okay). 
Each line of the code, except the function call of grow() method, in the add() method takes constant time. The running time of grow() is O(n). Thus, the running time of add() method is O(1) if it does not call the grow() method, and the running time of add() method is O(n) if it call the grow() method.  

4. grow takes O(n) time. 
In the grow() method, each line of code, except the for loop section, takes constan time. However, the for loop will loop through the backingArraySize. This means that the running time of this for loop depends on the input size, in this case, the value of backingArraySize. This line of code "for(unsigned int i =0; i<backingArraySize; i++)" will take constant time per iteration, and there will be n(n is the value of backingArraySize) interations. The line of code inside the for loop will also take constant time per iteration, and there will be n iterations. As a result, the grow() method has a running time of O(n), where n is the value of backingArraySize. 

6. getNumItems is O(1) time
The one line of code in the getNumItems() method returns a value. This line of code takes constant time, and thus, the running time of getNumItems() is O(1). 


Reading
=======
"Open Data Structures," Chapter 2, up through section 2.4 (ArrayDequeue). http://opendatastructures.org/ods-cpp/2_Array_Based_Lists.html

Questions
=========

#### 1. Which of the above requirements work, and which do not? For each requirement, write a brief response.

1. remove takes O(1) time. Each line of code in the function remove(), except the function call on the throw method, takes constant time. Ignoreand the running time does not depend on the input size.
2. add takes O(1) time, unless it calles grow  (in that case it takes O(n) time). If numItems!=backingArraySize, then add new elements at the back of the array (this takes constant time), and increase the number of items in the array (this takes constant time). 
3. grow is only called if numItems == backingArraySize, and the size of the array is doubled during grow. 
4. grow takes O(n) time. A for loop is required to copy every element in the old array to the new one, so if there are n elements in the first array, then it will have n iterations. 
5. No leak memory. The destructor deletes the memory allocated for backingArray. After grow the backingArray, the original backingArray will be deleted before it is assigned to the new backingArray. 
6. getNumItems takes O(1) time. It contains one line of code that return numItems, which is an integer.
7. add and remove throw exceptions as appropriate. If there is not enough memory for adding input items, a string exception will be thrown. If you try to remove an empty array, another string exception will be thrown.
8. The array is used in a circular fashion. 

#### 2. If we did a Stack instead of a Queue, which of the private methods and variables would we need to keep, and which could we get rid of? Explain your answer.

We will keep grow(), backingArray, numItems, and backingArraySize. We need grow() to resize the array. Wee need backingArray to store the stack. We need numItems to track number of items in the stack. We can get rid of front. The reason is that a stack is a Last in First Out (LIFO) data structure, and we don't need to know what's the index at the front of the stack. 

#### 3. What is one question that confused you about this excercise, or one piece of advice you would share with students next semester?

It took me a very long time to figure out what I suppose to do with this assignment. I did not understand the .ipp file and its function. Since I did not have any C++ experiences, it took me a long time to figure out the relationships among these files. I would suggest students really take time to know a little more about C++. 

#### 4. In Java you might write "class ArrayQueue extends Queue" ... how do you write the same thing in C++?

class ArrayQueue : public Queue <T>

#### 5. What is the purpose of "templates" in C++?

Templates make code more reusable. Templates allow functions and classes to operate with generic types. This allows a programmer to reuse templates on different data types. 

#### 6. What would the syntax be for dynamically allocating an array of 10 ints, in C++?


int* intArray = new int[10];

#### 7. What is the purpose of a class destructor in C++? Why don't you need them in Java?

The purpose of a class destructor is to deallocate memory for a class object and its members. We don't need a destructor in Java because Java has an automatic garbage collection feature. 