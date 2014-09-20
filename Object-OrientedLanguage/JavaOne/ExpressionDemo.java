public class ExpressionDemo {
   public static void main(String[] args) {
      // Integers
      System.out.println(1+2*3);
      System.out.println(9/2);
      System.out.println(9%2);
      System.out.println((1+2)*3);     
      // Doubles
      System.out.println(9./2.);
      System.out.println(9.%2.1);
      System.out.println(6.1E1+3.1);
      // Characters
      System.out.println('a');
      System.out.println( (0+'a') );
      System.out.println( ('a'+'A') );
      // Booleans
      System.out.println(true || false);
      System.out.println(!true);
      int i = 7;
      System.out.println(i >= 7 && true);
      // Mixing ints and doubles
      System.out.println(9./2);
      System.out.println(9/2*4.);
      System.out.println(9/2.*4);
   }
}