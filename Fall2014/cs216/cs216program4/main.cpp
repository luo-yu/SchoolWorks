#include <iostream>
#include <string>
#include <stack>
#include <sstream>
using namespace std;


// Determine if the passed parameter is an operand
//Returns turn if token is an operand, false otherwise
bool isOperand(string token){

	bool result=false;
	for(int i =0; i<token.length(); i++){
		if(isdigit(token[i])){
			result = true;
		}
		else{
			result = false;
			break;
		}
	}

	return result;
}

//Return true if c is an operater
bool isOperator(string c){

	return (c == "+" || c == "-" || c == "*" || c == "/");
	
}

//Determins if op2 has a lower precedence than op1
//Returns true if op2 has a lower preceence than op1, false otherwise;
bool isLowerOp(string op1, string op2){
	if( op1 == "+" || op1 == "-" )
	  {
		  return false;
	}
	  return (op2 == "+" || op2 == "-");
	

}


//Convert an infix expression into a postfix expression
//expr is a string expression to be converted
//return the expression in the postfix form
string infixToPostfix(string expr){
//1. Initialize an empty stack and empty result string variable.
	stack<string> s;
	string postfix="";
	stringstream ss(expr);
	string token;

	
	//2. Read the infix expression from left to right, one token at a time.
	while(ss>>token){
		//3. If the token is an operand, append it to the result string.
		if(isOperand(token))
			postfix+=token;

		//4. If the token is an operator, pop operators from the stack until you reach an
			//opening parenthesis, or an operator of lower precedence. Push the operator onto
			//the stack.
		else if(isOperator(token)){
			postfix+=" ";
			while(!s.empty()){
				string op = s.top();
				if(op =="(" || isLowerOp(token, op)){break;}
				postfix+=op;
				postfix+=" ";
				s.pop();
			
			}
			s.push(token);
		}
		//5. If the token is an opening parenthesis, push it onto the stack.
		else if(token=="(")
			s.push(token);
		//6. If the token is a closing parenthesis, pop all operators from the stack until you
	   //reach an opening parenthesis and append them to the result string.
		else if(token==")"){
			postfix+=" ";
			string p = s.top();
			s.pop();
			while (!s.empty() && p != "(")
			{
				postfix+=p;
				postfix+=" ";
				p=s.top();
				s.pop();
			}
		
		}
		else{
		
			postfix+=" ";
		}	
	}

	//7. If the end of the input string is found, pop all operators and append them to the result string

	postfix+=" ";
	while (!s.empty())
	{
		postfix+=s.top();
		postfix+=" ";
		s.pop();
	}


	return postfix;
}//End infixToPostfix



//Performs evaluation on a postfix parameter
//Returns the evaluation result
//expr is a postfix expression
double evaluate(string expr){

//1. Initialize an empty stack.
	stack<double> s;
	stringstream ss(expr);
	string token;
	
//2. Read the postfix expression from left to right.
	while (ss>>token)
	{
		//3. If the token is an operand, push it onto the stack.
		if(isOperand(token)){
			double d;
			istringstream(token)>>d;
			s.push(d);
			
		}
		//If the token is an operator, pop two operands, perform the appropriate operation,
	   //and then push the result onto the stack.
		else{
				double d1 = s.top();
				s.pop();
				double d2 = s.top();
				s.pop();
				

				if(token=="+"){
					s.push(d2 + d1);
				}
				else if(token=="-"){
					s.push(d2 - d1);
				}
				else if(token=="*"){
					s.push(d2 * d1);
				}
				else if (token=="/"){
					s.push(d2 / d1);
				}
		
		}
	}
	
	//At the end of the postfix expression, pop a result from the stack. If the postfix
	//expression was correctly formed, the stack should be empty.
	double eval = s.top();
	s.pop();
	return eval;
}


// Check if all chars in the expression are valid
//return tur if the expression contains valid characters
bool isValidExpression(string expr){
	string token;
	bool valid = true;
	
	string first=expr.substr(0, expr.find(" "));
	

	if(!isOperand(first)&& expr[0]!='(')
		return false;

	string delimiter = " ";

	size_t pos = 0;

	while ((pos = expr.find(delimiter)) != std::string::npos) {
		token = expr.substr(0, pos);
		cout << token << std::endl;
		if(!isOperand(token) && !isOperator(token) && token !="(" && token !=")"){
			valid = false;
			break;
		}
		expr.erase(0, pos + delimiter.length());
	}

	return valid;
}


int main(){

	string choice="";
	string expression="";
	bool quit=false;
	string postfix="";
	while(!quit){

		cout <<"**********************************************"<<endl;
		cout <<"1. Read an expression in infix notation."<<endl;
		cout <<"2. Convert infix to postfix."<<endl;
		cout <<"3. Evaluate the expression using postfix notation."<<endl;
		cout <<"4. Exit"<<endl;
		cout <<"**********************************************"<<endl;
		cout <<"Select: ";
		getline(cin, choice);
		

		if(choice == "1"){

		
			do
			{	
				cout <<"Enter an infix expression: "<<endl;
				getline(cin, expression);
			
			}while(!isValidExpression(expression));
		
		}
		else if(choice=="2"){
			cout<<"Infix Expression: "<<expression<<endl;
			postfix=infixToPostfix(expression);
			cout<<"Postfix Expression: "<<postfix<<endl;

		}

		else if(choice=="3"){
			cout<<"Infix Expression: "<<expression<<endl;
			cout<<"Evaluation of this expression: "<<evaluate(postfix)<<endl;
	
	
		}
		else{
			quit = true;
			cout<<"Thanks for using my program"<<endl;
		}

		
	}


	//system("PAUSE");
	return 0;
}