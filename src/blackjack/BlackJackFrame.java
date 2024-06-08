package blackjack;

import javax.swing.*;
import java.awt.*;

public class BlackJackFrame extends JFrame {
    BlackJackGame game;
    GamePanel gamePanel;
    ButtonPanel buttonPanel;
    DBManager dbManager;

    BlackJackFrame() { // Constructor for BlackJackFrame class
        dbManager = new DBManager(); // Initialize DBManager
        String playerName = JOptionPane.showInputDialog(this, "Enter your name:"); // Prompt user for name
        dbManager.addPlayer(playerName); // Add player name to database

        game = new BlackJackGame(playerName, dbManager); // Initialize BlackJackGame with player name and DBManager

        setTitle("Black Jack"); // Set frame title
        setSize(700, 600); // Set frame size
        setLocationRelativeTo(null); // Center frame on screen
        setResizable(false); // Disable frame resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation

        gamePanel = new GamePanel(game); // Initialize GamePanel with BlackJackGame
        buttonPanel = new ButtonPanel(game); // Initialize ButtonPanel with BlackJackGame
        game.setGamePanel(gamePanel); // Set GamePanel for BlackJackGame
        game.setButtonPanel(buttonPanel); // Set ButtonPanel for BlackJackGame

        add(gamePanel); // Add GamePanel to frame
        add(buttonPanel, BorderLayout.SOUTH); // Add ButtonPanel to SOUTH region of frame

        game.startGame(); // Start the game
        setVisible(true); // Make frame visible
    }
}
