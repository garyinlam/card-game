package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class War extends CardGame{

    private WarPlayer[] players;
    private int tableCards;

    public War() {
        super("war");
        players = new WarPlayer[2];
        tableCards = 0;
    }

    @Override
    public void doGame(BufferedReader br) throws IOException {
        System.out.println("Welcome to "+getName());
        shuffleDeck();
        setUp(br);
        boolean finished = false;
        while (!finished){
            doTurn(br);
            Input.wait(br);
            finished = players[0].isOutOfCards();
        }
        System.out.println(players[0].getName() + " won " + players[0].getCardsWon() + " cards");
        System.out.println(players[1].getName() + " won " + players[1].getCardsWon() + " cards");
        if (players[0].getCardsWon() > players[1].getCardsWon()){
            System.out.println(players[0].getName() + " wins!");
        } else if (players[0].getCardsWon() == players[1].getCardsWon()) {
            System.out.println("It's a tie!");
        } else {
            System.out.println(players[1].getName() + " wins!");
        }
    }

    public void doTurn(BufferedReader br){
        Card playerCard = players[0].dealCard();
        Card compCard = players[1].dealCard();
        System.out.println(players[0].getName() + "'s card: " + playerCard.shortString());
        Input.wait(br);
        System.out.println(players[1].getName() + "'s card: " + compCard.shortString());
        Input.wait(br);
        if (playerCard.getValue() > compCard.getValue()){
            System.out.println(players[0].getName() + " wins this round!");
            players[0].addWonCards(2 + tableCards);
            tableCards = 0;
        } else if (playerCard.getValue() == compCard.getValue()) {
            System.out.println("It's a tie, cards are held for next turn");
            tableCards += 2;
        } else {
            System.out.println(players[1].getName() + " wins this round!");
            players[1].addWonCards(2 + tableCards);
            tableCards = 0;
        }
    }

    private void setUp(BufferedReader br) {
        System.out.println("Would you like to read the rules? (y/n)");
        if(Input.confirm(br)){
            rules();
            Input.wait(br);
        }
        System.out.println("Enter your name:");
        players[0] = new WarPlayer(Input.getName(br), new ArrayList<>(getDeck().subList(0,getDeck().size()/2)));
        players[1] = new WarPlayer("Computer",new ArrayList<>(getDeck().subList(getDeck().size()/2,getDeck().size())));
    }

    public static void rules() {
        System.out.println("War is played vs the computer");
        System.out.println("Both players receive half of a shuffled deck");
        System.out.println("Both players then deal the top card of the deck");
        System.out.println("The card with the greater value (Ace high) wins");
        System.out.println("In the event of a tie, the cards are saved for the next round");
        System.out.println("The player that wins the round after a tie gets all the cards on the table");
        System.out.println("Game ends when both players are out of unplayed cards, the player with more cards won wins");
    }
}
