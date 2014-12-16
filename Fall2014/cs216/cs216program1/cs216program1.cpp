//course: cs216-002
//Project: Programming assignment1
//Date: 09/21/2014
//Purpose: This program access data from text
//         files and print out data in certain
//         order
//Author: Yu Luo


#include <iostream>
#include <fstream>
using namespace std;

int main(int argc, char* argv[] ){
	
	// Access the student id provided
	// by the shell script
	string studentId = argv[1];


	ifstream studentFile("student.txt");
	ifstream courseFile("studentcourses.txt");
	
	// the variable id is used to track the student
	// id in the student.txt file
	// the variable sid is used to track the student
	// id in the studentcourses.txt file
	// lName: last name
	// fName: first name
	string id,sid, lName, fName, dept, courseId, grade;	
	
	bool isTakingCourse=false;
	bool isStudentId=false;
	
	// if the student.txt file is open
	if(studentFile.is_open()){
		// first output the student number
		// provided by the calling program
		cout << "Student number: "<< studentId<<endl;
		
		
		// start reading the student.txt 
		while(studentFile >> id >> lName >> fName >>dept){

			// if the student id provided by the shell
			// can be found in student.txt file
			if(id==studentId){
				isStudentId=true;

				// print out the last name and first name
				cout  << "Name: " << lName << ", "<<fName <<endl;

				// then read the studentcourses.txt file
				if(courseFile.is_open()){
					while(courseFile >> sid >> courseId >> grade){

						// if the student id provided by 
						// the shell script can be found
						// in the studentcourses.txt file
						if(sid==studentId){
	   					
							//output his or her courseId
							cout << courseId <<endl;
					 		isTakingCourse=true;
						}//end inner if
					}// end while 
					

					// if the student id provided by the 
					// shell script can not be found
					// in studentcourses.txt file
					if(!isTakingCourse)
						cout << "is not taking any courses"<<endl;
				}

				

				else{
					cout <<"studentcourse.txt file cannot be opened!"<<endl;
					return 0;			
				}
				
				courseFile.close();
		
			}
		}

		// if the student id cannot be found in student.txt file
		if(!isStudentId)
			cout<<"not found in student.txt"<<endl;

	} 
	else{ 
		cout << "Student.txt file cannot be open!"<<endl;
		return 0;
	}

	studentFile.close();
	return 0;

}
