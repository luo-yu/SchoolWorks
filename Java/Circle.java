// Day 5 Class Exercise
// Add an import statement for the Scanner class
import java.util.Scanner;
public class Circle {
   public static void main(String[] args) {
      // Declare a final variable to hold Math.PI
      final double PI = Math.PI;
      // Declare primitive variables for the radius, area, and perimeter of a circle
      // Use meaningful variable names and start with a lower case letter
      double radius;
      double area;
      double perimeter;
      // Declare objects for the String to contain the circle name and 
      // Scanner to allow keyboard input
      String circleName = new String();
      Scanner keyboard = new Scanner(System.in);
      // Prompt the user for the name of the circle and assign the name to
      // the appropriate variable
      System.out.print ("Enter the name of your circle: ");
      circleName = keyboard.nextLine();
      // Prompt the user for the radius of the circle and assign the radius to
      // the appropriate variable
      System.out.print ("Enter the radius of your circle: ");
      radius = keyboard.nextDouble();
      // Assign a value to the variable you declared to store the area
      // and the variable you used to store the perimeter
      area = PI*radius*radius;
      perimeter = PI*radius*2.;      
      // Display the area and perimeter in a single statement as illustrated
      System.out.println ("The area of " + circleName + " is " + area + "\n" +
                          "The perimeter of " + circleName + " is " + perimeter);
      // Declare a boolean variable called areaGreaterAnd and areaGreaterOr
      boolean areaGreaterAnd, areaGreaterOr;
      // Assign the first boolean variable true if the area is greater than the 
      // perimeter AND the radius is less than 1
      areaGreaterAnd = area > perimeter && radius < 1;
      // Assign the second boolean variable true if the area is greater than the 
      // perimeter OR the radius is less than 1
      areaGreaterOr = area > perimeter || radius < 1;
      // Display the boolean variables
      System.out.println("And = " + areaGreaterAnd + "\nOr = " +
                            areaGreaterOr);
      // Use the String method charAt to display the first character
      // of the circle name
      char first = circleName.charAt(0);
      System.out.println("First character = " + first);
      // Use an if/else statement to display a message to indicate
      // if the area is greater than the perimenter or vice versa
      if (area > perimeter) {
         System.out.println("Area is greater than perimeter");
      }
      else {
         System.out.println("Perimeter is greater than area");
      }
   }
}
