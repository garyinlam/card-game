package nology.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackPlayer extends Player{

    private List<Card> hand;

    public BlackjackPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void resetHand(){
        hand = new ArrayList<>();
    }

    public void printHand(){
        for (Card card: hand) {
            System.out.print(card.shortString() + " ");
        }
        System.out.println();
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public int handValue(){
        Collections.sort(hand);
        int total = 0;
        boolean aces = false;
        for (Card card: hand) {
            if(card.getValue() <= 10){
                total += card.getValue();
            } else if (card.getValue() != 14){
                total += 10;
            } else {
                total++;
                aces = true;
            }
        }
        if (aces && total + 10 <= 21){
            total += 10;
        }
        return total;
    }
}
