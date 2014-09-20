/*
 * Name: Yu Luo
 * Instructor: Mr. Norman J. Krumpe
 * CSE 271, TA
 * Date: June 09, 2013
 * File: BlackjackGame.java
 * Description: Design a GUI that shows 
 *              cards for a player and the dealer, 
 *              with some related labels and buttons
 */

/**
 * Implements a BlackjackGame class with all the definitions
 * that inherited from JFrame class.Utilizes JButtons, JLabels,
 * and JPanels with a GUI that shows cards for a player and 
 * a dealer, with related labels and buttons.
 * @author Yu Luo
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import card.Deck;
import card.Hand;

public class BlackjackGame extends JFrame {

	private Hand player;
	private Hand dealer;
	private Deck deck;
	private JPanel playerPanel;
	private JPanel dealerPanel;
	private JPanel controlPanel;
	private JLabel playerStatus;
	private JButton hit;
	private JButton stand;
	private JButton playAgain;
	private JButton quit;

	/**
	 * Constructs an GUI window with a 870 width and a 600 height. The
	 * GUI displays both player and dealer's hand. The dealer's first
	 * card is face down. The GUI also displays a player's current
	 * score, and displays buttons for a player to choose for hitting,
	 * standing, playing again, or quitting.
	 */
	public BlackjackGame() {
		super();
		this.setBounds(0, 0, 870, 600);
		this.setLayout(new GridLayout(5, 1));

		/*
		 * displays a label that indicates player's hand
		 */
		JPanel playerInforPanel = new JPanel(new BorderLayout());
		playerInforPanel.add(new JLabel("Player's Hand"),
				BorderLayout.SOUTH);
		/*
		 * displays a label that indicates dealer's hand
		 */
		JPanel dealerInforPanel = new JPanel(new BorderLayout());
		dealerInforPanel.add(new JLabel("Dealer's hand"),
				BorderLayout.SOUTH);

		/*
		 * displays player and dealer's card flow to the left
		 */
		playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dealerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		/*
		 * add four JPanels to the JFrame in order
		 */
		this.add(playerInforPanel);
		this.add(playerPanel);
		this.add(dealerInforPanel);
		this.add(dealerPanel);

		/*
		 * update cards calling the method updateHandGraphics
		 */
		deck = new Deck();
		deck.shuffle();
		player = new Hand();
		
		int cardForPlayer = 2;
		for (int i = 0; i < cardForPlayer; i++) {
			player.addCard(deck.deal());
		}
		updateHandGraphics(playerPanel, player, false);

		dealer = new Hand();
		int cardForDealer = 2;
		for (int i = 0; i < cardForDealer; i++) {
			dealer.addCard(deck.deal());
		}
		updateHandGraphics(dealerPanel, dealer, true);

		/*
		 * add score and control to controlPanel, and add controlPanel
		 * to the JFrame
		 */
		controlPanel = new JPanel(new BorderLayout());
		JPanel score = new JPanel();
		JPanel control = new JPanel();
		controlPanel.add(score, BorderLayout.NORTH);
		controlPanel.add(control, BorderLayout.SOUTH);
		playerStatus = new JLabel("Player Total: "
				+ player.getTotal());

		score.add(playerStatus);
		hit = new JButton("Hit");
		stand = new JButton("Stand");
		playAgain = new JButton("Play again");
		quit = new JButton("Quit");
		control.add(hit);
		control.add(stand);
		control.add(playAgain);
		control.add(quit);

		this.add(controlPanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

	}

	/**
	 * Updates and displays graphic of the hand in the specified
	 * JPanel.
	 * 
	 * @param panel
	 *            a specified JPanel
	 * @param hand
	 *            a specified hand
	 * @param faceDown
	 *            a specified boolean variable
	 */
	public void updateHandGraphics(JPanel panel, Hand hand,
			boolean isDealer) {

		/*
		 * loop through a specified hand of card,if it's dealer's
		 * hand, the first card is faced-down,otherwise, display the
		 * card
		 */
		for (int i = 0; i < hand.size(); i++) {
			ImageIcon image;
			if (isDealer && i == 0) {
				image = new ImageIcon("cardImages/BACKREDTALL.png");
			} else {

				image = new ImageIcon("cardImages/"
						+ hand.getCard(i).toString() + ".png");
			}
			JLabel playerLabel = new JLabel();
			playerLabel.setIcon(image);
			panel.add(playerLabel);
		}

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		BlackjackGame bjGame = new BlackjackGame();
	}
}
