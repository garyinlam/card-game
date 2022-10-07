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
            System.out.println("3 - War");
            int choice = Input.getInt(br,1,3);
            CardGame game = null;
            switch (choice){
                case 1:
                    game = new Snap();
                    break;
                case 2:
                    game = new Blackjack();
                    break;
                case 3:
                    game = new War();
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
