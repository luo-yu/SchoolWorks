#include <string>

template<class Pri, class T>
Heap<Pri,T>::Heap(){
  //TODO
  arrSize = START_SIZE;
  numItems = 0;
  backingArray = new std::pair<Pri, T>[arrSize]();
}

template<class Pri, class T>
Heap<Pri,T>::~Heap(){
  //TODO
  while(numItems>0)
	remove();
  delete[] backingArray;
}

template<class Pri, class T>
void Heap<Pri,T>::grow(){
  //TODO
    std::pair<Pri, T>* newBackingArray = new std::pair<Pri, T>[arrSize*2];
        if(newBackingArray == NULL)
                throw (std::string) "Unable to add input item. Your computer may out of memory ";
        for(int i =0; i<arrSize; i++){
                newBackingArray[i] = backingArray[i];
        }
        arrSize = arrSize*2;
        delete[] backingArray;
        backingArray = newBackingArray;
}

template<class Pri, class T>
void Heap<Pri,T>::add(std::pair<Pri,T> toAdd){
  //TODO

  if(numItems + 1>arrSize)
	grow();
  backingArray[numItems+1]= toAdd;
  numItems++;
  bubbleUp(numItems);
}

template<class Pri, class T>
void Heap<Pri,T>::bubbleUp(unsigned long index){
  //TODO
  unsigned long parent = (index-1)%2;
  while(index > 0 && std::get<0>(backingArray[index])<std::get<0>(backingArray[parent])){
		backingArray[index].swap(backingArray[parent]);
        index = parent;
        parent = (index-1)%2;
  }
}


template<class Pri, class T>
void Heap<Pri,T>::trickleDown(unsigned long index){
  //TODO
	  unsigned long leftChild;
	  unsigned long rightChild;

	  while((index*2+1)<(numItems-1)){

		 leftChild = 2*index + 1;
	     rightChild = 2*index + 2;
		 
		 // if only one child
		 if(leftChild==(numItems-1)&& std::get<0>(backingArray[leftChild])<std::get<0>(backingArray[index])){
			backingArray[index].swap(backingArray[leftChild]);
		 }

	     if(std::get<0>(backingArray[leftChild])<=std::get<0>(backingArray[rightChild])){
			backingArray[index].swap(backingArray[leftChild]);
			index = leftChild;
			
		 }
		else{
			backingArray[index].swap(backingArray[rightChild]);
			index = rightChild;
		 }
	  }
 

}



template<class Pri, class T>
std::pair<Pri,T> Heap<Pri,T>::remove(){
  //TODO

  if(numItems==0)
	throw (std::string)"Empty, can't remove";
  std::pair<Pri,T> tmp = backingArray[0];
  backingArray[0] = backingArray[numItems-1];
  trickleDown(0);
  numItems--;
  return tmp;
}

template<class Pri, class T>
unsigned long Heap<Pri,T>::getNumItems(){
  //TODO
  return numItems;
}
