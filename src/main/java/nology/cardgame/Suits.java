package nology.cardgame;

public enum Suits {
    CLUB("\u2663"), HEART("\u2661"), SPADE("\u2660"), DIAMOND("\u2662");
    String unicode;

    Suits(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }
}
