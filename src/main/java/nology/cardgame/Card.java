package nology.cardgame;

public class Card implements Comparable<Card> {
    private String suit;
    private String symbol;
    private int value;

    public Card(String suit, String symbol) {
        this.suit = suit;
        this.symbol = symbol;
        value = symbolToValue(symbol);
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    private int symbolToValue(String symbol) {
        switch (symbol){
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(symbol);
        }
    }

    public String shortString() {
        return symbol + suit;
    }

    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (!(obj instanceof Card)) {
            return false;
        }

        Card c = (Card) obj;

        return this.suit.equals(c.suit) && this.value == c.value;
    }

    @Override
    public String toString() {
        return "Card{" +
                symbol  + suit +
                ", value=" + value +
                '}';
    }
}
