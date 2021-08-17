import java.util.HashMap;

public class Card{
    private Suit suit;
    private int rank;
    String tS[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    HashMap<Suit, String> outSuit = new HashMap<>() {{
            put(Suit.club, "C"); put(Suit.diamond, "D"); put(Suit.heart, "H"); put(Suit.spade, "S");
    }};
    HashMap<String, Suit> toSuit = new HashMap<String, Suit>() {{
        put("C", Suit.club); put("D", Suit.diamond); put("H", Suit.heart); put("S", Suit.spade);
    }};

    public Card(Suit s, int i) {
        suit = s;
        rank = i;
    }

    public Card(String s){
        String[] tmp = s.split("\\[|\\]");
        Suit _suit = toSuit.get(tmp[0]);
        int _rank;
        switch (tmp[1]) {
            case "A": _rank = 1; break;
            case "J": _rank = 11; break;
            case "Q": _rank = 12; break;
            case "K": _rank = 13; break;
            default: _rank = Integer.parseInt(tmp[1]); break;
        }
        suit = _suit;
        rank = _rank;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNum() { return ((rank - 3 + 13) % 13) * 4 + suit.ordinal(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;
        if (rank == card.rank && suit == card.suit) return true;
        return false;
    }

    @Override
    public String toString() {
        return outSuit.get(suit)+ "[" + tS[rank - 1] + "]";
    }
}
