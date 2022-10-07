package nology.cardgame;

import java.util.Stack;

public class WarPlayer extends Player {

    private Stack<Card> deck;
    private int cardsWon = 0;


    public WarPlayer(String name, Stack<Card> deck) {
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
        return deck.pop();
    }

    public void addWonCards(int won){
        cardsWon += won;
    }

    public boolean isOutOfCards(){
        return deck.isEmpty();
    }

}
