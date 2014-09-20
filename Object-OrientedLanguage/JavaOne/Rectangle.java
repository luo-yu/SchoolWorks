// Name: Don Byrkett
// Instructor:  Don Byrkett
// CSE 174 D
// Date: January 15, 2013
// Filename:  Rectangle.java
// Description:  Computation of rectangle properties

public class Rectangle {
   public static void main (String [] args) {
      // Declaration of variables
      int height = 5;
      int width = 6;
      int area;
      double diagonal;
      
      // Computaions
      area = height*width;
      diagonal = Math.sqrt(height*height - width*width);
      
      // Display results
      System.out.println ("The rectangle is " + height +
                          " by " + width);
      System.out.println ("The area is " + area);
      System.out.println ("The diagonal is " + diagonal);
   }
}