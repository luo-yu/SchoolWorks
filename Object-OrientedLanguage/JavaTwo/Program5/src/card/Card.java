package card;
/**
 * Implements a playing card with a suit and value, along with methods for
 * obtaining those values.
 * 
 * @author Norm Krumpe
 * 
 */

public class Card {

	// Constants for representing the suits
	public final static int CLUBS = 0;
	public final static int HEARTS = 1;
	public final static int SPADES = 2;
	public final static int DIAMONDS = 3;

	// Constants for representing values of
	// ace, jack, queen, and king.
	public final static int ACE = 1;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;

	// Final will keep them from being changed
	// after cards are constructed.
	private final int value;
	private final int suit;

	/**
	 * Constructs a card with a specified suit and value.
	 * 
	 * @param value
	 *            the value of the card. 2 through 10 are used to specify the
	 *            cards with those corresponding values, and constants exist for
	 *            specifying ace, jack, queen, and king
	 * @param suit
	 *            the suit of the card. Use one of Card.CLUBS, Card.Hearts,
	 *            Card.SPADES, or Card.DIAMONDS
	 */
	public Card(int value, int suit) {
		if (value < ACE || value > KING) {
			throw new IllegalArgumentException("Illegal card value: " + value);
		}
		if (suit < CLUBS || suit > DIAMONDS) {
			throw new IllegalArgumentException("Illegal card suit: " + suit);
		}

		this.value = value;
		this.suit = suit;
	}
	
	/**
	 * Constructs a new card with the same value and suit as the original.
	 * @param original the card to be copied
	 */
	public Card(Card original) {
		this(original.value, original.suit);
	}

	/**
	 * Gets this card's suit.
	 * 
	 * @return the suit of this card
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Gets this card's value
	 * 
	 * @return the value of this card
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets a letter representing the suit
	 * 
	 * @return a single letter, either "C", "H", "S", or "D", representing
	 *         clubs, hearts, spades, and diamonds respectively
	 */
	private String getSuitString() {
		switch (suit) {
		case CLUBS:
			return "C";
		case HEARTS:
			return "H";
		case SPADES:
			return "S";
		default:
			return "D";
		}
	}

	/**
	 * Gets a one- or two-character string representing the value
	 * 
	 * @return either "2" through "10" or "A", "J", "Q", or "K"
	 */
	private String getValueString() {
		switch (value) {
		case ACE:
			return "A";
		case JACK:
			return "J";
		case QUEEN:
			return "Q";
		case KING:
			return "K";
		default:
			return "" + value;
		}
	}

	/**
	 * Returns whether two cards have the same suit and value
	 * 
	 * @param other
	 *            the other object to be compared
	 * @return true if the other object is a card with the same suit and value
	 *         as this card
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Card))
			return false;
		if (this == other) {
			return true;
		}

		Card that = (Card) other;

		return this.suit == that.suit && this.value == that.value;

	}

	/**
	 * Returns a String representation of this card, by combining its value and
	 * suit (see getValueString() and getSuitString)
	 * 
	 * @return a 2- or 3-character representation of this card (such as "JD" for
	 *         the jack of diamonds, or "10H" for the 10 of hearts
	 */
	public String toString() {

		return getValueString() + getSuitString();
	}

	public static void main(String[] args) {
		
		System.out.println("Create and print all the diamonds: ");
		
		for (int i = Card.ACE; i <= Card.KING; i++) {
			Card c = new Card(i, Card.DIAMONDS);
			System.out.println(c + ", value: " + c.getValue() +
					", suit: " + c.getSuit());
		
		}
		
		System.out.println("Testing equals()");
		Card c1 = new Card(5, Card.CLUBS);
		Card c2 = new Card(5, Card.CLUBS);
		Card c3 = new Card(6, Card.CLUBS);
		System.out.println("both cards 5 of clubs: " + c1.equals(c2));
		System.out.println("5 and 6 of clubs: " + c1.equals(c3));
		System.out.println("5 of clubs and null: " + c1.equals(null));
		System.out.println("5 of clubs and a non-card: " + c1.equals("cat"));
		
	}
	
	
}
