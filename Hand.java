/**This class will be a set of cards. Going to have a value based on what the hand is, and a method for comparing two hands to one another to see which one is higher */
public class Hand {
    public static void main(String[] args) {
        //Testing the hand class
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
    //Attributes
    private Card[] hand = new Card[5];//each hand contains 5 cards
    private int handRank;//for comparing hands
    private int[] numberOfCards = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//for finding pairs/three-of-a-kind/four-of-a-kind
    /**Constructor with arguments. */
    public Hand(String[] initialHand) {
        for (int i = 0; i < hand.length; i++) {
            hand[i] = new Card(initialHand[i]);
        }
        countCards();//populates the numberOfCards array
        sortHand();//sorts the hand so the highest card is at index 0, and so on.
        setHandRank();//sets the rank of the hand. lower numbers represent higher ranked hands.
    }
    /**Method to set the value of handRank depending on the cards in the hand. Only used in the constructor.
     * Lower values of handRank denote a higher ranked hand (e.g. a Royal Flush is the best hand possible, so its handRank is 1.)
     */
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
                default://this case should never happen unless the program is fed something that isn't a properly formatted card
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

    /**Method to return the handRank.
     * 
     * @return handRank, a number from 1 to 46.
     */
    public int getHandRank() {
        return handRank;
    }
/**Compares two hands to see which one wins the round.
 * 
 * @param other the other Hand to compare this hand to
 * @return 1 if this hand is better than the other hand, -1 if this hand is worse than the other hand, or 0 if they are the same (rare, but possible)
 */
    public int compareHands(Hand other) {
        int comparison = 0;//variable to store the result of comparing the two hands
        if (this.getHandRank() < other.getHandRank()) {
            comparison = 1;
        } else if (this.getHandRank() > other.getHandRank()) {
            comparison = -1;
        } else {
            if(this.getHandRank() == 16) {//if both hands are a Full House (otherwise it would be handled by one of the first two statements)
                //the Full House with the higher three of a kind wins
                if(this.getThreeOfAKindValue() > other.getThreeOfAKindValue()) {
                    comparison = 1;
                } else if (this.getThreeOfAKindValue() < other.getThreeOfAKindValue()) {
                    comparison = -1;
                } else {
                    //if both three of the kinds are the same (which should be impossible with a standard deck of cards), you have to compare the pairs
                    if (this.getHighestPairValue() > other.getHighestPairValue()) {
                        comparison = 1;
                    } else if (this.getHighestPairValue() < other.getHighestPairValue()) {
                        comparison = -1;
                    } else {
                        comparison = 0;
                    }
                }

            } else if (this.getHandRank() == 32){//if both hands are a Two Pair
                //the hand with the highest pair wins
                if (this.getHighestPairValue() > other.getHighestPairValue()) {
                    comparison = 1;
                } else if (this.getHighestPairValue() < other.getHighestPairValue()) {
                    comparison = -1;
                //if both hands have the same highest pair (rare but possible), you have to compare the second pairs
                } else if (this.getSecondHightestPairValue() > other.getSecondHightestPairValue()) {
                    comparison = 1;
                } else if (this.getSecondHightestPairValue() < other.getSecondHightestPairValue()) {
                    comparison = -1;
                } else {//if both pairs in both hands are the same (rare but possible)
                    for (int i = 0; i < hand.length; i++) {
                        //comparing the remaining card that isn't one of the two pairs
                        if (this.getCardInHand(i).compareValue(other.getCardInHand(i)) == -1) {
                            comparison = -1;
                            break;
                        } else if (this.getCardInHand(i).compareValue(other.getCardInHand(i)) == 1) {
                            comparison = 1;
                            break;
                        }
                    }
                }
    
            //if both hands have the same rank and they aren't a full house or two pair, the hand with the highest card wins
            } else {
                /*comparing the cards in the hands to one another. Since the hands are sorted in the constructor
                the card at index 0 is the highest in each hand. If the first card in each hand is the same,
                moves on to the second card in each hand, etc.
                if cards in this hand are lower than the cards in the other hand*/
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
    /**Returns the card in this hand at the specified index.
     * 
     * @param index integer 0-4 representing a card in the hand
     * @return the Card object at the index
     */
    public Card getCardInHand(int index) {
        return this.hand[index];
    }
    /**Method to determine if the hand contains a Flush (all cards in the hand are the same suit).
     * 
     * @return true if the hand is a flush, false otherwise
     */
    public boolean isFlush() {
        boolean flush = false;
        if (hand[0].compareSuit(hand[1]) && hand[1].compareSuit(hand[2]) && hand[2].compareSuit(hand[3]) && hand[3].compareSuit(hand[4])) {
            flush = true;
        }
        return flush;
    }
    /**Method to find the number of pairs in the hand.
     * 
     * @return the number of pairs in the hand (1 or 2)
     */
    public int getNumberOfPairs() {
        int numberOfPairs = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 2) {
                numberOfPairs++;
            }
        }
        return numberOfPairs;
    }
    /**Method to find and return the value of the highest pair in the hand.
     * 
     * @return the value of the highest pair in the hand. 14 = Ace, 13 = King, ..., 2 = 2
     */
    public int getHighestPairValue() {
        int highestPairValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 2) {//the index of this array represents the value of the pair, so we are finding the highest(last) index with a pair
                highestPairValue = i;
            }
        }
        return highestPairValue;
    }
    /**Method to find and return the value of the second highest pair in the hand.
     * 
     * @return the value of the second highest pair in the hand. 14 = Ace, 13 = King, ..., 2 = 2
     */
    public int getSecondHightestPairValue() {
        int secondHightestPairValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 2) {//the index of this array represents the value of the pair, so we are finding the lowest(first) index with a pair
                secondHightestPairValue = i;
                break;//this is needed, otherwise we will find the highest pair value again
            }
        }
        return secondHightestPairValue;
    }
    /**Method to determine if the hand contains three of a kind.
     * 
     * @return true if the hand contains three of a kind, false otherwise
     */
    public boolean isThreeOfAKind() {
        boolean three = false;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 3) {//if there are three of any of the cards in the hand
                three = true;
            }
        }
        return three;
    }
    /**Method that returns the value of the three of a kind in the hand.
     * 
     * @return the value of the three of a kind in the hand
     */
    public int getThreeOfAKindValue() {
        int threeValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 3) {//the index of this array represents the value of the cards
                threeValue = i;
            }
        }
        return threeValue;
    }
    /**Method to determine if the hand contains a Four of a Kind.
     * 
     * @return true if the hand contains Four of a Kind, false otherwise
     */
    public boolean isFourOfAKind() {
        boolean four = false;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 4) {
                four = true;
            }
        }
        return four;
    }
    /**Method that returns the value of the four of a kind, or 0 if the hand doesn't have a four of a kind in it.
     * 
     * @return the value of the four of a kind, or 0 if the hand doesn't have a four of a kind in it
     */
    public int getFourOfAKindValue() {
        int fourValue = 0;
        for (int i = 0; i < numberOfCards.length; i++) {
            if (numberOfCards[i] == 3) {
                fourValue = i;
            }
        }
        return fourValue;
    }
    /**Method to determine if a hand is a Full House (three of a kind and a pair).
     * 
     * @return true if the hand is a Full House, false otherwise.
     */
    public boolean isFullHouse() {
        boolean fullHouse = false;
        if((this.getNumberOfPairs() != 0) && this.isThreeOfAKind()) {
            fullHouse = true;
        }
        return fullHouse;
    }
    /**Method to determine if a hand is a Straight (all 5 cards are in numerical order).
     * 
     * @return true if the hand is a Straight, false otherwise
     */
    public boolean isStraight() {
        boolean straight = false;
        for (int i = 1; i < hand.length; i++) {//starts at the second card in the hand so we don't get an index out of bounds error
            //note: the hand is sorted in the constructor, so the higest card is at index 0, and the lowest card is at index 4
            //if each card is 1 less than the card before it
            if ((hand[i - 1].getIntValue() - hand[i].getIntValue()) == 1) {
                straight = true;
            } else {
                straight = false;
                break;
            }
        }
        return straight;
    }
    /**Method to determine if a hand is a RoyalFlush (hand is a Flush, and a Straight, and the highest card is an Ace).
     * 
     * @return true if the hand is a Royal Flush, false otherwise
     */
    public boolean isRoyalFlush() {
        boolean royalFlush = false;
        if(this.isStraight() && this.isFlush() && (hand[0].getValue() == 'A')) {
            royalFlush = true;
        }
        return royalFlush;
    }
    /**Method that returns the array of the number of each card in the hand.
     * 
     * @return the array of integers that counts the number of cards in the hand
     */
    public int[] getNumberOfCards() {
        return numberOfCards;
    }
    /**Method that returns a String containing useful information about the Hand.
     * 
     * @return a String that says how many of each card are in the hand
     */
    public String toString() {
        String output = "";
        for (int i = 2; i < numberOfCards.length; i ++) {
            if (numberOfCards[i] != 0) {
                output = output + intToChar(i) + " : " + numberOfCards[i] + "; ";
            }
        }
        return output;
    }
    /**Method that prints a description of the hand to the console. */
    public void printHand() {
        for (int i = 0; i < hand.length; i ++) {
            System.out.print("" + hand[i].getValue() + hand[i].getSuit() + " ");
        }
    }
    /**Private method to sort the hand from highest card to lowest card. */
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
    /**Private method to count how many of each card are in the hand. */
    private void countCards() {
        for (int i = 0; i < hand.length; i++) {
            numberOfCards[hand[i].getIntValue()]++;
        }
    }
    /**Private method to returns the proper character given an integer. Converts a card value to a character.  */
    private static char intToChar(int value) {
        char card;
        switch (value) {
            case 14://Ace
            card = 'A';
            break;
            case 13://King
            card = 'K';
            break;
            case 12://Queen
            card = 'Q';
            break;
            case 11://Jack
            card = 'J';
            break;
            case 10://Ten
            card = 'T';
            break;
            case 9://9, 8, 7, ..., 2
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