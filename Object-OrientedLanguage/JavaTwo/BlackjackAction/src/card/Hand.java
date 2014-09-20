package card;
/**
 * Implements a hand of cards in the game of blackjack,
 * with methods for determining the value of the hand,
 * as well as for determining whether the hand is busted
 * or has blackjack.
 * 
 * @author Norm Krumpe
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Hand {
	private ArrayList<Card> cards;

	/*
	 * Play a simple game of blackjack
	 */
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		Hand hand = new Hand();
		Deck deck = new Deck();
		deck.shuffle();
		char choice;

		System.out.println("Welcome to blackjack...dealing 2 cards:\n");
		hand.addCard(deck.deal());
		hand.addCard(deck.deal());

		do {
			System.out.println("Here are your cards: " + hand);
			if (hand.hasBlackjack() || hand.isBusted())	break;
			
			System.out.print(hand.getTotal() + ", (h)it or (s)tand? ");
			choice = kb.next().charAt(0);
			System.out.println();

			if (choice == 'h') {
				hand.addCard(deck.deal());
			}
		} while (hand.getTotal() != 21 && choice == 'h');

		showGameStatus(hand);
	}

	/*
	 * Game has ended...show status
	 */
	private static void showGameStatus(Hand hand) {
		// Game has ended, either as blackjack, bust, or hold
		if (hand.hasBlackjack()) {
			System.out.println("BLACKJACK!  YOU WIN!");
		} else if (hand.isBusted()) {
			System.out.println("BUSTED!  YOU LOSE!");
		} else {
			System.out.println("You ended with " + hand.getTotal() + ".");
		}
	}

	/**
	 * Constructs an empty hand with no cards in it.
	 */
	public Hand() {
		cards = new ArrayList<Card>();
	}

	/**
	 * Adds a card to this hand.
	 * @param c the card to be added.
	 */
	public void addCard(Card c) {
		cards.add(c);
	}

	/**
	 * Gets the number of cards in this hand.
	 * @return a count of the number of cards
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Gets the specified card.  
	 * @param i the index of the desired card
	 * @return the specified card
	 */
	public Card getCard(int i) {
		return new Card(cards.get(i));		
	}

	/**
	 * Returns whether the hand is empty (containing no cards).
	 * @return true of the hand has no cards remaining, and false
	 * otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns whether the card contains an ace.  Useful in
	 * determining the soft/hard totals
	 * @return true if the hand contains an ace, and false otherwise
	 */
	private boolean holdingAce() {
		for (Card c : cards) {
			if (c.getValue() == Card.ACE)
				return true;
		}
		return false;
	}

	/**
	 * Get the total of the hand if all aces are counted
	 * as 1.
	 * @return the hand total when all aces are counted as 1 point
	 */
	public int getHardTotal() {
		int total = 0;

		for (Card c : cards) {
			int value = c.getValue();
			if (value == Card.ACE)
				total += 1;
			else if (value >= 10 && value <= Card.KING) {
				total += 10;
			} else {
				total += value;
			}
		}

		return total;
	}

	/**
	 * Get the total of the hand, counting one ace (if there is one)
	 * as 11 points.
	 * @return the hand total when one ace (if there is one) is counted as 11 points.
	 */
	public int getSoftTotal() {
		return holdingAce() ? getHardTotal() + 10 : getHardTotal();
	}

	/**
	 * Gets the best possible score of the hand.  
	 * @return the soft total if it is less than or equal to 21.  Otherwise returns
	 * the hard total.
	 */
	public int getTotal() {
		return getSoftTotal() <= 21 ? getSoftTotal() : getHardTotal();
	}

	/**
	 * Determines whether the player has blackjack.
	 * @return true if the player has a 2-card total of 21, and false otherwise.
	 */
	public boolean hasBlackjack() {
		return getTotal() == 21 && size() == 2;
	}

	/**
	 * Determines whether the player has busted.
	 * @return true if the player's hard total exceeds 21
	 */
	public boolean isBusted() {
		return getHardTotal() > 21;
	}

	/**
	 * Gets a String representation of this hand
	 * @return a space-separated list of cards in the hand
	 */
	public String toString() {
		String result = "";
		for (Card c : cards) {
			result += c + " ";
		}

		return result.trim();
	}

}
