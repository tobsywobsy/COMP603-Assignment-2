package blackjack;

public class Card {
    String value; // Value of the card
    String type; // Type of the card (e.g., Hearts, Diamonds)

    Card(String value, String type) { // Constructor for Card class
        this.value = value; // Initialize card value
        this.type = type; // Initialize card type
    }

    public String toString() { // Method to return a string representation of the card
        return value + "-" + type; // Concatenate value and type with a hyphen
    }

    public int getValue() { // Method to get the value of the card
        if ("AJQK".contains(value)) { // If the card is an Ace, Jack, Queen, or King
            if (value.equals("A")) { // If the card is an Ace
                return 11; // Ace value is 11
            }
            return 10; // Jack, Queen, and King value is 10
        }
        return Integer.parseInt(value); // Convert string value to integer for cards 2-10
    }

    public boolean isAce() { // Method to check if the card is an Ace
        return value.equals("A"); // Return true if the value is "A" (Ace)
    }

    public String getImagePath() { // Method to get the file path of the card image
        return "./cards/" + toString() + ".png"; // Return file path based on card value and type
    }
}
