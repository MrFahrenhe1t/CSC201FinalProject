/**This class will be a set of cards. Going to have a value based on what the hand is, and a method for comparing two hands to one another to see which one is higher */
public class Hand {
    public static void main(String[] args) {
        Hand testHand = new Hand("5C AD 5D AC AS".split(" "));
        System.out.println(testHand.toString());
        System.out.println("Is the hand a flush? " + testHand.isFlush());
        System.out.println("Number of pairs: " + testHand.getNumberOfPairs());
        System.out.println("Three of a kind? " + testHand.isThreeOfAKind());
        System.out.println("Is this hand a full house? " + testHand.isFullHouse());
    }
    private Card[] hand = new Card[5];
    private int handRank;
    private int[] numberOfCards = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public Hand(String[] initialHand) {
        for (int i = 0; i < hand.length; i++) {
            hand[i] = new Card(initialHand[i]);
        }
        countCards();
    }

    // private void setHandRank() {
        //the idea here to to analyze the hand and give it a rank based on what it is. Lower numbers will denote a better hand

    // }
    private void countCards() {
        for (int i = 0; i < hand.length; i++) {
            numberOfCards[hand[i].getIntValue()]++;
        }
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
    public boolean isFullHouse() {
        boolean fullHouse = false;
        if((this.getNumberOfPairs() != 0) && this.isThreeOfAKind()) {
            fullHouse = true;
        }
        return fullHouse;
    }
    
    public int[] getNumberOfCards() {
        return numberOfCards;
    }

    public String toString() {
        String output = "";
        for (int i = 2; i < numberOfCards.length; i ++) {
            if (numberOfCards[i] != 0) {
                output = output + intToChar(i) + " : " + numberOfCards[i] + ". ";
            }
        }
        return output;
    }
    /*This is where I left off...*/
    // private void sortHand() {
    //     Card[] tempArray = new Card[5];
    //     Card temp = hand[0];
    //     for (int i = 0; i < hand.length; i++) {
    //         if (hand[i].getIntValue() >)
    //     }
    // }

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
    // private boolean hasPair() {
    //     boolean pair = false;
    //     for(int i = 1; i < hand.length; i++) {
    //         if((hand[0] == hand[i]) && (i != 0)) {
    //             pair = true;
    //         } else if ((hand[1] == hand[i]) && (i != 1)) {
    //             pair = true;
    //         } else if ((hand[2] == hand[i]) && (i != 2)) {
    //             pair = true;
    //         } else if ((hand[3] == hand[i]) && (i != 3)) {
    //             pair = true;
    //         } else {
    //             pair = false;
    //         }
    //     }
    //     return pair;
    // }
}