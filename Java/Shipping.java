// Day 9 Class Exercise
import java.util.Scanner;
import java.util.Random;
public class Shipping {
   public static void main (String [] args) {
      Scanner s = new Scanner(System.in);
      System.out.println("Enter the weight of package and shipping price per pound: ");
      double weight = s.nextDouble();
      double cost = s.nextDouble();
      double total = weight*cost;
      int i = (int)((total + .005)*100.);
      double rounded = i/100.;
      System.out.println("The shipping charge is $" + rounded);
      System.out.println(total);
      stringMethod("man of war");
      mathMethod();
   }
   public static void stringMethod(String s) {
      int i = s.indexOf("man");
      String ans = s.substring(i+3, i+6);
      System.out.println("For String " + s + ": " + ans.toUpperCase());
   }
   public static void mathMethod() {
      Random r = new Random();
      int i = 1000+r.nextInt(1001);
      int digit = i%1000/100;
      System.out.println("Hundreds digit of " + i + " is " + digit);
   }
}