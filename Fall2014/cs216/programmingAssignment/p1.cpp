#include <iostream>
#include <fstream>
using namespace std;

int main(int argc, char* argv[] ){
	cout << "Number of parameters: " << argc <<endl;
	
	string line;
	string studentId = argv[1];
	cout << "Student ID = " << studentId <<endl;

	ifstream studentFile("student.txt");
	ifstream courseFile("studentcourses.txt");
	
	string id,sid, lName, fName, dept, courseId, grade;	
	
	bool isTakingCourse=false;
	bool isStudentId=false;
	if(studentFile.is_open()){
		while(studentFile >> id >> lName >> fName >>dept){
			if(id==studentId){
				isStudentId=true;
				cout << "Student number: " << studentId << "\nName: " << lName << ", "<<fName <<endl;
				if(courseFile.is_open()){
					while(courseFile >> sid >> courseId >> grade){
						if(sid==studentId){
	   					
							cout << courseId <<endl;
							isTakingCourse=true;
						}
					}
					if(!isTakingCourse)
						cout << "is not taking any courses"<<endl;
				}
				else{
					cout <<"studentcourse.txt file cannot be opened!"<<endl;
					
				}
		
			}
		}
		
		if(!isStudentId)
			cout<<"not found in student.txt"<<endl;

	} 
	else 
		cout << "Student.txt file cannot be open!"<<endl;

/*	while(studentFile >> id >> lName >> fName >> dept){
		cout << "Student Number: "<< id << "\nName:  " <<lName << ", "<< fName << "\n" << dept << endl; 
        }
*/
	return 0;

}
