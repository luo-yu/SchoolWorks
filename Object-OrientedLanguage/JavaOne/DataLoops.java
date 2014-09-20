import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class DataLoops {
   public static void main (String [] args) throws IOException {
      File f = new File("Numbers.dat");
      Scanner dataInput = new Scanner(f);
      int numberCount = 0;
      while (dataInput.hasNext()) {
         System.out.println(dataInput.nextInt());
         numberCount++;
      } 
      System.out.println("Records = " + numberCount);
      dataInput.close();
      dataInput = new Scanner(f);
      int sumNeg = 0;
      int n;
      while (dataInput.hasNext()) {
         n = dataInput.nextInt();
         if (n < 0) {
            sumNeg += n;
         }
      }
      System.out.println("Negatives = " + sumNeg);
      dataInput.close();
      // Input validation with while loop
      Scanner key = new Scanner(System.in);
      System.out.print("Enter a number between 1 and 10: ");
      n = key.nextInt();
      while (n <= 0 || n >= 11) {
         System.out.println ("Try again!");
         System.out.print("Enter a number between 1 and 10: ");
         n = key.nextInt();
      }
      System.out.println(n + " is a valid number.");
      // Input validation with do-while loop
      do {
         System.out.print("Enter a number between 1 and 10: ");
         n = key.nextInt();
      } while (n <= 0 || n >= 11);
      System.out.println(n + " is a valid number.");
   }
}