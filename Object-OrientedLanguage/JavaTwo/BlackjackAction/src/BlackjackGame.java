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
		startGame();

	}

	public void startGame() {

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
		updateHandGraphics(playerPanel, player, false, false);

		dealer = new Hand();
		int cardForDealer = 2;
		for (int i = 0; i < cardForDealer; i++) {
			dealer.addCard(deck.deal());
		}
		updateHandGraphics(dealerPanel, dealer, true, false);

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

		ButtonClickListener listener = new ButtonClickListener();
		hit.addActionListener(listener);
		stand.addActionListener(listener);
		playAgain.addActionListener(listener);
		quit.addActionListener(listener);

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
			boolean isDealer, boolean isCompare) {

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

	/**
	 * Updates image displays when player hits
	 * 
	 * @param panel
	 *            the JPanel to be updated with
	 * @param hand
	 *            the cards on this hand
	 */
	public void updateHit(JPanel panel, Hand hand) {
		ImageIcon image;
		image = new ImageIcon("cardImages/"
				+ hand.getCard(hand.size() - 1).toString() + ".png");
		JLabel playerLabel = new JLabel();
		playerLabel.setIcon(image);
		panel.add(playerLabel);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Implements ActionListener to performe what happen when user
	 * click the buttons
	 * 
	 */
	private class ButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			// if the user click hit, add card to both player
			// and dealer, update the images, and update the score
			if (ae.getSource() == hit) {
				player.addCard(deck.deal());
				dealer.addCard(deck.deal());
				updateHit(playerPanel, player);
				updateHit(dealerPanel, dealer);
				repaint();
				int i =0;
				for(int j=1; j<dealer.size(); j++){
					i+=dealer.getTotal();
				}
				
				if(i>=17){
					updateHandGraphics(dealerPanel, dealer, true, true);
				}
				remove(playerPanel);

				// if the player lost, pop up message to say so
				if (player.isBusted())
					JOptionPane.showMessageDialog(null,
							"Sorry, you lost");

				// if the player get black jack,
				// pop up message to say so
				if (player.hasBlackjack() || player.getTotal()==21)
					JOptionPane.showMessageDialog(null, "Congratulations, you won");
				
				// if the dealer reach 17 points, and stand
				

			}
			// if player choose to stand, pop up display final score
			else if (ae.getSource() == stand) {
				JOptionPane.showMessageDialog(null, "You ended with "
						+ player.getTotal());
				repaint();
			}

			// if the player wants to play again
			else if (ae.getSource() == playAgain) {
				
			
				repaint();
				
			}

			// if player choose to quit, quit the game
			else
				System.exit(0);

			playerStatus
					.setText("Player Total: " + player.getTotal());
			repaint();
		}

	}

	public static void main(String[] args) {
		BlackjackGame bjGame = new BlackjackGame();
	}
}
