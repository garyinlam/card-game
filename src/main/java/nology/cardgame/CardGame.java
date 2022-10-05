package nology.cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class CardGame {
    ArrayList<Card> deckOfCards;
    String name;

    public CardGame(String name) {
        this.name = name;
        generateDeck();
    }


    public ArrayList<Card> getDeck() {
        return deckOfCards;
    }

    private void generateDeck(){
        deckOfCards = new ArrayList<>();
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = {Suits.CLUB.getUnicode(),Suits.HEART.getUnicode(),Suits.SPADE.getUnicode(),Suits.DIAMOND.getUnicode()};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deckOfCards.add(new Card(suits[i],values[j]));
            }
        }
    }

    public void reset(){
        generateDeck();
    }

    public Card dealCard(){
        if (deckOfCards.size() == 0){
            return null;
        }
        return deckOfCards.remove(deckOfCards.size()-1);
    }

    public void sortDeckInNumberOrder(){
        Collections.sort(deckOfCards);
    }

    public void sortDeckIntoSuits(){
        Collections.sort(deckOfCards, new SortBySuitThenValue());
    }

    public void shuffleDeck(){
        Random random = new Random();
        ArrayList<Card> shuffled = new ArrayList<>();
        while(!deckOfCards.isEmpty()){
            shuffled.add(deckOfCards.remove(random.nextInt(deckOfCards.size())));
        }

        deckOfCards = shuffled;
    }

    public void printDeck() {
        deckOfCards.forEach(card -> {
            System.out.print(card.shortString()+ " ");
        });
        System.out.println();
    }

    public abstract void doGame(BufferedReader br) throws IOException;


}
