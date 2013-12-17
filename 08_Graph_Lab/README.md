Author
==========
"Luo, Yu", luoy6
08_Graph_Lab
============

Implement a simple graph class

Requirements
------------

1. Implement all methods of the `Graph` class.

Reading
=======
"Open Data Structures," Chapter 12, the whole chapter. http://opendatastructures.org/ods-cpp/12_Graphs.html

Questions
=========

#### 1. Which of the above requirements work, and which do not? For each requirement, write a brief response.

1. add - Work
2. remove - Work
3. getCost - Work

#### 2. For each of your methods, what is the worst-case running time? You may write your answer in terms of `n` (the number of vertices), `m` (the number of edges), `d` (the maximum degree of any node in the graph), or any combination of these. Try to give the most informative bound that you can.

1. add - The worst-case running time for this method is constant time. The running time of add is depending on the method call to push_back, and I am assuming push_back take constant time
2. remove - The worst-case running time for remove is O(d1 + d2). The degree of node1 plus the degree for node 2.
3. getCost - The worst-case running time for getCost is O(d1), the degree of node1. Worst case, we need loop through the whole edge list, which is the degree of node1. 

TODO

#### 3. Exercise 12.1 from http://opendatastructures.org/ods-cpp/12_4_Discussion_Exercises.html. You may want to draw by hand, upload the picture online (Instagram, Twitter, imgur, or some place like that), and then just put a link here.

#### 4. What is one question that confused you about this exercise, or one piece of advice you would share with students next semester?

None