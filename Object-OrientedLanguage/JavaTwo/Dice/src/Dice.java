import java.util.*;
public class Dice {
	private int numDice;
	private Random random;
	
	public Dice(){
		// default constructor--one die in the set
		numDice = 1;
		random = new Random();
	}
	
	public Dice(int n){
		// one argument constructor--n dice in the set
		
		numDice = n;
		random = new Random();
	}
	
	public int rollDice(){
		// returns the number of spots shown when tossing numDice dice
		int sum =0;
		
		// for each die in the set
		for(int i =1; i<=numDice; i++){
			sum+=random.nextInt(6)+1; // add an integer between 1 and 6 to sum
		}
		
		return sum;
	}
	
	public int getNumDice(){
		return numDice;
	}
	
	public void setNumDice(int n){
		numDice = n;
	}
}
