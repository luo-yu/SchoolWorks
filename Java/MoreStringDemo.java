import java.util.Scanner;
public class MoreStringDemo {
   public static void main(String[] args) {
      Scanner keyInput = new Scanner(System.in);
      String a, b;
      System.out.print ("Enter two words: ");
      a = keyInput.next();
      b = keyInput.next();
      String clear = keyInput.nextLine();
      System.out.println(a.equals(b));
      System.out.println(a.equalsIgnoreCase(b));
      if (a.compareTo(b) < 0) {
         System.out.println(a + " is before " + b);
      }
      else {
         System.out.println(b + " is before " + a);
      }
         
      System.out.println ("Enter a string composed of three words: ");
      String s = keyInput.nextLine();
      int space1 = s.indexOf(" ");
      System.out.println("First = " + s.substring(0, space1));
      int space2 = s.indexOf(" ", space1+1);
      System.out.println("Second = " + s.substring(space1+1, space2));
      System.out.println("Third = " + s.substring(space2+1));
   }
}
