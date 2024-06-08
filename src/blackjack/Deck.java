package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck; // List to hold the deck of cards
    Random random = new Random(); // Random number generator for shuffling

    Deck() { // Constructor for Deck class
        buildDeck(); // Build the deck
        shuffleDeck(); // Shuffle the deck
    }

    public void buildDeck() { // Method to build the deck of cards
        deck = new ArrayList<Card>(); // Initialize the deck array list
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // Array of card values
        String[] types = {"C", "D", "H", "S"}; // Array of card types (Clubs, Diamonds, Hearts, Spades)

        for (String type : types) { // Loop through each card type
            for (String value : values) { // Loop through each card value
                Card card = new Card(value, type); // Create a new card with the current value and type
                deck.add(card); // Add the card to the deck
            }
        }
    }

    public void shuffleDeck() { // Method to shuffle the deck of cards
        for (int i = 0; i < deck.size(); i++) { // Iterate through each card in the deck
            int j = random.nextInt(deck.size()); // Generate a random index within the deck size
            Card currCard = deck.get(i); // Get the current card
            Card randomCard = deck.get(j); // Get a randomly selected card
            deck.set(i, randomCard); // Replace the current card with the randomly selected card
            deck.set(j, currCard); // Replace the randomly selected card with the current card
        }
    }

    public Card drawCard() { // Method to draw a card from the deck
        return deck.remove(deck.size() - 1); // Remove and return the last card from the deck (top of the deck)
    }
}
