
public class TestDice {

	public static void main(String[] args) {
		Dice d1 = new Dice();
		Dice d2 = new Dice(2);
		
		System.out.println("d1: numDice = " + d1.getNumDice());
		System.out.println("d2: numDice = " + d2.getNumDice());
		
		for(int i =1; i<=10; i++){
			System.out.println(d1.rollDice() + " " + d2.rollDice());
		}
		d1.setNumDice(5);
		System.out.println("d1: numDice = " + d1.getNumDice());
		
		for(int i =1; i<=10; i++){
			System.out.println(d1.rollDice());
		}
	}

}
