import java.util.Random;

/**

 * Name: Yu Luo 
 * Instructor: Mr. Norman J. Krumpe 
 * CSE 271, TA 
 * Date:June 25, 2013 
 * File: EmployeeTester.java 
 * Description: Testing Employee class definitions
 * @author Yu Luo
 */
public class EmployeeTester {

	public static void main(String[] args) {
		Random r = new Random();
		
		/*
		 * Array of names used to randomly picked later
		 */
		String[] names = { "Irene", "Ann", "Betty", "Cara", "Dee",
				"Erica", "Robin", "Adele", "Chloe", "Chris", "Joan",
				"Jody", "Joni", "Erica", "Nia", "Terry", "Kelly",
				"Grace", "Cathy", "Lee" };

		Employee[] e = new Employee[1000];
		
		for (int i = 0; i < 1000; i++) {
			/* random age */
			int age = r.nextInt(82) + 18;
			/* random name*/
			int name = r.nextInt(20);
			/* create random employee*/
			e[i]= new Employee(names[name], age);
		}
		
		/*
		 * Print unsorted array
		 */
		for(int j =0; j<e.length; j++){
			System.out.println("Index " + j + ": " + e[j]);
		}
		
		/*
		 * Demonstration of linearSearch
		 */
		int linear = Employee.linearSearch(e, new Employee("Jody", 62));
		System.out.println("Jody(age 62) is at index: " + linear);
		
		/*
		 * Sorts the array using quickSort
		 */
		Employee.quickSort(e, 0, e.length-1);
		
		/*
		 * Print sorted array
		 */
		for(int j =0; j<e.length; j++){
			System.out.println("Sorted-Index " + j + ": " + e[j]);
		}
		
		/*
		 * Demonstration of binarySearch method
		 */
		int binary = Employee.binarySearch(e, new Employee("Kelly", 55));
		System.out.println("Kelly(age 55) is at index: " + binary);
		
	}
}
