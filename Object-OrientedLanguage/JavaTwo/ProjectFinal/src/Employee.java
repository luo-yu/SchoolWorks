/**
 * 
 * 
 * Name: Yu Luo 
 * Instructor: Mr. Norman J. Krumpe 
 * CSE 271, TA 
 * Date:June 25, 2013 
 * File: Employee.java 
 * Description: An employee class
 *             that implements Comparable interface.
 * 
 * @author Yu Luo
 */
public class Employee implements Comparable<Employee> {

	private String name;
	private int age;

	/**
	 * 
	 * @param name
	 *            name of the employee
	 * @param age
	 *            employee's age
	 */
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;

	}

	/**
	 * Returns the employee's name
	 * 
	 * @return the string representation of the name of the employee
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the employee's age
	 * 
	 * @return the age of the employee
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Returns a String representation of the employee
	 * 
	 * @return a String representation of the employee
	 */
	public String toString() {
		return "[Name: " + this.name + " Age: " + this.age + "]";
	}

	/**
	 * @param that
	 *            the other object to be compared
	 * @return true if it is the same object, false otherwise
	 */
	public boolean equals(Object that) {
		if (!(that instanceof Employee))
			return false;
		return this.name.equals(((Employee) that).name)
				&& this.age == ((Employee) that).age;
	}

	/**
	 * Compares two employees according to their name and age. It will
	 * first compare employee's age, if the age are the same, then
	 * compare their name
	 * 
	 * @param that
	 *            employee to be compared with
	 * @return negative integer, if the employee is younger, positive
	 *         integer, if the employee is older, 0, if it's the same
	 *         employee
	 */
	@Override
	public int compareTo(Employee that) {
		if (this.getAge() == that.getAge())
			return this.getName().compareTo(that.getName());
		else
			return this.getAge() - that.getAge();
	}

	/**
	 * Returns the index of employee her
	 * 
	 * @param e
	 *            array of Employee
	 * @param her
	 *            a particular employee
	 * @return her index, -1 if not found
	 */
	public static int linearSearch(Employee[] e, Employee her) {
		for (int i = 0; i < e.length; i++) {
			if (e[i].equals(her))
				return i;
		}
		return -1;
	}

	/**
	 * Returns the index of a particular employee in an array of
	 * employees
	 * 
	 * @param e
	 *            the array of employees
	 * @param her
	 *            the employee to be searched with
	 * @return the particular employee's index in the array, -1 if not
	 *         found
	 */
	public static int binarySearch(Employee[] e, Employee her) {
		return recursiveBinarySearch(e, her, 0, e.length);
	}

	/**
	 * Returns the index of a particular employee in an array of
	 * employees from a low position to a high position
	 * 
	 * @param e
	 *            array of employees
	 * @param her
	 *            the employee to be searched with
	 * @param lo
	 *            the low index
	 * @param hi
	 *            the high index
	 * @return the employee's index in the array, -1 if not found
	 */
	private static int recursiveBinarySearch(Employee[] e,
			Employee her, int lo, int hi) {
		if (lo > hi)
			return -1;
		int mid = (lo + hi) / 2;

		if (e[mid].compareTo(her) < 0)
			return recursiveBinarySearch(e, her, mid + 1, hi);
		else if (e[mid].compareTo(her) > 0)
			return recursiveBinarySearch(e, her, lo, mid - 1);
		else
			return mid;

	}

	/**
	 * Sort an array of employees using quick sort algorithm
	 * 
	 * @param e
	 *            array of employees
	 * @param start
	 *            start index
	 * @param end
	 *            end index
	 */
	public static void quickSort(Employee[] e, int start, int end) {

		if (end <= start)
			return;

		Employee pivot = e[end];

		int swaplocation = start;

		for (int runner = start; runner < end; runner++) {
			if (e[runner].compareTo(pivot) < 0) {
				Employee temp = e[runner];
				e[runner] = e[swaplocation];
				e[swaplocation] = temp;
				swaplocation++;
			}

		}
		e[end] = e[swaplocation];
		e[swaplocation] = pivot;

		quickSort(e, start, swaplocation - 1);
		quickSort(e, swaplocation + 1, end);

	}

}
