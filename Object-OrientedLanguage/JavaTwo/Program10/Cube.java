// Name:  Don Byrkett
// Instructor:  Dr. Byrkett
// CSE 174, Section D, E
// Date:  March 22, 2013
// Filename:  Cube.java
// Description:  Model a Cube containing six faces and a letter on each face
import java.util.Random;
public class Cube {
   private char [] faces;
   private int faceIndex;
   private Random rnd;
   private final String VOWELS = "AEIOU";
   private final String VALUE1 = "LNRST";
   private final String VALUE2 = "DG";
   private final String VALUE3 = "BCMP";
   private final String VALUE4 = "FHVWY";
   private final String VALUE5 = "K";
   private final String VALUE8 = "JX";
   /** Constructor to create new Cube with a given letter 
     * on each face.  Face 0 shows initially.  All letters 
     * are converted to uppercase
     * @param faceValues six letters on cube
     */
   public Cube (String faceValues) {
      faceValues = faceValues.toUpperCase();
      faces = new char[6];
      for (int i = 0; i < 6; i++) {
         faces[i] = faceValues.charAt(i);
      }
      faceIndex = 0;
      rnd = new Random();
   }
   /** Obtain letter on current face of Cube
     * @return letter on face
   */
   public char getFace() {
      return faces[faceIndex];
   }
   /** Obtain value of letter on face of Cube
     * Value = 1 for all vowels
     * Value = 1 for for L, N, R, S, and T
     * Value = 2 for D and G
     * Value = 3 for B, C, M, and P
     * Value = 4 for F, H, V, W, and Y
     * Value = 5 for K
     * Value = 8 for J and X
     * Value = 10 for Q and Z
     * @return value of letter on Cube face
   */
   public int getValue() {
      char c = faces[faceIndex];
      if (VOWELS.indexOf(c) != -1) {
         return 1;
      }
      else if (VALUE1.indexOf(c) != -1) {
         return 1;
      }
      else if (VALUE2.indexOf(c) != -1) {
         return 2;
      }
      else if (VALUE3.indexOf(c) != -1) {
         return 3;
      }
      else if (VALUE4.indexOf(c) != -1) {
         return 4;
      }
      else if (VALUE5.indexOf(c) != -1) {
         return 5;
      }
      else if (VALUE8.indexOf(c) != -1) {
         return 8;
      }
      else {
         return 10;
      }
   }
   /** Roll Cube to obtain a new face 
   */
   public void roll() {
      faceIndex = rnd.nextInt(6);
   }
}
