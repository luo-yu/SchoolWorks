public class DanglingElse {
   public static void main(String [] args) {
      int age = 12;
      if (age < 10)
      if (age > 5)
      System.out.println("Yes");
      else
      System.out.println("No");
   }
}