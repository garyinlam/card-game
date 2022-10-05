package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Choose a game:");
            System.out.println("1 - Snap");
            System.out.println("2 - Blackjack");
            int choice = Input.getInt(br,1,2);
            CardGame game = null;
            switch (choice){
                case 1:
                    game = new Snap();
                    break;
                case 2:
                    game = new Blackjack();
                    break;
            }
            try {
                if (game != null) {
                    game.doGame(br);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Play again? (y/n)");
            playAgain = Input.confirm(br);
        }

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
