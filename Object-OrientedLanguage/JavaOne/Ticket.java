// Day 11 class exercise
import java.util.Scanner;
public class Ticket {
   // These final constants have scope throughout the class
   public static final double CHILD_PRICE = 13.25;
   public static final double YOUTH_PRICE = 24.50;
   public static final double ADULT_PRICE = 66.75;
   public static final int CHILD_LIMIT = 5;
   public static final int YOUTH_LIMIT = 15;   
   public static void main(String[] args) {
      Scanner keyInput = new Scanner(System.in);
      System.out.print ("Enter your age: ");
      int age = keyInput.nextInt();
      System.out.println ("Your ticket price using version 1 is: " + 
                        getPriceVersion1(age));
      System.out.println ("Your ticket price using version 2 is: " + 
                        getPriceVersion2(age));
      System.out.println ("Your ticket type is: " + getTicketType(age));
      System.out.print ("Enter the age of three guests: ");
      int guest1 = keyInput.nextInt();
      int guest2 = keyInput.nextInt();
      int guest3 = keyInput.nextInt();
      System.out.println("The total cost is: " +
                         getTotalCost(guest1, guest2, guest3));
   }
   public static double getPriceVersion1(int age) {
      // Determine ticket price
      // Use the nested if-else structure
      double price;
      if (age <= CHILD_LIMIT) {
         price = CHILD_PRICE;
      }
      else {
         if (age <= YOUTH_LIMIT) {
            price = YOUTH_PRICE;
         }
         else {
            price = ADULT_PRICE;
         }
      }
      return price;
   }
   public static double getPriceVersion2(int age) {
      // Determine ticket price
      // Use the else-if structure
      double price;
      if (age <= CHILD_LIMIT) {
         price = CHILD_PRICE;
      }
      else if (age <= YOUTH_LIMIT) {
            price = YOUTH_PRICE;
      }
      else {
         price = ADULT_PRICE;
      }
      return price;
   }
   public static String getTicketType(int age) {
      // Determine the ticket type Child, Youth, or Adult
      // Use any if structure
      String type = "Adult";
      if (age <= CHILD_LIMIT) {
         type = "Child";
      }
      else if (age <= YOUTH_LIMIT) {
         type = "Youth";
      }
      return type;
   }
   public static double getTotalCost(int age1, int age2, int age3) {
      // Determine total cost for three guests with ages provided
      // Hint:  Use previously written methods
      return getPriceVersion1(age1) + getPriceVersion1(age2) + getPriceVersion1(age3);
   }
}
 