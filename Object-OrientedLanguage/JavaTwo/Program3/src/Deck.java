/*
 * Name: Yu Luo
 * Instructor: Mr. Norman J. Krumpe
 * CSE 271, TA
 * Date: May 31, 2013
 * File: Deck.java
 * Description: The definition of a Deck class. 
 *              Including a main method for testing
 *              the constructor and methods of Deck class
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines a deck representing 52 cards on the deck which can be
 * shuffled, dealt, and reset.
 * 
 * @author Yu Luo
 * 
 */
public class Deck {
	private ArrayList<Card> cards;

	/**
	 * Constructs a deck with 52 cards, in order. The cards are not
	 * shuffled.
	 * 
	 */
	public Deck() {
		reset();
	}

	/**
	 * Deals and removes a card from the deck.
	 * 
	 * @return the remaining cards on the deck
	 */
	public Card deal() {
		if (cards.isEmpty())
			return null;
		else {
			return cards.remove(0);
		}
	}

	/**
	 * Returns the number of cards left on the deck.
	 * 
	 * @return the number of cards left on the deck
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Determines whether or not the deck is empty.
	 * 
	 * @return true if the deck is empty; false otherwise.
	 */
	public boolean isEmpty() {
		boolean result = false;
		if (cards.size() == 0)
			result = true;
		return result;
	}

	/**
	 * Randomly shuffles the cards on the deck.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Returns a string representation of the deck, consisting all the
	 * cards remaining on the deck, separated by spaces (e.g. 3H 5D
	 * 10D AC).
	 * 
	 * @return a string representation of the deck
	 */
	public String toString() {
		String result = "";

		for (int i = 0; i < cards.size(); i++) {
			result = result + cards.get(i) + " ";
		}

		return result;
	}

	/**
	 * Refills the deck with all the cards, in order. The cards are
	 * not shuffled.
	 */
	public void reset() {
		cards = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j <= 3; j++) {
				cards.add(new Card(i, j));
			}
		}

	}

	/*
	 * The main method tester for Deck class. Tests all the
	 * definitions in the Deck class.
	 */
	public static void main(String[] args) {
		Deck d = new Deck();
		System.out.println("All cards: " + d);
		System.out.println("Size before deal: " + d.size());
		for (int i = 0; i < 45; i++) {
			d.deal();
		}
		System.out.println("Size after deal 45 cards: " + d.size());
		System.out.println("Before shuffle: " + d);
		d.shuffle();
		System.out.println("After shuffle: " + d);
		System.out.println("Is empty = " + d.isEmpty());
		System.out.println("Before reset: " + d);
		d.reset();
		System.out.println("After reset: " + d);
		for (int i = 0; i < 52; i++) {
			d.deal();
		}
		System.out.println("Size after deal 52 cards: " + d.size());
		System.out.println("Is empty = " + d.isEmpty());

		Deck d1 = new Deck();
		System.out
				.println("All d1 cards to string: " + d1.toString());

		Deck d2 = new Deck();
		System.out.println("Deck d2: " + d2);
		d2.deal();
		d2.deal();
		d2.deal();
		System.out.println("After deal 3 cards on deck d2: " + d2);
		d2.reset();
		System.out.println("After reset d2: " + d2);

	}

}
