package blackjack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    JButton hitButton = new JButton("Hit"); // Button for player to hit
    JButton standButton = new JButton("Stand"); // Button for player to stand
    JButton resetButton = new JButton("Reset"); // Button to reset the game
    JButton viewLeaderboardsButton = new JButton("View Leaderboards"); // Button to view leaderboards

    ButtonPanel(BlackJackGame game) { // Constructor for ButtonPanel class
        hitButton.setFocusable(false); // Ensure button does not gain focus when clicked
        add(hitButton); // Add hit button to panel
        standButton.setFocusable(false); // Ensure button does not gain focus when clicked
        add(standButton); // Add stand button to panel
        resetButton.setFocusable(false); // Ensure button does not gain focus when clicked
        add(resetButton); // Add reset button to panel
        viewLeaderboardsButton.setFocusable(false); // Ensure button does not gain focus when clicked
        add(viewLeaderboardsButton); // Add view leaderboards button to panel

        hitButton.addActionListener(new ActionListener() { // Action listener for hit button
            public void actionPerformed(ActionEvent e) {
                Card card = game.getDeck().drawCard(); // Draw a card
                game.getPlayerHand().addCard(card); // Add the card to player's hand
                if (game.getPlayerHand().getSum() > 21) { // If player busts
                    hitButton.setEnabled(false); // Disable hit button
                    game.playerLose(); // Player loses
                }
                game.getGamePanel().repaint(); // Repaint the game panel
            }
        });

        standButton.addActionListener(new ActionListener() { // Action listener for stand button
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false); // Disable hit button
                standButton.setEnabled(false); // Disable stand button

                while (game.getDealerHand().getSum() < 17) { // Dealer hits until hand value is 17 or more
                    Card card = game.getDeck().drawCard(); // Draw a card for the dealer
                    game.getDealerHand().addCard(card); // Add the card to dealer's hand
                }

                if (game.getDealerHand().getSum() > 21 || // If dealer busts or player's hand is greater
                        game.getPlayerHand().getSum() > game.getDealerHand().getSum()) {
                    game.playerWin(); // Player wins
                } else {
                    game.playerLose(); // Player loses
                }

                game.getGamePanel().repaint(); // Repaint the game panel
            }
        });

        resetButton.addActionListener(new ActionListener() { // Action listener for reset button
            public void actionPerformed(ActionEvent e) {
                game.resetGame(); // Reset the game
                hitButton.setEnabled(true); // Enable hit button
                standButton.setEnabled(true); // Enable stand button
                game.getGamePanel().repaint(); // Repaint the game panel
            }
        });

        viewLeaderboardsButton.addActionListener(new ActionListener() { // Action listener for view leaderboards button
            public void actionPerformed(ActionEvent e) {
                showLeaderboards(); // Show the leaderboards
            }
        });
    }

    private void showLeaderboards() { // Method to show leaderboards
        String leaderboardData = DBManager.retrieveLeaderboardData(); // Retrieve leaderboards data from database

        JOptionPane.showMessageDialog(null, leaderboardData, "Leaderboards", JOptionPane.PLAIN_MESSAGE); // Show leaderboards in a message dialog
    }
}
