package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;

public class Snap extends CardGame{

    private Card lastCard;
    private Player[] players = new Player[2];

    private boolean isMultiplayer;

    public Snap() {
        super("snap");
    }

    @Override
    public void doGame(BufferedReader br) throws IOException {
        System.out.println("Welcome to snap");
        setUp(br);
        shuffleDeck();
        boolean hasWon = false;
        int turnCounter = 0;
        while (!hasWon) {
            Card current = doTurn(br, players[turnCounter % players.length]);
            turnCounter++;
            if(lastCard != null) {
                hasWon = current.getValue() == lastCard.getValue();
            }
            lastCard = current;
        }

        endGame(br,turnCounter);
    }

    private Card doTurn(BufferedReader br, Player player){
        System.out.println("-----------------------------------------------------------");
        System.out.println(player.getName()+"'s turn");
        Input.wait(br);
        Card card = dealCard();
        if (card == null) {
            System.out.println("Out of cards, picking up cards except last and reshuffling deck");
            reset();
            getDeck().remove(lastCard);
            shuffleDeck();
            card = dealCard();
        }
        System.out.println(card.shortString());
        return card;
    }

    private void setUp(BufferedReader br){
        System.out.println("Would you like to read the rules? (y/n)");
        if(Input.confirm(br)){
            rules();
            Input.wait(br);
        }
        System.out.println("Would you like to play against a friend (y) or the computer (n)");
        isMultiplayer = Input.confirm(br);
        if (isMultiplayer){
            System.out.println("Enter first player's name:");
            players[0] = new Player(Input.getName(br));
            System.out.println("Enter second player's name:");
            players[1] = new Player(Input.getName(br));
        } else {
            System.out.println("Enter your name:");
            players[0] = new Player(Input.getName(br));
            players[1] = new Player("Computer");
        }
    }

    private void endGame(BufferedReader br, int turnCounter) throws IOException {
        Player current = players[0];
        Player other = players[1];
        if (isMultiplayer){
            current = players[(turnCounter - 1) % players.length];
            other = players[turnCounter % players.length];
        }
        System.out.println("Quick type 'snap' to win");
        int timeOut = 2;
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeOut * 1000
                && !br.ready()) {
        }
        if (br.ready()) {
            String check = br.readLine().toLowerCase();
            if (check.equals("snap")){
                System.out.println("Snap! " + current.getName() + " wins!");
            } else {
                System.out.println("Incorrect input you lose! " + other.getName() + " wins!");
            }
        } else {
            System.out.println("Too slow you lose! " + other.getName() + " wins!");
        }
    }

    public static void rules(){
        System.out.println("Snap can be played versus the computer or another player.");
        System.out.println("Each turn a card is dealt from the top of the deck");
        System.out.println("If the card value matches the previous card's value that player has 2 seconds to type snap");
        System.out.println("When versus another player:");
        System.out.println("Typing in snap within the time limit will result in the player that dealt that card winning");
        System.out.println("Miss typing or failing to type within 2 seconds will result in the other player winning.");
        System.out.println("When playing versus the computer:");
        System.out.println("Typing in snap within the time limit will result in the player winning");
        System.out.println("Miss typing or failing to type within 2 seconds will result in the computer winning.");
    }

}
