import java.util.Scanner;
public class LoopExamples {
   public static void main (String [] args) {
      String s = "" + code('a') + code('z') + code('c') + code('v');
      System.out.println(s);
      int [] numbers = {4, 7, -4, -9, 5, 2, 8, -3, 10, 1000}; 
      // Example 1: Sentinel controlled loop 
      int i = 0;
      while (numbers[i] != 1000) { 
         System.out.println(i + ": " + numbers[i]); 
         i++; 
      }
      // Example 2: Counting and accumulating 
      int count = 0; 
      int sum = 0; 
      while (numbers[count] != 1000) { 
         sum += numbers[count]; 
         count++; 
      } 
      System.out.println("Count: " + count + " Sum: " + sum); 
      // Example 3: Nesting if structure inside a while loop 
      int positive = 0; 
      int negative = 0; 
      int j = 0; 
      while (numbers[j] != 1000) { 
         if (numbers[j] < 0) { 
            negative++; 
         } 
         else { 
            positive++; 
         } 
         j++; 
      } 
      System.out.println("Negative: " + negative + 
                         " Positive: " + positive); 
      // Example 4: Infinite loop (remove j++ from above) 
   }
   public static char code(char in) {
      char out;
      switch (in) {
         case 'a':
            out = 'j';
            break;
         case 'b':
            out = 'q';
         break;
         case 'c':
            out = 'v';
            break;
         default:
            out = 'a';
      }
      return out;
   }
}