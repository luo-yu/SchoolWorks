/*
 * Name: Yu Luo
 * Instructor: Mr. Norman J. Krumpe
 * CSE 271, TA
 * Date: May 31, 2013
 * File: Card.java
 * Description: The definition of a Card class.
 *              Including a main method for testing
 *              the constructor and methods of Card class
 */

/**
 * Implements a single card with a card value and a card suit. The
 * card value and card suit each are represented by an int.
 * 
 * @author Yu Luo
 * 
 */
public class Card {

	private int value;
	private int suit;

	/**
	 * The int value represents card suit clubs
	 */
	public final static int CLUBS = 0;
	/**
	 * The int value represents card suit hearts
	 */

	public final static int HEARTS = 1;
	/**
	 * The int value represents card suit spades
	 */
	public final static int SPADES = 2;
	/**
	 * The int value represents card suit diamonds
	 */
	public final static int DIAMONDS = 3;

	/**
	 * The int value represent card value Ace
	 */
	public final static int ACE = 1;
	/**
	 * The int value represent card value Jack
	 */
	public final static int JACK = 11;
	/**
	 * The int value represent card value Queen
	 */
	public final static int QUEEN = 12;
	/**
	 * The int value represent card value King
	 */
	public final static int KING = 13;

	/**
	 * Constructs and initializes a card with specified value and
	 * suit.
	 * 
	 * @param value
	 *            represents a card value (e.g. 1 represents Ace)
	 * @param suit
	 *            represents a card suit (e.g. 0 represents Clubs)
	 * @throws IllegalArgumentException
	 *             if card value is not between 1 and 13 inclusive,
	 *             card suit value is not between 0 and 3 inclusive
	 */
	public Card(int value, int suit) {
		if (value < ACE || value > KING)
			throw new IllegalArgumentException("Illegal card value: "
					+ value);
		if (suit < CLUBS || suit > DIAMONDS)
			throw new IllegalArgumentException("Illegal card suit: "
					+ suit);

		this.value = value;
		this.suit = suit;
	}

	/**
	 * Gets the value of the card. (Jack, Queen, and King equals 10
	 * each. Ace equals 1. 2through 10 equal face value).
	 * 
	 * @return the value of a card
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Gets the suit of the card. The suit is represented by int value
	 * from 0 to 3 inclusive.
	 * 
	 * @return the suit of the card
	 */
	public int getSuit() {
		return this.suit;
	}

	/**
	 * Returns a string representation of the suit of the card(e.g.
	 * clubs are represented by "C").
	 * 
	 * @return a string representation of the suit of the card
	 */
	private String getSuitString() {
		String result = "";
		switch (this.suit) {
		case CLUBS:
			result = "C";
			break;
		case HEARTS:
			result = "H";
			break;
		case SPADES:
			result = "S";
			break;
		case DIAMONDS:
			result = "D";
			break;
		}

		return result;
	}

	/**
	 * Returns a string representation of the value of the card.
	 * 
	 * @return a string representation of the value of the card
	 */
	private String getValueString() {
		String result = "";

		switch (this.value) {
		case ACE:
			result = "A";
			break;
		case JACK:
			result = "J";
			break;
		case QUEEN:
			result = "Q";
			break;
		case KING:
			result = "K";
			break;
		default:
			result =""+ this.value;
			break;

		}

		return result;
	}

	/**
	 * Returns a string representation of the card, consisting of the
	 * value followed by the suit(e.g. KH).
	 * 
	 * @return a string representation of the card
	 */
	public String toString() {
		String result = this.getValueString() + ""
				+ this.getSuitString();
		return result;
	}

	/**
	 * Determines whether or not two cards are equal. Two cards are
	 * equal if they have the same suit and value.
	 * 
	 * @return true if the object to be compared is an instance of
	 *         Card and has the same suit and value; false otherwise
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Card))
			return false;
		return (this.suit == ((Card) obj).suit && 
			   this.value == ((Card) obj).value);
	}

	/*
	 * The main method tester for Card class. Tests all the
	 * definitions in the Card class.
	 */
	public static void main(String[] args) {
		Card c = new Card(1, 0);
		System.out.println("Card c to string: " + c.toString());
		System.out.println("get value of card c (Club Ace): "
				+ c.getValue());
		System.out.println("get suit of card c "
				+ "(Club Ace, 0 represent clubs): " + c.getSuit());
		System.out.println("get string representation of value "
				+ "of card c (Club Ace): " + c.getValueString());
		System.out.println("get string representation of suit "
				+ "       of card c (Club Ace, C represent clubs): "
				+ c.getSuitString());

		String s = "This is an String object";
		System.out.println("Card c equals String s = " + c.equals(s));
		Card c1 = new Card(1, 1);
		System.out.println("Card c equals card c1(Heart Ace) = "
				+ c.equals(c1));
		Card c2 = new Card(1, 0);
		System.out.println("Card c equals card c2(Club Ace) = "
				+ c.equals(c2));
		Card c3 = new Card(13, 3);
		Card c4 = new Card(13, 3);
		System.out.println("Card c3(Diamonds King)" + c3.toString()
				+ " equals Card c3(Diamonds king)" + c4.toString()
				+ " = " + c3.equals(c4));

	}

}
