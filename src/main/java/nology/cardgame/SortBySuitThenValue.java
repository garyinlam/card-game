package nology.cardgame;

import java.util.Comparator;

public class SortBySuitThenValue implements Comparator<Card> {
    @Override
    public int compare(Card left, Card right) {
        if(left.getSuit().equals(right.getSuit())){
            return left.compareTo(right);
        }
        if (left.getSuit().equals(Suits.CLUB.getUnicode())){
            return -1;
        } else if (left.getSuit().equals(Suits.HEART.getUnicode())) {
            if (right.getSuit().equals(Suits.CLUB.getUnicode())){
                return 1;
            } else {
                return -1;
            }
        } else if (left.getSuit().equals(Suits.SPADE.getUnicode())) {
            if (right.getSuit().equals(Suits.DIAMOND.getUnicode())){
                return -1;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
