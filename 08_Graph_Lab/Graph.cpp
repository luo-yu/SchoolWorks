/*****
 * Author   : brinkmwj
 * Date     : 2009-11-06
 * Sources  : All code is original
 */
#include "Graph.h"

Graph::Graph(unsigned int numNodes){
  //TODO
	adjList.resize(numNodes);

}

int Graph::getCost(int node1, int node2){
  //TODO
	//Go through the edge list, if one of the edgelist is node2, then
	// return that value
	for(int i=0; i<adjList[node1].edgeList.size(); i++){
		if(adjList[node1].edgeList[i].dest ==node2){
			int cost = adjList[node1].edgeList[i].cost;
			return cost;
		}
	}

	// if there is nothing is the edgelist, return -1
    return -1;
}

//Add an edge from node1 to node2, and from node2 to node1, with
// the given cost. If the cost is < 0, throw a string exception.
void Graph::addEdge(int node1, int node2, double cost){
  //TODO
    if (cost < 0)
    {
        throw (std::string)"The cost can not be negative";
    }

	//Creating a new e
	Edge* temp = new Edge(cost, node2);

	// It has to be dereference to get a type Edge in order
	// to push to the back of the edge list.
	adjList[node1].edgeList.push_back(*temp);

	Edge* temp2 = new Edge(cost, node1);
	
	adjList[node2].edgeList.push_back(*temp2);


	// get rid of the pointer after push the edge to the edgelist 
	delete temp;
	delete temp2;
}

//Remove the edge from node1 to node2, and also from node2 to node1.
// If there are no such edges, then don't do anything.
void Graph::removeEdge(int node1, int node2){
  //TODO


    //Go through the edge list, if one of the edgelist is node2, then
	// remvoe it, otherwise do thing
	//http://www.cplusplus.com/reference/vector/vector/erase/

	int node1EdgeListSize = adjList[node1].edgeList.size();

	for(int i=0; i<node1EdgeListSize; i++){
		if(adjList[node1].edgeList[i].dest ==node2){
			adjList[node1].edgeList.erase(adjList[node1].edgeList.begin()+i);
			i = node1EdgeListSize;
		}
	}

	int node2EdgeListSize = adjList[node2].edgeList.size();
	for(int i=0; i<node2EdgeListSize; i++){
		if(adjList[node2].edgeList[i].dest ==node1){
			adjList[node2].edgeList.erase(adjList[node2].edgeList.begin()+i);
			i = node2EdgeListSize;
		}
	}
}
