import java.util.ArrayList;
import java.util.Collections;

public class Play {
    static int[][] straights = {
            {3,4,5,1,2},
            {3,4,5,6,2},
            {3,4,5,6,7},
            {4,5,6,7,8},
            {5,6,7,8,9},
            {6,7,8,9,10},
            {7,8,9,10,11},
            {8,9,10,11,12},
            {9,10,11,12,13},
            {10,11,12,13,1},
            {11,12,13,1,2},
            {3,12,13,1,2},
            {3,4,13,1,2}
    };
    private ArrayList<Card> cards;
    private Card dom_card;
    private PlayStyle Style;
    public Play (ArrayList<Card> ac) {
        cards = ac;
        Collections.sort(cards, (o1, o2) -> Integer.compare(o1.getNum(), o2.getNum()));
        if (cards.size() == 1) {
            Style = PlayStyle.single;
            dom_card = cards.get(0);
        } else if (cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank()) {
            Style = PlayStyle.pair;
            dom_card = cards.get(1);
        } else if (cards.size() == 5) {
            if (cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(3).getRank() == cards.get(4).getRank() &&
                (cards.get(2).getRank() == cards.get(1).getRank() || cards.get(2).getRank() == cards.get(3).getRank())
            ) {
                Style = PlayStyle.full_house;
                dom_card = cards.get(2);
            } else {
                boolean is_straight = false;
                for (int[] ia : straights) {
                    if (is_straight) break;
                    boolean cis = true;
                    for (int i = 0; i < 5; i++)
                        if (cards.get(i).getRank() != ia[i])
                            cis = false;
                    if (cis) is_straight = true;
                }
                if (is_straight) {
                    Style = PlayStyle.straight;
                    dom_card = cards.get(4);
                } else { Style = PlayStyle.invalid; }
            }
        } else {
            Style = PlayStyle.invalid;
        }
    }

    public Play () {
        cards = new ArrayList<> ();
        Style = PlayStyle.invalid;
    }

    public Play (PlayStyle ps) {
        cards = new ArrayList<>();
        Style = ps;
    }

    public PlayStyle getStyle() {
        return Style;
    }

    public int getDom() {
        if (Style == PlayStyle.invalid) return -1;
        if (Style == PlayStyle.pass) return -2;
        return dom_card.getNum();
    }

    @Override
    public String toString() {
        String ret = Style.toString();
        for (Card c : cards)
            ret += " " + c.toString();
        return ret;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
