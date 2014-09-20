// Name:  Don Byrkett
// Instructor:  Dr. Byrkett
// CSE 174, Section D and E
// Date:  February 20, 2013
// Filename:  Craps.java
// Description:  Program to simulate a given number of
// plays of craps
import java.util.Random;
import java.util.Scanner;
public class Craps {
   public static final Random R = new Random();
   public static void main (String [] args) {
      Scanner scan = new Scanner(System.in);
      int nTimes;
      // Validate nTimes > 0
      do {
         System.out.print("Enter number of times to play: ");
         nTimes = scan.nextInt();
      } while (nTimes <= 0);
      // Play craps and count wins
      int win = 0;
      for (int i = 1; i <= nTimes; i++) {
         if (craps()) 
            win++;
      }
      // Report win percentage
      System.out.println("Win percentage = " + win/(double) nTimes);
   }
   // Method to play a single game of craps and to display each
   // roll of die
   public static boolean craps() {
      boolean win;
      int comeOut = rollTwoDie();
      System.out.print("Rolls: " + comeOut);
      // Determine results of comeOut roll
      if (comeOut == 7 || comeOut == 11) {
         win = true;
      }
      else if (comeOut == 2 || comeOut == 3 || comeOut == 12) {
         win = false;
      }
      else {
         // Continue rolling until you repeat comeOut roll
         // or roll 7
         int point = comeOut;
         int nextRoll;
         do {
            nextRoll = rollTwoDie();
            System.out.print(", " + nextRoll);
         } while (nextRoll != point && nextRoll != 7);
         if (nextRoll == 7) {
            win = false;
         }
         else {
            win = true;
         }
      }
      System.out.println(" ==> " + win);
      return win;
   }
   public static int rollTwoDie() {
      return R.nextInt(6) + R.nextInt(6) + 2;
   }
}