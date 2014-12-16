#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

//Define our own math error exception class
class Matherr {public: string errname;};
class DivideByZero : public Matherr{
	public: 
	DivideByZero(){
		this->errname = "Division by zero";
	}
        string getName(){
		return this->errname;
	}

};

//Calculate the average value of a list of scores
// stored in vector. if the size of the vector
// is 0, throw a DivideByZero expection.  
double calculateAverage(vector<double> scores){
   double total=0.0; 
   if(scores.size()==0)
	throw DivideByZero();
   for(int i =0; i<scores.size(); i++){
      total+=scores[i];
    }
   return total/scores.size();


}
int main(){
        vector<double> v;
        int vectorNum=0;
	//use to track if user still inputting values	
        bool input = true;

        cout <<"Enter your score on each line. When you are done, press 'Q' or 'q' to stop"<<endl;
        while(input){
		//use to accept user input  
                string score="";
                 int numScore=0;
        	 getline(cin, score);
                
		//if the user want to stop entering scores 
		if(score=="Q" || score=="q")
		 	input =false;

		// if user entered scores, extract the score
		// and convert it to integer
		else{
                	 istringstream(score)>>numScore;
                	 v.push_back(numScore);
		}
       }
      	// Try to call calculateAverge function to calculate
	// the average score, if there is an error, catch it
       	try{
		double avg = calculateAverage(v);
	
		cout <<"Average is: "<<avg<<endl;
	}catch(DivideByZero& err){
		cout<< "Exception: " <<err.getName() <<endl;
	}
	return 0;
}
