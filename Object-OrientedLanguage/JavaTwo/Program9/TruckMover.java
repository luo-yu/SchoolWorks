// Name:  Don Byrkett
// Instructor:  Dr. Byrkett
// CSE 174, Section D and E
// Date:  March 26, 2013
// Filename:  TruckMover.java
// Description:  Simulate movement of two trucks loading and 
//               unloading material
import java.util.Scanner;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
public class TruckMover {
   public static void main (String [] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      String command = new String();
      System.out.print("Enter file name: ");
      String fileName = keyboard.nextLine();
      File f = new File(fileName);
      Scanner file = new Scanner(f);
      // Create two trucks, one with capacity 20 and one with
      // capacity 10
      Truck truck1 = new Truck(20);
      Truck truck2 = new Truck(10);
      int t = 1;
      // Assign truck to currently receive instructions
      Truck currentTruck = truck1;
     // Process a file of commands
      do {
         command = file.next();     
         // "load" option
         if (command.equals("load")) {
            int amountToLoad = file.nextInt();
            System.out.print("Command: load " + amountToLoad + " ==> ");
            int actualLoad = currentTruck.load(amountToLoad);
            System.out.println(actualLoad + " tons were loaded");
         }
         // "dump" option
         else if (command.equals("dump")) {
            System.out.print("Command: dump ==> ");
            int amountDumped = currentTruck.dump();
            System.out.println(amountDumped + " tons were dumped");
         }
         // "move" option
         else if (command.equals("move")) {
            int x = file.nextInt();
            int y = file.nextInt();
            System.out.print("Command: move " + x + " " + y + " ==> ");
            currentTruck.move(new Point(x,y));
            System.out.println(" truck moved to (" + x + ", " + y + ")");
         }
         // "home" option
         else if (command.equals("home")) {
            System.out.print("Command: home ==> ");
            currentTruck.home();
            System.out.println(" truck moved to (0, 0)");
         }
         // "switch" option
         else if (command.equals("switch")) {
            System.out.print("Command: switch ==> ");
            if (currentTruck == truck1) {
               System.out.println ("truck 2 is now active");
               currentTruck = truck2;
               t = 2;
            }
            else {
               System.out.println ("truck 1 is now active");
               currentTruck = truck1;
               t = 1;
            }
         }
         // "quit" option
         else if (command.equals("quit")) {
            System.out.println("Command: quit ==> command file finished");
            // This will end loop
         }
         // invalid command
         else {
            // This will prompt for another command - invalid command
            System.out.println("Command: " + command + " ==> invalid command");
         }
         System.out.println("Truck " + t + " state: " + currentTruck.toString());
      } while (!command.equals("quit"));
      file.close();
      System.out.println ("****Truck 1 summary****");
      System.out.println ("Truck 1 state: " + truck1.toString());
      System.out.println ("Total miles travelled = " + 
                          truck1.getMilesTravelled());
      System.out.println ("Total tons delivered = " + 
                          truck1.getTotalDeliveries());
      System.out.println ("****Truck 2 summary****");
      System.out.println ("Truck 2 state: " + truck2.toString());
      System.out.println ("Total miles travelled = " + 
                          truck2.getMilesTravelled());
      System.out.println ("Total tons delivered = " + 
                          truck2.getTotalDeliveries());
   }   
}