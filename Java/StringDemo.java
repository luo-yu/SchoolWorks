import java.util.Scanner;
public class StringDemo {
   public static void main(String[] args) {
      int i = 5;
      i++;
      System.out.println(i);
      i-=5;
      System.out.println(i);
      Scanner keyInput = new Scanner(System.in);
      String a, b, c;
      System.out.print ("Enter three strings: ");
      a = keyInput.next();
      b = keyInput.next();
      c = keyInput.next();
      System.out.println(a + "\n" + b + "\n" + c);
      String s = b + a.concat(c);
      System.out.println(s);
      System.out.println(s.indexOf(a));
      System.out.println(s.indexOf(a, b.length()+1));
      s = s.replace('a', 'Z');
      System.out.println(s);
      System.out.println(s.substring(1,5));
      s = s.toUpperCase();
      System.out.println(s);
   }
}
