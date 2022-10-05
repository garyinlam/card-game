package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

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

    public static boolean hitOrStand(BufferedReader br){
        String input = null;
        try {
            input = br.readLine().toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean correctInput = false;
        while (!correctInput){
            correctInput = input.matches("hit") || input.matches("stand");
            if (!correctInput){
                System.out.println("Only input hit or stand");
                try {
                    input = br.readLine().toLowerCase();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return input.matches("stand");
    }

    public static String getName(BufferedReader br){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getInt(BufferedReader br, int min, int max){
        String input;
        try {
            input = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int retVal = 0;

        boolean validInput = false;

        while (!validInput) {
            try {
                retVal = Integer.parseInt(input);

                if(retVal < min || retVal > max){
                    System.err.println("Please enter in an valid number ("+min+"-"+max+")");
                    input = br.readLine();
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.err.println("Only input a number");
                try {
                    input = br.readLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return retVal;
    }

    public static void wait(BufferedReader br){
        System.out.println("Press enter do next turn");
        try {
            br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
