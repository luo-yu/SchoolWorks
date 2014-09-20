// Day 12
public class GradeCount {
   public static void main(String[] args) {
      char [] grades = {'A', 'C', 'B', 'B', 'F', 'Q'};  // Case 1
//  char [] grades = {'A', 'C', 'B', 'B', 'F', 'f',
//   'D', 'A', 'S', 'A', 'C', 'C', 'Q'};           // Case 2
//  char [] grades = {'Q'};                           // Case 3
//  char [] grades = {'A', 'B', 'C'};                 // Case 4
      
      int aCount = 0, bCount = 0, cCount = 0, dCount = 0, fCount = 0;
      int i = 0;
      // Using else-if structure inside loop
      while (grades[i] != 'Q') {
         if (grades[i] == 'A') 
            aCount++;
         else if (grades[i] == 'B')
            bCount++;
         else if (grades[i] == 'C') 
            cCount++;
         else if (grades[i] == 'D')
            dCount++;
         else if (grades[i] == 'F')
            fCount++;
         else
            System.out.println("Erroneous grade in array");
         i++;
      }
      System.out.println("A = " + aCount + ", " +
                         "B = " + bCount + ", " +
                         "C = " + cCount + ", " +
                         "D = " + dCount + ", " +
                         "F = " + fCount);      
      // Using switch structure inside loop
      aCount = 0; bCount = 0; cCount = 0; dCount = 0; fCount = 0;
      i = 0;
      while (grades[i] != 'Q') {
         switch (grades[i]) {
            case 'A':
               aCount++;
               break;
            case 'B':
               bCount++;
               break;
            case 'C':
               cCount++;
               break;
            case 'D':
               dCount++;
               break;
            case 'F':
               fCount++;
               break;
            default:
               System.out.println("Erroneous grade in array");
         }
         i++;
      }
      System.out.println("A = " + aCount + ", " +
                         "B = " + bCount + ", " +
                         "C = " + cCount + ", " +
                         "D = " + dCount + ", " +
                         "F = " + fCount);
      
   }
}
