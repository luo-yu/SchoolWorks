// Day 3 Class Exercises
public class EvaluateExpressions {
   public static void main (String[] args) {
      // Inputs
      int pennies = 372;
      double radius = 11.8;
      char newLine = '\t';
      boolean ansCorrect = true;
      // a. Display an expression to compute the number of quarters
      System.out.println("a. Quarters = " + ( pennies / 25 ));
      // b. Display an expression to compute the number of nickels left
      // after the quarters have been removed
      System.out.println("b. Nickels = " + ( pennies % 25 / 5 ));
      // c. Display an expression to compute the volume of a sphere
      System.out.println("c. Volume = " + ( 4./3.*Math.PI*radius*radius*radius ));
      // d. Display the integer values of the letters contained in your initials
      System.out.print("d. Letter D = " + (0 + 'D') + '\n' +
                       "   Letter L = " + (0 + 'L') + '\n' +
                       "   Letter B = " + (0 + 'B') + '\n');
      // e. Display the sum of the integer values of the letters in your initials
      System.out.println("e. Sum = " + ( 'D' + 'L' + 'B' ));
      // f. Display whether or not pennies is an even number
      System.out.println("f. Pennies Even? = " + ( pennies % 2 == 0 ));
      // g. Display whether or not newLine has a value of 10
      System.out.println("g. newline ten? = " + ( newLine == 10 ));
      // h. Display whether radius is > 12 and ansCorrect is true
      System.out.println("h. And condition = " + ( radius > 12 && ansCorrect ));
      // i. Display whether radius is > 12 or ansCorrect is true
      System.out.println("i. Or condition = " + ( radius > 12 || ansCorrect ));     
      // Short exercise #11
      System.out.println("11a. = " + ( 7/3*2 ));
      System.out.println("11b. = " + ( 7/(3*2) ));
      System.out.println("11c. = " + ( 7.0/3*2 ));
      System.out.println("11d. = " + ( 7/3*2.0 ));
      System.out.println("11e. = " + ( 7/(3*2.0) ));
      System.out.println("11f. = " + ( 7.0/3.0*2.0 ));
      System.out.println("11g. = " + ( (7/3)*2 ));
      System.out.println("11h. = " + ( (7.0/3)*2 ));
   }
}