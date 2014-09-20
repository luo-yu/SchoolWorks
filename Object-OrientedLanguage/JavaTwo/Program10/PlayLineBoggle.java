// Name:  Don Byrkett
// Instructor:  Dr. Byrkett
// CSE 174, Section D and E
// Date:  March 22, 2013
// Filename:  PlayLineBoggle.java
// Description:  Play a game of Line Boggle
import java.util.Scanner;
public class PlayLineBoggle {
   public static void main(String[] args) {
      // Initialize array of cubes
      Cube [] cubes = new Cube[6];;
      cubes[0] = new Cube("APHRTN");
      cubes[1] = new Cube("YUBAKH");
      cubes[2] = new Cube("EFVMPB");
      cubes[3] = new Cube("LOCZWT");
      cubes[4] = new Cube("DJLISG");
      cubes[5] = new Cube("MASDRC");
      // Initialize dictionary
      Dictionary engDict = new Dictionary();
      // Initialize keyboard
      Scanner keyboard = new Scanner(System.in);
      int score;
      int round;
      String again;
      do {
         // Play a game of LineBoggle
         System.out.println("**Starting New Line Boggle Game**");
         score = 0;  // Score for this game
         round = 1;  // Count of operations and wrong guesses
         boolean playMore = true;
         do {
            System.out.println("\nCubes: " + toString(cubes));
            System.out.println("Operations: guess xxx, left x, " + 
                               "right x, swap x x, roll x, quit");
            System.out.print("Enter operation for round #" + round + ": ");
            String operation = keyboard.next();
            if (operation.equals("guess")) {
               String guess = keyboard.next();
               int result = validateGuess(cubes, guess, engDict);
               if (result == 0) {
                  System.out.println("The word " + guess + 
                                     " was not found in the dictionary");
                  round++;
               }
               else if (result == -1) {
                  System.out.println("The word " + guess + 
                                     " was guessed previously");
                  round++;
               }
               else if (result == -2) {
                  System.out.println("The word " + guess + 
                                     " was not found in the cubes");
                  round++;
                  
               }
               else {
                  System.out.println("The word " + guess + " earned " + 
                                     result + " points");
                  score += result;
               }
            }
            else if (operation.equals("left")) {
               int nPos = keyboard.nextInt();
               rotateLeft(cubes, nPos);
               round++;
            }
            else if (operation.equals("right")) {
               int nPos = keyboard.nextInt();
               rotateRight(cubes, nPos);
               round++;
            }
            else if (operation.equals("swap")) {
               int p1 = keyboard.nextInt();
               int p2 = keyboard.nextInt();
               swap(cubes, p1, p2);
               round++;
            }
            else if (operation.equals("roll")) {
               int pos = keyboard.nextInt();
               rollCube(cubes, pos);
               round++;
            }
            else {
               playMore = false;
            }
         } while (round <= 5 && playMore);
         // Roll all cubes
         for (int i = 1; i <=6; i++) {
            rollCube(cubes, i);
         }
         // Reset dictionary
         engDict.resetGuessedWords();
         System.out.println("Your final score is: " + score);
         System.out.print("Play again? (Y/N): ");
         again = keyboard.next();
         System.out.println();
      }while (again.toUpperCase().equals("Y"));  
   }
   /** The String returned from this method contains
     * the six faces on the six cubes separated by two spaces.
   */
   public static String toString(Cube [] c) {
      String s = "";
      for (int i = 0; i < c.length; i++) {
         s += c[i].getFace() + "  ";
      }
      return s;
   }
   
   /** Swap the position of two cubes. 
     * The parameters are specified as 1 to 6.
     * @param pos1 position of one cube to be swapped
     * @param pos2 position of other cube to be swapped
   */
   public static void swap(Cube [] c, int pos1, int pos2) {
      Cube t = c[pos1-1];
      c[pos1-1] = c[pos2-1];
      c[pos2-1] = t;
   }
   /** Move the cubes a certain number of positions to the left. <br>
     * Hint:  Use the rotateLeft() method
     * @param n number of positions to move
   */
   public static void rotateLeft(Cube [] c, int n) {
      for (int i = 0; i < n; i++) {
         rotateLeft(c);
      }
   }
   /** Move the cubes one position to the left. 
     * The leftmost cube will be placed on the right end.
   */
   public static void rotateLeft(Cube[] c) {
      Cube t = c[0];
      for (int i = 0; i < c.length-1; i++) {
         c[i] = c[i+1];
      }
      c[c.length-1] = t;
   }
   /** Move the cubes a certain number of positions to the right. <br>
     * Hint:  Use the rotateRight() method.
     * @param n number of positions to move
   */ 
   public static void rotateRight(Cube [] c, int n) {
      for (int i = 0; i < n; i++) {
         rotateRight(c);
      }
   }
   /** Move the cubes one position to the right. 
     * The rightmost cube will be placed on the left end.
   */
   public static void rotateRight(Cube [] c) {
      Cube t = c[c.length-1];
      for (int i = c.length-1; i > 0; i--) {
         c[i] = c[i-1];
      }
      c[0] = t;  
   }
   /** Roll the specified cube one time.  
     * The parameter is specified as 1 to 6.
     * @param pos position of cube to be rolled
   */
   public static void rollCube(Cube [] c, int pos) {
      c[pos-1].roll();
   }
   /** Determine if the specified word guess by user is a valid guess
     * and a valid word.  A valid guess (1) must be in the dictionary, 
     * (2) must not have been used before, and (3) must contain the 
     * letters of the word on the cube faces in a 
     * left to right order.  These checks are performed in the order
     * given.<br>
     * @param word guess made by the user
     * @return score of a valid word (sum of the point values of 
     *           the letters)<br>
     *         0 if the word is not in the dictionary<br>
     *         -1 if the word has been previously guessed<br>
     *         -2 if the word is not found in the cubes<br>
   */
   public static int validateGuess(Cube [] c, String word, Dictionary d) {
      word = word.toUpperCase();
      if (!d.isWordValid(word)) {
         // Not in dictionary
         return 0;
      }
      else if (d.hasWordBeenGuessed(word)) {
         // Previously guessed
         return -1;
      }
      else {
         // A new word in the dictionary, see if it is in the cubes
         boolean wordFound = true;  // Assume word is found
         boolean charFound = false; // Assume a given letter is not found
         int lastIndex = 0;
         // Look at each character of the word
         for (int i = 0; i < word.length() && wordFound; i++) {
            char ch = word.charAt(i);
            charFound = false;
            // Try to find that character on the line of cubes
            for (int j = lastIndex; j < c.length && !charFound; j++) {
               if (c[j].getFace() == ch) {
                  charFound = true;
                  lastIndex = j+1;  // Starting point to look for next letter
               }
            }
            // If we never found character we were looking for, 
            // word is not there
            if (!charFound) {
               wordFound = false;
            }
         }
         if (!wordFound) {
            // Word not found on cubes
            return -2;
         }
         else {
            // Word is valid - calculate score
            int wordScore = 0;
            for (int i = 0; i < word.length(); i++) {
               boolean found = false;
               for (int j = 0; j < c.length && !found; j++) {
                  if (word.charAt(i) == c[j].getFace()) {
                     found = true;
                     wordScore += c[j].getValue();
                  }
               }
            }
            d.wordGuessed(word);
            return wordScore;
         }
      }
   }   
}
