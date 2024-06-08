package blackjack;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private BlackJackGame game; // Reference to the current blackjack game
    private int cardWidth = 110; // Width of a card image
    private int cardHeight = 154; // Height of a card image

    GamePanel(BlackJackGame game) { // Constructor for GamePanel class
        this.game = game; // Initialize the game reference
        setBackground(new Color(53, 101, 77)); // Set background color of the panel
    }

    @Override
    protected void paintComponent(Graphics g) { // Method to paint the component (draw the game panel)
        super.paintComponent(g); // Call the superclass method to paint the component

        try {
            // Draw hidden card for dealer
            Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage(); // Get image for hidden card
            if (!game.isStandButtonEnabled()) { // If stand button is not enabled (show hidden card)
                hiddenCardImg = new ImageIcon(getClass().getResource(game.getHiddenCard().getImagePath())).getImage(); // Get image for hidden card
            }
            g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null); // Draw hidden card image

            // Draw rest of dealer's hand
            for (int i = 1; i < game.getDealerHand().hand.size(); i++) { // Iterate through dealer's hand cards (excluding hidden card)
                Card card = game.getDealerHand().hand.get(i); // Get card at current index
                Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage(); // Get image for the card
                g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5) * (i - 1), 20, cardWidth, cardHeight, null); // Draw card image
            }

            // Draw player's hand
            for (int i = 0; i < game.getPlayerHand().hand.size(); i++) { // Iterate through player's hand cards
                Card card = game.getPlayerHand().hand.get(i); // Get card at current index
                Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage(); // Get image for the card
                g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null); // Draw card image
            }

            if (!game.isStandButtonEnabled()) { // If stand button is not enabled (end of game)
                int dealerSum = game.getDealerHand().getSum(); // Get sum of dealer's hand
                int playerSum = game.getPlayerHand().getSum(); // Get sum of player's hand

                String message = ""; // Initialize message string
                if (playerSum > 21) { // If player busts
                    message = "You Lose!"; // Set message to "You Lose!"
                } else if (dealerSum > 21) { // If dealer busts
                    message = "You Win!"; // Set message to "You Win!"
                } else if (playerSum == dealerSum) { // If it's a tie
                    message = "Tie!"; // Set message to "Tie!"
                } else if (playerSum > dealerSum) { // If player's hand value is greater than dealer's
                    message = "You Win!"; // Set message to "You Win!"
                } else if (playerSum < dealerSum) { // If dealer's hand value is greater than player's
                    message = "You Lose!"; // Set message to "You Lose!"
                }

                g.setFont(new Font("Arial", Font.PLAIN, 30)); // Set font for message
                g.setColor(Color.white); // Set color for message

                int x = getWidth() / 2 - g.getFontMetrics().stringWidth(message) / 2; // Calculate x-coordinate for message
                int y = getHeight() / 2; // Calculate y-coordinate for message
                g.drawString(message, x, y); // Draw message on panel
            }
        } catch (Exception e) { // Catch any exceptions
            e.printStackTrace(); // Print stack trace for exception
        }
    }
}
