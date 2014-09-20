public class RevisedRectangle {
   public static void main(String[] args) {
      /* Declaration of 
       variables */
      double length = 100.;               // Double variable and literal
      double width = 50.;
      double area;
      String rectName = "MU Football Field";  // String class
      
      // Computations
      area = computeArea(length, width);  // Invoke special method
      
      // Display results
      System.out.println(rectName);       // String variable
      System.out.print("The area is ");   // String literal
      System.out.print(area + "\n");      // Escape sequence
      System.out.println("\"");           // Escape sequence
      System.out.println(length + width); // Add and convert to String
   }
   // Special method to compute area
   public static double computeArea(double x, double y) {
      return x*y;
   }
}

