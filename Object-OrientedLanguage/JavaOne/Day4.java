// Day 4 Class Exercise
import java.util.Scanner;
public class Day4 {
   public static Scanner keyboard = new Scanner(System.in);
   public static void main (String[] args) {
      calculateTimes();
      extractDigits();
   }
   // Similar to programming exercise 2
   public static void calculateTimes() {
      System.out.print("Enter number of days, hours, and minutes: ");
      int days = keyboard.nextInt();
      int hours = keyboard.nextInt();
      int minutes = keyboard.nextInt();
      System.out.println("Time in days: " + (days + hours/24. + minutes/(24.*60.)) + " days");
      System.out.println("Time in hours: " + (days*24 + hours + minutes/60.) + " hours");
      System.out.println("Time in minutes: " + (days*24*60 + hours*60 + minutes) + " minutes");
      System.out.println("Time in seconds: " + (days*24*60*60 + hours*60*60 + minutes*60) + " seconds");
   }
   // Programming exercise 3.6
   public static void extractDigits() {
      System.out.print("Enter 5 digit number: ");
      int fiveDigitNumber = keyboard.nextInt();
      int first = fiveDigitNumber/10000;
      int second = fiveDigitNumber%10000/1000;
      int third = fiveDigitNumber%1000/100;
      int fourth = fiveDigitNumber%100/10;
      int fifth = fiveDigitNumber%10;
      System.out.println("First digit: " + first);
      System.out.println("Second digit: " + second);
      System.out.println("Third digit: " + third);
      System.out.println("Fourth digit: " + fourth);
      System.out.println("Fifth digit: " + fifth);      
   }
}