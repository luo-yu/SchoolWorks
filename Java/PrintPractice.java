// Day 2 Class Exercise
public class PrintPractice {
   public static void main (String[] args) {
      displayName();
      displayDiamond();
      displaySum(1, 2);
      displaySum(5, 6);
   }
   public static void displayName() {
      System.out.print("+-----+\n");
      System.out.print("| Don |\n");
      System.out.print("+-----+\n");
   }
   public static void displayDiamond() {
      System.out.println("+++++++++++");
      System.out.println("+    *    +");
      System.out.println("+   ***   +");      
      System.out.println("+  *****  +");
      System.out.println("+ ******* +");      
      System.out.println("+  *****  +");
      System.out.println("+   ***   +");
      System.out.println("+    *    +");      
      System.out.println("+++++++++++");
   }
   public static void displaySum(int a, int b) {
      int sum = a + b;
      System.out.println (a + " + " + b + " = " + sum);
   }
}