package blackjack;

public class BlackJackGame {
    private Deck deck; // Instance of the Deck class
    private Card hiddenCard; // Instance of the Card class
    private Hand dealerHand; // Instance of the Hand class representing the dealer's hand
    private Hand playerHand; // Instance of the Hand class representing the player's hand
    private GamePanel gamePanel; // Instance of the GamePanel class for displaying the game
    private ButtonPanel buttonPanel; // Instance of the ButtonPanel class for game controls
    private String playerName; // Name of the player
    private DBManager dbManager; // Instance of the DBManager class for database interaction

    public BlackJackGame(String playerName, DBManager dbManager) { // Constructor for BlackJackGame class
        this.playerName = playerName; // Initialize player name
        this.dbManager = dbManager; // Initialize DBManager
        deck = new Deck(); // Initialize deck
        dealerHand = new Hand(); // Initialize dealer's hand
        playerHand = new Hand(); // Initialize player's hand
    }

    public void startGame() { // Method to start the game
        deck.buildDeck(); // Build the deck
        deck.shuffleDeck(); // Shuffle the deck

        hiddenCard = deck.drawCard(); // Draw a card for the dealer's hidden card
        dealerHand.addCard(hiddenCard); // Add the hidden card to the dealer's hand

        dealerHand.addCard(deck.drawCard()); // Draw a card for the dealer's visible card

        playerHand.addCard(deck.drawCard()); // Draw two cards for the player
        playerHand.addCard(deck.drawCard());
    }

    public void resetGame() { // Method to reset the game
        dealerHand = new Hand(); // Reset dealer's hand
        playerHand = new Hand(); // Reset player's hand
        startGame(); // Start a new game
    }

    public void playerLose() { // Method called when player loses
        buttonPanel.standButton.setEnabled(false); // Disable the Stand button
        gamePanel.repaint(); // Repaint the game panel
    }

    public void playerWin() { // Method called when player wins
        dbManager.incrementWins(playerName); // Increment player's win count in the database
        buttonPanel.standButton.setEnabled(false); // Disable the Stand button
        gamePanel.repaint(); // Repaint the game panel
    }

    public Deck getDeck() { // Method to get the deck
        return deck;
    }

    public Card getHiddenCard() { // Method to get the hidden card
        return hiddenCard;
    }

    public Hand getDealerHand() { // Method to get the dealer's hand
        return dealerHand;
    }

    public Hand getPlayerHand() { // Method to get the player's hand
        return playerHand;
    }

    public GamePanel getGamePanel() { // Method to get the game panel
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) { // Method to set the game panel
        this.gamePanel = gamePanel;
    }

    public void setButtonPanel(ButtonPanel buttonPanel) { // Method to set the button panel
        this.buttonPanel = buttonPanel;
    }

    public boolean isStandButtonEnabled() { // Method to check if the Stand button is enabled
        return buttonPanel.standButton.isEnabled();
    }
}
