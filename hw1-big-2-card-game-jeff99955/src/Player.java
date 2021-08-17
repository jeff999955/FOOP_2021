import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private ArrayList<Card> hand_cards;
    private String name;
    public Player (String s) {
        name = s;
        hand_cards = new ArrayList<Card> ();
    }

    public String getName() {
        return name;
    }

    public Player (String s, ArrayList<Card> ac) {
        name = s;
        hand_cards = ac;
        arrange();
    }

    public boolean hasCard(Card c) {
        return hand_cards.contains(c);
    }
    public int getCardLength() { return hand_cards.size(); };
    public void deal(Card c) {
        hand_cards.add(c);
        arrange();
    }

    public void deal(ArrayList<Card> ac) {
        hand_cards.addAll(ac);
        arrange();
    }

    public void arrange() {
        Collections.sort(hand_cards, (o1, o2) -> Integer.compare(o1.getNum(), o2.getNum()));
    }
    @Override
    public String toString() {
        String ret = name + "\n" + getHand();
        return ret;
    }

    public String getHand() {
        String ret = "";
        StringBuilder numbers = new StringBuilder();
        StringBuilder cards = new StringBuilder();
        for (int i = 0; i < hand_cards.size(); i++) {
            Card card = hand_cards.get(i);
            numbers.append(String.format("%"+(-card.toString().length())+"s", i)).append(" ");
            cards.append(card.toString()).append(" ");
        }
        ret += numbers.toString().trim() + "\n" + cards.toString().trim();
        return ret;
    }

    public Play play(String buf) {
        if (buf.startsWith("-1")) {
            Play ret = new Play(PlayStyle.pass);
            return ret;
        }
        String[] str_toPlay = buf.split(" ");
        ArrayList<Integer> card_index = new ArrayList<>();
        for (String s : str_toPlay) card_index.add(Integer.parseInt(s));
        ArrayList<Card> toPlay = new ArrayList<>();
        ArrayList<Card> remaining = new ArrayList<>();
        Collections.sort(card_index);
        for (int i = 0, j = 0; i < hand_cards.size(); i++) {
            if (j < card_index.size() && i == card_index.get(j)) {
                toPlay.add(hand_cards.get(i));
                j++;
            } else {
                remaining.add(hand_cards.get(i));
            }
        }
        hand_cards = remaining;
        return new Play(toPlay);
    }
}
