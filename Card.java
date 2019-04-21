/** cards are given in the form ValueSuit, like AD for ace of diamonds
 * Suits: D = Diamonds, H = Hearts, C = Clubs, S = Spades
 * Values: A = Ace, K = King, Q = Queen, J = Jack, T = 10, 9, ..., 2
 */

public class Card {
    private char value;
    private int intValue;
    private char suit;

    Card(String card) {
        char[] array = card.toCharArray();
        setValue(array[0]);
        setIntValue(array[0]);
        setSuit(array[1]);
    }

    public void setValue(char newValue) {
        value = newValue;
    }

    public void setIntValue(char newValue) {
        intValue = valueToInt(newValue);
    }

    public void setSuit(char newSuit) {
        suit = newSuit;
    }

    public char getValue() {
        return value;
    }

    public int getIntValue() {
        return intValue;
    }

    public char getSuit() {
        return suit;
    }
    /** Method to compare the value of two cards; Returns -1 if the first card is lower, 0 if they are the same, or 1 if the first card is higher.
     * 
     * @param other a second card object to compare this card object to
     * @return -1 if this card is lower the the other card, 0 if they are the same, and 1 if this card is higher than the other card
     */
    public int compareValue(Card other) {
        int comparison; //will be -1 if this card is less than the other card, 0 if they are the same, or 1 if this card is greater than the other card
        if (this.getIntValue() < other.getIntValue()) {
            comparison = -1;
        } else if (this.getIntValue() == other.getIntValue()) {
            comparison = 0;
        } else {
            comparison = 1;
        }
        return comparison;
    }
    /** Method to compare the suits of two cards.
     * 
     * @param other another card object to compare this card object to
     * @return true if the two cards are the same suit, false otherwise.
     */
    public boolean compareSuit(Card other) {
        boolean sameSuit = false;
        if (this.getSuit() == other.getSuit()) {
            sameSuit = true;
        }
        return sameSuit;
    }
    public String toString() {
        return "" + value + suit;
    }
    /** Method to convert the char value of the cards to an integer value. */
    private int valueToInt(char value) {
        int intValue = 0;
        switch (value) {
            case 'A':
            intValue = 14;
            break;
            case 'K':
            intValue = 13;
            break;
            case 'Q':
            intValue = 12;
            break;
            case 'J':
            intValue = 11;
            break;
            case 'T':
            intValue = 10;
            break;
            case '9':
            case '8':
            case '7':
            case '6':
            case '5':
            case '4':
            case '3':
            case '2':
            intValue = Character.getNumericValue(value);
            break;
            default: intValue = -1;
        }
        return intValue;
    }

    public static void main(String[] args) {
        Card card1 = new Card("AD");
        Card card2 = new Card("2S");
        Card card3 = new Card("QH");
        Card card4 = new Card("7C");

        System.out.println("Card4 value: " + card4.getIntValue());
        System.out.println("Card2 value: " + card2.getIntValue());
        System.out.println("Card3 value: " + card3.getIntValue());
        System.out.println("Card1: " + card1.getValue() + " of " + card1.getSuit());
        System.out.println("Comparing Card1 (Ace of Diamonds) and Card2 (2 of Spades): " + card1.compareValue(card2));//expecting 1;
    }
}