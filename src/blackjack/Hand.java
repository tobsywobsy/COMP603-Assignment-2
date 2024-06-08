package blackjack;

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> hand; // List to hold the cards in the hand
    int sum; // Total sum of the hand
    int aceCount; // Count of aces in the hand

    Hand() { // Constructor for Hand class
        hand = new ArrayList<Card>(); // Initialize the hand array list
        sum = 0; // Initialize the sum to 0
        aceCount = 0; // Initialize the ace count to 0
    }

    public void addCard(Card card) { // Method to add a card to the hand
        hand.add(card); // Add the card to the hand
        sum += card.getValue(); // Add the value of the card to the sum
        aceCount += card.isAce() ? 1 : 0; // If the card is an ace, increment the ace count
    }

    public int getSum() { // Method to get the sum of the hand
        return reduceAce(); // Return the reduced sum (if there are aces)
    }

    private int reduceAce() { // Method to reduce the sum by 10 for each ace if the sum exceeds 21
        while (sum > 21 && aceCount > 0) { // Loop until the sum is less than or equal to 21 or there are no more aces
            sum -= 10; // Reduce the sum by 10
            aceCount -= 1; // Decrement the ace count
        }
        return sum; // Return the reduced sum
    }
}
