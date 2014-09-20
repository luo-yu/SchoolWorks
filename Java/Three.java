import java.util.Scanner;
public class Three {
   public static void main (String [] args) {
      Scanner s = new Scanner(System.in);
      System.out.print("Enter three numbers: ");
      int i1 = s.nextInt();
      int i2 = s.nextInt();
      int i3 = s.nextInt();
      System.out.println("Minimum (V1) = " + getMin1(i1,i2,i3));  
      System.out.println("Minimum (V2) = " + getMin2(i1,i2,i3));
      System.out.println("Is i1 Min = " + isI1Min(i1,i2,i3));
   }
   public static int getMin1(int a, int b, int c) {
      int min;
      if (a < b) {
         if (a < c) {
            min = a;
         }
         else {
            min = c;
         }
      }
      else {
         if (b < c) {
            min = b;
         }
         else {
            min = c;
         }
      }
      return min;
   }
   public static int getMin2(int a, int b, int c) {
      int min;
      if (a < b && a < c) {
         min = a;
      } else if (b < a && b < c) {
         min = b;
      }
      else {
         min = c;
      }
      return min;
   }
   public static boolean isI1Min(int a, int b, int c) {
      return a == getMin1(a, b, c);
   }
}