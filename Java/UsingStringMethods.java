// Day 7 Class Exercise
import java.util.Scanner;
public class UsingStringMethods {
   public static void main(String[] args) {
      // Delcare a Scanner object associated with System.in
      Scanner keyInput = new Scanner(System.in);
      // Declare a String object initialized to 
      // an empty String ("")
      String s = new String();
      // Prompt the user for a String and place the content 
      // in the String you declared (use nextLine() method)
      System.out.print ("Enter a string: ");
      s = keyInput.nextLine();
      // Concatonate your name to the String entered
      // at the keyboard
      s = s.concat("Byrkett");
      // Use String methods to produce the output 
      // illustrated below
      System.out.println("The appended string is: " + s);
      System.out.println("The length of the string is: " + 
                         s.length());
      System.out.println("The character in position 6 is: " + 
                         s.charAt(6));
      System.out.println("The string in upper case is: " + 
                         s.toUpperCase());
      System.out.println("The string in lower case is: " + 
                         s.toLowerCase()); 
      System.out.println("The first 5 characters of the string are: " 
                            + s.substring(0,5));
      System.out.println("The string with the first 5 characters displayed twice is: " 
                            + s.substring(0, 5) + s); 
      System.out.println("The word Java begins in position: " + 
                         s.indexOf("Java"));
   }
}
