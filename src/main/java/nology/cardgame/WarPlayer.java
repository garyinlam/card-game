package nology.cardgame;

import java.util.ArrayList;

public class WarPlayer extends Player {

    private ArrayList<Card> deck;
    private int cardsWon = 0;


    public WarPlayer(String name, ArrayList<Card> deck) {
        super(name);
        this.deck = deck;
    }

    public int getCardsWon(){
        return cardsWon;
    }

    public Card dealCard(){
        if (deck.size() == 0){
            return null;
        }
        return deck.remove(deck.size()-1);
    }

    public void addWonCards(int won){
        cardsWon += won;
    }

    public boolean isOutOfCards(){
        return deck.isEmpty();
    }

}
