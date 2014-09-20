/*
 * Name: Yu Luo
 * Instructor: Mr. Norman J. Krumpe
 * CSE 271, TA
 * Date: May 31, 2013
 * File: Hand.java
 * Description: The definition of a Hand class.
 *              Including a main method for testing
 *              the constructor and methods of Hand class
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Defines a hand representing all the cards on this hand.
 * 
 * @author Yu Luo
 * 
 */

public class Hand {
	private ArrayList<Card> cards;

	/**
	 * Constructs an empty hand representing 0 card on hand.
	 */
	public Hand() {
		cards = new ArrayList<Card>();
	}

	/**
	 * Adds a specified card to the hand.
	 * 
	 * @param card
	 *            a specified card
	 */
	public void addCard(Card card) {
		cards.add(card);
	}

	/**
	 * Returns a string representation of the hand, consisting all the
	 * cards on this hand, separated by spaces (e.g. 3H 5D 10D AC).
	 * 
	 * @return a string representation of this hand
	 */
	public String toString() {
		String result = "";

		for (int i = 0; i < cards.size(); i++) {
			result = result + cards.get(i) + " ";
		}

		return result;
	}

	/**
	 * Returns the number of cards on the hand.
	 * 
	 * @return the number of cards on the hand
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Determines whether the hand is empty.
	 * 
	 * @return true if the hand is empty; false otherwise.
	 */
	public boolean isEmpty() {
		boolean result = false;
		if (cards.size() == 0)
			result = true;
		return result;
	}

	/**
	 * Returns the soft total of the hand. Soft total is computed by
	 * counting one ace as 11 points.
	 * 
	 * @return the soft total of the hand
	 */
	public int getSoftTotal() {
		int softTotal = 0;
		int ace = 0;

		/*
		 * Count the number of aces on hand. if there is no ace, then
		 * the soft total is the hard total, simply add all the card
		 * values to get a total. if there is more than one ace on
		 * hand, then first count the hard total of all card. After
		 * that, add 10 in which means counting one ace worth 11
		 * points
		 */
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getValue() == 1)
				ace++;
		}

		if (ace == 0) {
			for (int j = 0; j < cards.size(); j++) {
				if (cards.get(j).getValue() == Card.JACK
						|| cards.get(j).getValue() == Card.QUEEN
						|| cards.get(j).getValue() == Card.KING) {
					softTotal += 10;
				} else {
					softTotal += cards.get(j).getValue();
				}

			}
		} else {
			for (int k = 0; k < cards.size(); k++) {
				if (cards.get(k).getValue() == Card.JACK
						|| cards.get(k).getValue() == Card.QUEEN
						|| cards.get(k).getValue() == Card.KING) {
					softTotal += 10;
				} else {
					softTotal += cards.get(k).getValue();
				}
			}
			softTotal += 10;

		}

		return softTotal;
	}

	/**
	 * Returns the hard total of the hand. Hard total is computed by
	 * counting all ace as 1 points.
	 * 
	 * @return the hard total of the hand
	 */
	public int getHardTotal() {
		int hardTotal = 0;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getValue() == Card.JACK
					|| cards.get(i).getValue() == Card.QUEEN
					|| cards.get(i).getValue() == Card.KING) {
				hardTotal += 10;
			} else {
				hardTotal += cards.get(i).getValue();
			}
			
		}
		return hardTotal;
	}

	/**
	 * Returns the total of the hand. The total is determined by
	 * comparing soft total and hard total, and choosing whichever
	 * total is better.
	 * 
	 * @return the total of the hand
	 */
	public int getTotal() {

		/*
		 * as long as the hand is not busted and the soft total is
		 * under 21, always choose soft total as the total. If the
		 * hand is either busted or the soft total excesses 21, then
		 * use the hard total as the total
		 */
		int total = 0;
		if (!isBusted()) {
			if (getSoftTotal() <= 21)
				total = getSoftTotal();
			else
				total = getHardTotal();
		} else
			total = getHardTotal();

		return total;
	}

	/**
	 * Determines whether the hand has a blackjack. The hand has a
	 * blackjack if there are 2 cards and total is 21.
	 * 
	 * @return true if the hand has a blackjack; false otherwise.
	 */
	public boolean hasBlackjack() {
		boolean result = false;
		if (cards.size() == 2 && getTotal() == 21)
			result = true;

		return result;
	}

	/**
	 * Determines whether the hand is busted. The hand is busted if
	 * the total exceeds 21.
	 * 
	 * @return true if the hand is busted; false otherwise.
	 */
	public boolean isBusted() {
		boolean busted = false;

		if (getSoftTotal() > 21 && getHardTotal() > 21)
			busted = true;
		return busted;
	}

	/*
	 * The main method tester for Hand class. Tests all the
	 * definitions in the Hand class.
	 */
	public static void main(String[] args) {

		/*
		 * Creates a deck and a hand. Use boolean win, lose, and
		 * string h(stands for hits) to control a while loop that
		 * allows user to interactively view her hand and her score,
		 * and decide whether to hit or stand.
		 */
		Deck deck = new Deck();
		Hand hand = new Hand();

		boolean win = false;
		boolean lose = false;
		String hitOrStand = "";
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to blackjack...dealing 2 cards:");

		deck.shuffle();

		/*
		 * add a card to this hand. This means to remove or deal a
		 * card from the deck
		 */
		hand.addCard(deck.deal());
		hand.addCard(deck.deal());
		System.out.println();

		System.out.println("Here are your cards: " + hand);

		/*
		 * If the first two cards' total is 21, then there is a
		 * blackjack, and the game ends automatically
		 */
		if (hand.hasBlackjack()) {
			System.out.println("BLACKJACK! YOU WIN!");
		}

		/*
		 * If the first two cards' total is not 21, show the hand and
		 * score, and user will decide whether to hit or stand
		 */
		else {
			System.out
					.print(hand.getTotal() + ", (h)it or (s)tand? ");
			hitOrStand = keyboard.next();
			System.out.println();
			while (hitOrStand.equalsIgnoreCase("h") && !win && !lose) {
				hand.addCard(deck.deal());
				System.out.println("Here are your cards: " + hand);
				if (hand.isBusted()) {
					System.out.println("BUSTED! YOU LOSE!");
					lose = true;
				} else if (hand.getTotal() == 21) {
					System.out.println("YOU GOT 21 POINTS!YOU WIN!");
					win = true;
				} else {
					System.out.print(hand.getTotal()
							+ ", (h)it or (s)tand? ");
					hitOrStand = keyboard.next();
					System.out.println();
				}

			}

			/*
			 * if the user choose to stand, show score, and end game
			 */
			if (hitOrStand.equals("s"))
				System.out.println("You ended with "
						+ hand.getTotal() + ".");
		}
		keyboard.close();

	}

}
