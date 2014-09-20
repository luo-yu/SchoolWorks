import java.util.Scanner;
public class Triangle {
   public static void main (String[] args) {
      final double AREACONSTANT = .5;  //******
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
      area = AREACONSTANT*base*height;   //****
      // Display answer
      System.out.println("The area of " + triangleName + " is " + area + " square feet"); 
      //***
      char first = triangleName.charAt(0);
      char third = triangleName.charAt(3);
      int length = triangleName.length();
      System.out.println("First = " + first + "\nThird = " + third +
      "\nNo. of char = " + length);
      if (first >= 65 && first <= 90) {
         System.out.println("Starts with upper case letter");
      }
      else {
         System.out.println("Does not start with upper case letter");
      }
   }
}