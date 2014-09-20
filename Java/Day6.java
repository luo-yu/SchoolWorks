// Day 6 Class Exercise
import java.util.Random;
public class Day6 {
   public static void main(String[] args) {
      convertToF(21.6);
      System.out.println();
      convertToF(0.1);
      System.out.println();
      twoInts();
      System.out.println();
      threeDoubles();      
   }
   // Convert the Celsius temperature c to Fahrenheit
   // and display the temperature as in integer
   // Display the integer rounded down, rounded up,
   // and rounded to the nearest integer
   // F = (9/5)C + 32
   public static void convertToF(double c) {
      double f = (9.0/5.0)*c + 32.;
      int down = (int) f;
      int up = down+1;
      System.out.println("Rounded down: " + down);
      System.out.println("Rounded up: " + up);
      System.out.println("Rounded: " + Math.round(f));
   }
   // Display two random integers between 1 and 10
   // and display the smallest and largest of the two
   public static void twoInts() {
      Random r = new Random();
      int i1 = r.nextInt(10) + 1;
      int i2 = r.nextInt(10) + 1;
      System.out.println("Two ints: " + i1 + " " + i2);
      System.out.println("Largest: " + Math.max(i1, i2));
      System.out.println("Smallest: " + Math.min(i1, i2));
   }
   // Display three random doubles between 0 and 1
   // and display the smallest and largest of the three
   public static void threeDoubles() {
      Random r = new Random();
      double d1 = r.nextDouble();
      double d2 = r.nextDouble();
      double d3 = r.nextDouble();
      System.out.println("Three doubles: " + d1 + " " + d2 + " " + d3);
      System.out.println("Largest: " + Math.max(d1, Math.max(d2, d3)));
      System.out.println("Smallest: " + Math.min(d1, Math.min(d2, d3)));
   }
}
