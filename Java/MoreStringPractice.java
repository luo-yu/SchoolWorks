// Day 8 Class Exercise
import java.util.Scanner;
public class MoreStringPractice {
   public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter three strings: ");
      String s1 = keyboard.next();
      String s2 = keyboard.next();
      String s3 = keyboard.next();
      // Use an if statement to answer questions 1, 2, and 3
      System.out.print("1. Is String 1 before String 2?  ");
      if (s1.compareTo(s2) < 0) {
         System.out.println("Yes");
      }
      else {
         System.out.println("No");
      }
      System.out.print("2. Is String 1 identical to String 2?  ");
      if (s1.equals(s2)) {
         System.out.println("Yes");
      }
      else {
         System.out.println("No");
      }
      System.out.print("3. Is String 1 identical to String 2" +
                       " if case is ignored?  ");
      if (s1.equalsIgnoreCase(s2)) {
         System.out.println("Yes");
      }
      else {
         System.out.println("No");
      }
      // Use String methods to produce the output in items 4 - 8
      System.out.println("4. String 1 with letter m replaced by lettter B: " +
                         s1.replace('m','B'));
      System.out.println("5. Position of string 1 within string 2: " + 
                         s2.indexOf(s1));
      System.out.println("6. First 3 characters of String 1: " + 
                         s1.substring(0, 3));
      int length = s2.length();
      System.out.println("7. Length of String 2: " + s2.length());
      System.out.println("8. Last 3 characters of String 2: " + 
                         s2.substring(length-3, length));
      // 9. Use an if statement to display String 3 with String 1 removed 
      // or to indicate String 1 is not in String 3
      int i = s3.indexOf(s1);
      if (i != -1) {
         int j = i + s1.length();
         System.out.println("9. String 3 with String 1 removed: " + 
                            s3.substring(0,i) + 
                            s3.substring(j, s3.length()));
      }
      else {
         System.out.println("9. String 1 is not in String 3");
      }
   }
}
