/**This class will be a set of cards. Going to have a value based on what the hand is, and a method for comparing two hands to one another to see which one is higher */
public class Hand {
    public static void main(String[] args) {
        Hand testHand = new Hand("AC AC 2H 2C 2C".split(" "));
        Hand otherHand = new Hand("2S 2S 3H 3S 3S".split(" "));
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

    private void setHandRank() {
        handRank = 0;
        if (isRoyalFlush()) {//Royal Flush
            handRank = 1;
        } else if (isStraight() && isFlush()) {//Straight Flush
            handRank = 2;
        } else if (isFourOfAKind()) {//Four of a Kind
            switch (this.getFourOfAKindValue()) {
                case 14://four Aces
                handRank = 3;
                break;
                case 13://Four Kings
                handRank = 4;
                break;
                case 12://Four Queens
                handRank = 5;
                break;
                case 11://Four Jacks
                handRank = 6;
                break;
                case 10://Four 10's
                handRank = 7;
                break;
                case 9://Four 9's
                handRank = 8;
                break;
                case 8://Four 8's
                handRank = 9;
                break;
                case 7://Four 7's
                handRank = 10;
                break;
                case 6://Four 6's
                handRank = 11;
                break;
                case 5://Four 5's
                handRank = 12;
                break;
                case 4://Four 4's
                handRank = 13;
                break;
                case 3://Four 3's
                handRank = 14;
                break;
                case 2://Four 2's
                handRank = 15;
                break;
                default:
                handRank = 1000000;
            }
        } else if (isFullHouse()) {//Full House
            handRank = 16;
        } else if (isFlush()) {//Flush
            handRank = 17;
        } else if (isStraight()) {//Straight
            handRank = 18;
        } else if (isThreeOfAKind()) {//Three of a Kind
            switch (this.getThreeOfAKindValue()) {
                case 14://Three Aces
                handRank = 19;
                break;
                case 13://Three Kings
                handRank = 20;
                break;
                case 12://Three Queens
                handRank = 21;
                break;
                case 11://Three Jacks
                handRank = 22;
                break;
                case 10://Three 10's
                handRank = 23;
                break;
                case 9://Three 9's
                handRank = 24;
                break;
                case 8://Three 8's
                handRank = 25;
                break;
                case 7://Three 7's
                handRank = 26;
                break;
                case 6://Three 6's
                handRank = 27;
                break;
                case 5://Three 5's
                handRank = 28;
                break;
                case 4://Three 4's
                handRank = 29;
                break;
                case 3://Three 3's
                handRank = 30;
                break;
                case 2://Three 2's
                handRank = 31;
                break;
                default:
                handRank = 1000000;
            }
        } else if (getNumberOfPairs() == 2) {//Two Pair
            handRank = 32;
        } else if (getNumberOfPairs() == 1) {//Pair
            switch (this.getHighestPairValue()) {
                case 14://Pair of Aces
                handRank = 33;
                break;
                case 13://Pair of Kings
                handRank = 34;
                break;
                case 12://Pair of Queens
                handRank = 35;
                break;
                case 11://Pair of Jacks
                handRank = 36;
                break;
                case 10://Pair of10's
                handRank = 37;
                break;
                case 9://Pair of 9's
                handRank = 38;
                break;
                case 8://Pair of 8's
                handRank = 39;
                break;
                case 7://Pair of 7's
                handRank = 40;
                break;
                case 6://Pair of 6's
                handRank = 41;
                break;
                case 5://Pair of 5's
                handRank = 42;
                break;
                case 4://Pair of 4's
                handRank = 43;
                break;
                case 3://Pair of 3's
                handRank = 44;
                break;
                case 2://Pair of 2's
                handRank = 45;
                break;
                default:
                handRank = 1000000;
            }

        } else {//High Card
            handRank = 46;
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
            if(this.getHandRank() == 16) {
                if(this.getThreeOfAKindValue() > other.getThreeOfAKindValue()) {
                    comparison = 1;
                } else if (this.getThreeOfAKindValue() < other.getThreeOfAKindValue()) {
                    comparison = -1;
                } else {
                    if (this.getHighestPairValue() > other.getHighestPairValue()) {
                        comparison = 1;
                    } else if (this.getHighestPairValue() < other.getHighestPairValue()) {
                        comparison = -1;
                    } else {
                        comparison = 0;
                    }
                }

            } else if (this.getHandRank() == 32){
                if (this.getHighestPairValue() > other.getHighestPairValue()) {
                    comparison = 1;
                } else if (this.getHighestPairValue() < other.getHighestPairValue()) {
                    comparison = -1;
                } else if (this.getSecondHightestPairValue() > other.getSecondHightestPairValue()) {
                    comparison = 1;
                } else if (this.getSecondHightestPairValue() < other.getSecondHightestPairValue()) {
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

    public int getHighestPairValue() {
        int highestPairValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 2) {
                highestPairValue = i;
            }
        }
        return highestPairValue;
    }

    public int getSecondHightestPairValue() {
        int secondHightestPairValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 2) {
                secondHightestPairValue = i;
                break;
            }
        }
        return secondHightestPairValue;
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

    public int getThreeOfAKindValue() {
        int threeValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 3) {
                threeValue = i;
            }
        }
        return threeValue;
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

    public int getFourOfAKindValue() {
        int fourValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 3) {
                fourValue = i;
            }
        }
        return fourValue;
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