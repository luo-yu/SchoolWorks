import java.util.Random;
public class Day6Demo {
   public static void main(String [] args) {
      Random r;
      r = new Random();
      double d;
      int i;
      i = 7;  
      d = 25.2;
      d = i;  
      i = (int) d;
      System.out.println(i + " " + d);
      for(i = 1; i < 10; i=i+1) {
         System.out.println(r.nextInt(10));
         System.out.println(r.nextInt(10)+1);
         System.out.println(r.nextDouble());
      }
      double radius = 32.1;
      double area = Math.PI*Math.pow(radius, 2.);
      double max = Math.max(r.nextDouble(), r.nextDouble());
      System.out.println(area + " " + max);
   }
}