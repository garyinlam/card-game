package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;

public class Snap extends CardGame{

    private Card lastCard;
    private Player[] players = new Player[2];

    public Snap() {
        super("snap");
    }

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
        System.out.println("Press enter do next turn");
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println("Would you like to play against a friend (y) or the computer (n)");
        if (Input.confirm(br)){
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
        System.out.println("Quick type 'snap' to win");
        int timeOut = 2;
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeOut * 1000
                && !br.ready()) {
        }
        if (br.ready()) {
            String check = br.readLine().toLowerCase();
            if (check.equals("snap")){
                System.out.println("Snap! " + players[(turnCounter - 1) % players.length].getName() + " wins!");
            } else {
                System.out.println("Incorrect input you lose! " + players[turnCounter % players.length].getName() + " wins!");
            }
        } else {
            System.out.println("Too slow you lose! " + players[turnCounter % players.length].getName() + " wins!");
        }
    }

}
