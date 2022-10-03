package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;

public class Input {
    public static boolean confirm(BufferedReader br){
        String input = null;
        try {
            input = br.readLine().toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean correctInput = false;
        while (!correctInput){
            correctInput = input.matches("y") || input.matches("n");
            if (!correctInput){
                System.out.println("Only input y or n");
                try {
                    input = br.readLine().toLowerCase();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return input.matches("y");
    }

    public static String getName(BufferedReader br){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
