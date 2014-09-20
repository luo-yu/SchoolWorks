 // Name:  Don Byrkett
// Instructor:  Dr. Byrkett
// CSE 174, Section D and E
// Date:  March 14, 2013
// Filename:  Bowling.java
// Description:  Program to score a game of bowling
// where rolls are contained in a file
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Bowling {
   public static void main (String [] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter name of file containing rolls: ");
      String fileName = keyboard.nextLine();
      File inputFile = new File(fileName);
      Scanner file = new Scanner(inputFile);
      int frame = 1;
      boolean spare = false;
      boolean strikeLast = false;
      boolean strikeTwoBack = false;
      int score = 0;
      int first, second;
      while(file.hasNext()) {
         first = file.nextInt();
         if (frame <= 10) {
            // Normal frame (1-10)
            // Display first roll of a normal frame
            if (frame <= 9) {
               System.out.print("Frame " + frame + "-- Ball 1: " + first);
            }
            else {
               System.out.print("Frame " + frame + "- Ball 1: " + first);
            }
            if (first == 10) 
                System.out.println("   Strike!");
            else
                System.out.println();
            // Display second roll of a normal frame if needed
            if (first <= 9) {
                second = file.nextInt();
                System.out.print("          Ball 2: " + second);
                if (second == 10)
                    System.out.println("   Spare!");
                else if (first + second == 10)
                    System.out.println("    Spare!");
                else
                    System.out.println();                   
            }
            else {
                second = 0;
            }
            // Update score for frames with previous strikes or spares
            if (strikeTwoBack && strikeLast && first == 10) {
               score += 30;                   // Two back score
            }
            else if (strikeTwoBack && strikeLast) {
               score += 20 + first;           // Two back score
               score += 10 + first + second;  // One back score
               strikeTwoBack = false;
               strikeLast = false;
            }
            else if (strikeLast && first == 10) {
               strikeTwoBack = true;
            }
            else if (strikeLast && first < 10) {
               score += 10 + first + second;  // One back score
               strikeLast = false;
            }
            else if (spare) {
               score += 10 + first;
               spare = false;
            }
            // Update score for this frame
            if (first == 10) {
               strikeLast = true;
            }
            else if (first + second == 10) {
               spare = true;
            }
            else {
               score += first+second;
            }
            frame++;
         }
         else {
            // Extra frame
            if (strikeTwoBack && strikeLast) {
               // Roll two extra
               System.out.println("Extra  -- Ball 1: " + first);
               second = file.nextInt();
               System.out.println("          Ball 2: " + second);
               score += (20+first) + (10+first+second);
            }
            else if (strikeLast) {
               // Roll two extra
               System.out.println("Extra  -- Ball 1: " + first);
               second = file.nextInt();
               System.out.println("          Ball 2: " + second);
               score += (10+first+second);               
            }
            else if (spare) {
               // Roll one extra
               System.out.println("Extra  -- Ball 1: " + first);
               score += 10+first;
               
            }
         }
         System.out.println("          Score:  " + score);
      }
      System.out.println("Your total score is " + score);
   }
}