import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/** A simple class to resolve English words.
  * This is a simple class that download's a set of valid English words
  * off the Internet and uses them to validate English words.
  * 
  * @author raodm (Professor DJ Rao)
  *
  */
public class Dictionary {
   /**
    * The constructor that initializes the list of valid words. 
    * The constructor attempts to down load a given list of valid
    * words from Dr. Byrkett's web site. If the words could not be
    * successfully down loaded this method will generate an exception. 
    */
   public Dictionary() {
      // Create hash map of valid words for quick look up
      validWords = new HashMap<String, Boolean>();
      // Load words from the server
      try {
         URL url = new URL("http://www.users.muohio.edu/byrketdl/english.txt.gz");
         GZIPInputStream zis = new GZIPInputStream(url.openStream());
         Scanner keyboard = new Scanner(zis);
         while (keyboard.hasNext()) {
            String word = keyboard.next();
            validWords.put(word, Boolean.TRUE);
         }
         // Track the set of words that have already been guessed
         alreadyGuessed = new HashMap<String, Boolean>();
      } catch (IOException e) {
         System.out.println(e);
         System.out.println("Sorry. The Dictionary will not be able to operate correctly.");
         System.out.println("Note: You must be connected to the Internet to use this class.");
         System.out.println("Are you connected to the Internet?");
      }
   }
   
   /** Clears out all the words in the already guessed word list.
     * 
     */
   public void resetGuessedWords() {
      alreadyGuessed.clear();
   }
   
   /** 
    * This method can be used to determine if the user has already
    * guessed a word. This parameter must not be null.
    * 
    * @param word The word to be checked.
    *  
    * @return This method returns true is the word has already been
    * guessed by the user
    */
   public boolean hasWordBeenGuessed(String word) {
      return alreadyGuessed.containsKey(word);
   }
   
   /**
    * This method can be used to add a word to list of words
    * that have already been guessed. Note that this method 
    * does not verify if a word is a valid English word (so
    * you can add any random word as the word that the user
    * has already guessed). 
    * 
    * @param word The word to be added to the list of words
    * that have already been guessed by the user. This
    * parameter must not be null.
    */
   public void wordGuessed(String word) {
      alreadyGuessed.put(word, Boolean.TRUE);
   }
   
   /**
    * Method to determine if a given English word is a valid word. 
    * 
    * @param word The word to be checked. This parameter must not
    * be null.
    * 
    * @return This method returns true if the word is a valid English word.
    *  Otherwise it returns false.
    */
   public boolean isWordValid(String word) {
      return validWords.containsKey(word);
   }
   
   // The list of valid words loaded by the constructor.
   private HashMap<String, Boolean> validWords;
   
   // The list of words that the user has already guessed.
   private HashMap<String, Boolean> alreadyGuessed;
}
