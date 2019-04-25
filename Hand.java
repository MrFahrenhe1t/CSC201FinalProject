/**This class will be a set of cards. Going to have a value based on what the hand is, and a method for comparing two hands to one another to see which one is higher */
public class Hand {
    public static void main(String[] args) {
        Hand testHand = new Hand("7C 3C 2H AC KC".split(" "));
        Hand otherHand = new Hand("2S 2S 7H 4S 6S".split(" "));
        testHand.printHand();
        System.out.println("");
        System.out.println(testHand.toString());
        System.out.println("Is the hand a flush? " + testHand.isFlush());
        System.out.println("Number of pairs: " + testHand.getNumberOfPairs());
        System.out.println("Three of a kind? " + testHand.isThreeOfAKind());
        System.out.println("Is this hand a full house? " + testHand.isFullHouse());
        System.out.println("Is this hand a straight? " + testHand.isStraight());
        System.out.println("Is this hand a Royal Flush? " + testHand.isRoyalFlush());
        System.out.println("Hand Rank: " + testHand.getHandRank());
        System.out.println("Which hand wins? " + testHand.compareHands(otherHand));
    }
    private Card[] hand = new Card[5];
    private int handRank;
    private int[] numberOfCards = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public Hand(String[] initialHand) {
        for (int i = 0; i < hand.length; i++) {
            hand[i] = new Card(initialHand[i]);
        }
        countCards();
        sortHand();
        setHandRank();
    }

    private void setHandRank() {//This isn't quite finished yet. Need to add an algorithm that makes it so a pair or 4's is a higher rank than a pair of 2's, etc.
        handRank = 0;
        if (isRoyalFlush()) {//Royal Flush
            handRank = 1;
        } else if (isStraight() && isFlush()) {//Straight Flush
            handRank = 2;
        } else if (isFourOfAKind()) {//Four of a Kind
            handRank = 3;
        } else if (isFullHouse()) {//Full House
            handRank = 4;
        } else if (isFlush()) {//Flush
            handRank = 5;
        } else if (isStraight()) {//Straight
            handRank = 6;
        } else if (isThreeOfAKind()) {//Three of a Kind
            handRank = 7;
        } else if (getNumberOfPairs() == 2) {//Two Pair
            handRank = 8;
        } else if (getNumberOfPairs() == 1) {//One Pair
            handRank = 9;
        } else {//High Card
            handRank = 10;
        }
    }

    public int getHandRank() {
        return handRank;
    }
/**Compares two hands to see which one wins the round.
 * 
 * @param other the other Hand to compare this hand to
 * @return 1 if this hand is better than the other hand, or -1 if this hand is worse than the other hand, or 0 if they are the same(which should be impossible)
 */
    public int compareHands(Hand other) {
        int comparison = 0;
        if (this.getHandRank() < other.getHandRank()) {
            comparison = 1;
        } else if (this.getHandRank() > other.getHandRank()) {
            comparison = -1;
        } else {
            for (int i = 0; i < hand.length; i++) {
                if (this.getCardInHand(i).compareValue(other.getCardInHand(i)) == -1) {
                    comparison = -1;
                    break;
                } else if (this.getCardInHand(i).compareValue(other.getCardInHand(i)) == 1) {
                    comparison = 1;
                    break;
                }
            }
        }
        return comparison;
    }

    public Card getCardInHand(int index) {
        return this.hand[index];
    }
    public boolean isFlush() {
        boolean flush = false;
        if (hand[0].compareSuit(hand[1]) && hand[1].compareSuit(hand[2]) && hand[2].compareSuit(hand[3]) && hand[3].compareSuit(hand[4])) {
            flush = true;
        }
        return flush;
    }

    public int getNumberOfPairs() {
        int numberOfPairs = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 2) {
                numberOfPairs++;
            }
        }
        return numberOfPairs;
    }

    public boolean isThreeOfAKind() {
        boolean three = false;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 3) {
                three = true;
            }
        }
        return three;
    }

    public boolean isFourOfAKind() {
        boolean four = false;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 4) {
                four = true;
            }
        }
        return four;
    }

    public boolean isFullHouse() {
        boolean fullHouse = false;
        if((this.getNumberOfPairs() != 0) && this.isThreeOfAKind()) {
            fullHouse = true;
        }
        return fullHouse;
    }

    public boolean isStraight() {
        boolean straight = false;
        for (int i = 1; i < hand.length; i++) {
            if ((hand[i - 1].getIntValue() - hand[i].getIntValue()) == 1) {
                straight = true;
            } else {
                straight = false;
                break;
            }
        }
        return straight;
    }
    
    public boolean isRoyalFlush() {
        boolean royalFlush = false;
        if(this.isStraight() && this.isFlush() && (hand[0].getValue() == 'A')) {
            royalFlush = true;
        }
        return royalFlush;
    }
    
    public int[] getNumberOfCards() {
        return numberOfCards;
    }

    public String toString() {
        String output = "";
        for (int i = 2; i < numberOfCards.length; i ++) {
            if (numberOfCards[i] != 0) {
                output = output + intToChar(i) + " : " + numberOfCards[i] + "; ";
            }
        }
        return output;
    }

    public void printHand() {
        for (int i = 0; i < hand.length; i ++) {
            System.out.print("" + hand[i].getValue() + hand[i].getSuit() + " ");
        }
    }
    /**Private function to sort the hand from highest card to lowest card. */
    private void sortHand() {
        Card currentMax;
        int currentMaxIndex;
        for (int i = 0; i < hand.length; i++) {
            currentMax = hand[i];
            currentMaxIndex = i;
            for (int j = i + 1; j < hand.length; j++) {
                if(currentMax.compareValue(hand[j]) < 0) {
                    currentMax = hand[j];
                    currentMaxIndex = j;
                }
            }
            if (currentMaxIndex != i) {
                hand[currentMaxIndex] = hand[i];
                hand[i] = currentMax;
            }
        }
    }
    //is there a way to use this array to differentiate between two different pairs (or three/four of a kind)?
    private void countCards() {
        for (int i = 0; i < hand.length; i++) {
            numberOfCards[hand[i].getIntValue()]++;
        }
    }

    private static char intToChar(int value) {
        char card;
        switch (value) {
            case 14:
            card = 'A';
            break;
            case 13:
            card = 'K';
            break;
            case 12:
            card = 'Q';
            break;
            case 11:
            card = 'J';
            break;
            case 10:
            card = 'T';
            break;
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            card = Character.forDigit(value, 10);
            break;
            default:
            card = '?';
        }
        return card;
    }
}