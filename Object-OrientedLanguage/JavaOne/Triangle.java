import java.util.Scanner;
public class Triangle {
   public static void main (String[] args) {
      // Primitive variable declarations
      double area;
      double base;
      double height;
      // Class variable declarations
      String triangleName = new String ();
      Scanner keyboard = new Scanner(System.in);
      // Prompt
      System.out.print ("Enter name of triangle: ");
      triangleName = keyboard.nextLine();
      System.out.print("Enter height of triangle: ");
      height = keyboard.nextDouble();
      System.out.print("Enter base of triangle: ");
      base = keyboard.nextDouble();
      // Compute
      area = 0.5*base*height;
      // Display answer
      System.out.println("The area of " + triangleName + " is " + area + " square feet");      
   }
}