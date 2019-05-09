/**
 * File: PokerGame.java
 * Author: Michael Sundt
 * Date: 9 MAY 2019
 * Purpose: Reads 1000 hands of a two player game of poker from a file named pokerhands.txt and compares them to eachother.
 *          Prints the number of time each player wins to the console.

In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
High Card: Highest value card.
One Pair: Two cards of the same value.
Two Pairs: Two different pairs.
Three of a Kind: Three cards of the same value.
Straight: All cards are consecutive values.
Flush: All cards of the same suit.
Full House: Three of a kind and a pair.
Four of a Kind: Four cards of the same value.
Straight Flush: All cards are consecutive values of same suit.
Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

If two players have the same ranked hands then the rank made up of the highest value wins;
for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks
tie, for example, both players have a pair of queens, then highest cards in each hand are
compared (see example 4 below); if the highest cards tie then the next highest cards are 
compared, and so on.

The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of 
the file contains ten cards (separated by a single space): the first five are Player 1's cards 
and the last five are Player 2's cards. You can assume that all hands are valid (no invalid 
characters or repeated cards), each player's hand is in no specific order, and in each hand 
there is a clear winner.

How many hands does Player 1 win? */
import java.io.FileNotFoundException;
import java.util.Scanner;
public class PokerGame {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "pokerhands.txt";//the hands of poker are in the file pokerhands.txt
        java.io.File file = new java.io.File(fileName);//create a File object representing pokerhands.txt
        Scanner input = new Scanner(file);//scanner to read from pokerhands.txt
        int handsize = 5;//a hand of poker contains 5 cards
        String[] player1 = new String[handsize];//player1's hand
        String[] player2 = new String[handsize];//player2's hand
        int count = 0;//to count the rounds of poker played
        int player1Wins = 0;//number of times player1 wins
        int player2Wins = 0;//number of times player2 wins
        int draws = 0;//number of draws
        do {
            count++;//increment number of games played
            String stringHands = input.nextLine();//each line of the file contains 10 cards. the first 5 are player 1's hand, the last 5 are player 2's hand
            // System.out.println("Hand " + count + ": " + stringHands);
            String[] playerHands = stringHands.split(" ");//create an array of Strings
            int player1Count = 0;
            int player2Count = 0;
            for(int i = 0; i < playerHands.length; i++) {
                if (i < 5) {//the first 5 cards are player 1's cards
                    player1[player1Count] = playerHands[i];
                    player1Count++;
                } else {//the last 5 cards are player 2's cards
                    player2[player2Count] = playerHands[i];
                    player2Count++;
                }
            }
            Hand player1Hand = new Hand(player1);//creating a Hand object that represents player 1's hand
            Hand player2Hand = new Hand(player2);//creating a Hand object that represents player 2's hand
            if (player1Hand.compareHands(player2Hand) == 1) {//if player 1's hand is better than player 2's hand
                player1Wins++;
            } else if (player1Hand.compareHands(player2Hand) == -1) {//if player 1's hand is worse than player 2's hand
                player2Wins++;
            } else {//if the two hands are equal
                draws++;
            }
    } while (input.hasNextLine());//while the end of the file has not been reached
    System.out.println("Hands of poker played: " + count);
    System.out.println("Hands player 1 won: " + player1Wins);
    System.out.println("Hands player 2 won: " + player2Wins);
    System.out.println("Draws: " + draws);

    input.close();
    }
}

