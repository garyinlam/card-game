package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;

public class Blackjack extends CardGame{

    private BlackjackPlayer[] players = new BlackjackPlayer[2];
    private int rounds;
    private int roundsWon = 0;

    public Blackjack() {
        super("blackjack");
    }

    @Override
    public void doGame(BufferedReader br) throws IOException {
        System.out.println("Welcome to blackjack");
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
        dealFaceUp(players[1],faceDown);
        while (players[1].handValue() < 17){
            dealFaceUp(players[1]);
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
        System.out.println("Enter your name:");
        players[0] = new BlackjackPlayer(Input.getName(br));
        players[1] = new BlackjackPlayer("Dealer");
        System.out.println("How many rounds would you like to play (1-10)");
        rounds = Input.getInt(br,1,10);
    }


}
