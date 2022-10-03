package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean playAgain = true;
        while (playAgain) {
            Snap snap = new Snap();
            try {
                snap.doGame(br);
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
