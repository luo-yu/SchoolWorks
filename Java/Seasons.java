import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Seasons {
   public static void main (String [] args) throws IOException {
      File f = new File ("Dates.dat");
      Scanner s = new Scanner(f);
      int month, day;
      while (s.hasNext()) {
         month = s.nextInt();
         day = s.nextInt();
         System.out.println(month + "/" + day + 
                            " is " + getSeason(month, day));
      }
   }
   public static String getSeason(int m, int d) {
      String season;
      if (m == 1 || m == 2 || m == 3) {
         season = "Winter";
      }
      else if (m == 4 || m == 5 || m == 6) {
         season = "Spring";
      }
      else if (m == 7 || m == 8 || m == 9) {
         season = "Summer";
      }
      else {
         season = "Fall";
      }
      if (m % 3 == 0 && d >= 21) {
         if (season.equals("Winter")) {
            season = "Spring";
         }
         else if (season.equals("Spring")) {
            season = "Summer";
         }
         else if (season.equals("Summer")) {
            season = "Fall";
         }
         else {
            season = "Winter";
         }
      }
      return season;
   }
}