package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;

public class Blackjack extends CardGame{

    private BlackjackPlayer[] players;
    private int rounds;
    private int roundsWon;

    public Blackjack() {
        super("blackjack");
        players = new BlackjackPlayer[2];
        roundsWon = 0;
    }

    @Override
    public void doGame(BufferedReader br) throws IOException {
        System.out.println("Welcome to "+getName());
        setUp(br);
        for (int i = 0; i < rounds; i++) {
            doRound(br);
        }
        System.out.println("You won "+roundsWon+" out of "+rounds+" rounds.");
    }

    private void doRound(BufferedReader br){
        reset();
        shuffleDeck();
        players[0].resetHand();
        players[1].resetHand();
        dealFaceUp(players[0]);
        Input.wait(br);
        dealFaceUp(players[1]);
        Input.wait(br);
        dealFaceUp(players[0]);
        Input.wait(br);
        Card faceDown = dealCard();
        System.out.println(players[1].getName() + " card:?");
        System.out.print("Hand: ? ");
        players[1].printHand();
        System.out.print("Value: ? + " + players[1].handValue());
        System.out.println();
        boolean stand = false;
        boolean bust = false;
        while (!stand){
            System.out.println("Hit or Stand?");
            stand = Input.hitOrStand(br);
            if (!stand){
                dealFaceUp(players[0]);
                if (players[0].handValue() > 21){
                    bust = true;
                    stand = true;
                }
            }
        }
        Input.wait(br);
        dealFaceUp(players[1],faceDown);
        Input.wait(br);
        while (players[1].handValue() < 17){
            dealFaceUp(players[1]);
            Input.wait(br);
        }
        if (bust && players[1].handValue() > 21){
            System.out.println("Both players bust, no-one wins.");
        } else if (bust) {
            System.out.println(players[0].getName()+" bust, "+players[1].getName()+" wins");
        } else if (players[1].handValue() > 21) {
            System.out.println(players[1].getName()+" bust, "+players[0].getName()+" wins");
            roundsWon++;
        } else if (players[0].handValue() > players[1].handValue()) {
            System.out.println(players[0].getName()+" "+players[0].handValue()+" vs "+players[1].getName()+" "+players[1].handValue());
            System.out.println(players[0].getName()+" wins");
            roundsWon++;
        } else if (players[0].handValue() < players[1].handValue()){
            System.out.println(players[0].getName()+" "+players[0].handValue()+" vs "+players[1].getName()+" "+players[1].handValue());
            System.out.println(players[1].getName()+" wins");
        } else {
            System.out.println(players[0].getName()+" "+players[0].handValue()+" vs "+players[1].getName()+" "+players[1].handValue());
            System.out.println("Tie, no-one wins.");
        }
        Input.wait(br);
    }

    private void dealFaceUp(BlackjackPlayer player){
        Card dealt = dealCard();
        dealFaceUp(player,dealt);
    }

    private void dealFaceUp(BlackjackPlayer player, Card card){
        player.addCard(card);
        System.out.println(player.getName() + " card:" + card.shortString());
        System.out.print("Hand: ");
        player.printHand();
        System.out.print("Value: " + player.handValue());
        if (player.handValue() > 21){
            System.out.println(" BUST!");
        } else {
            System.out.println();
        }
    }

    private void setUp(BufferedReader br){
        System.out.println("Would you like to read the rules? (y/n)");
        if(Input.confirm(br)){
            rules();
            Input.wait(br);
        }
        System.out.println("Enter your name:");
        players[0] = new BlackjackPlayer(Input.getName(br));
        players[1] = new BlackjackPlayer("Dealer");
        System.out.println("How many rounds would you like to play (1-10)");
        rounds = Input.getInt(br,1,10);
    }

    public static void rules(){
        System.out.println("Blackjack rules:");
        System.out.println("Single player blackjack versus the dealer.");
        System.out.println("Dealer will deal two face up cards, one for you and one for themselves.");
        System.out.println("Dealer will then deal you a second face up card and then themselves a face down card");
        System.out.println("You can then choose to hit or stand");
        System.out.println("Hitting will make the dealer deal you another card from the deck");
        System.out.println("If your hand value exceeds 21 you will bust and your turn will end");
        System.out.println("Standing ends your turn with your current hand");
        System.out.println("The dealer will then reveal their face down card");
        System.out.println("The dealer must hit below 17 and stand on or above 17");
        System.out.println("The player with the greater hand value will win");
        System.out.println("If only one player busts, the player that did not bust wins");
        System.out.println("If both players bust or the hand values are identical, no-one wins");
        System.out.println("Number cards are face vale (2-10)");
        System.out.println("Face cards have a value of 10");
        System.out.println("The ace is valued at 11 or 1, it is 11 unless that would take your hand value above 21 in which case it becomes 1");
    }
}
