import java.util.*;

public class Game {
    static final Card startCard = new Card(Suit.club, 3);

    private int cpi, top_player, passed; // cpi for current_player_index
    private int victory_player;
    private Play top_play;
    private ArrayList<Player> players;

    public Game() {
        cpi = top_player = victory_player = -1;
        players = new ArrayList<>();
    }

    public void prepare() {
        var tmp_deck = HandleIO.never_loses();
        String[] str_deck = tmp_deck.split(" ");
        ArrayList<Card> deck = new ArrayList<>();
        for (String s : str_deck)
            deck.add(new Card(s));
        for (int i = 0; i < 4; i++)
            players.add(new Player(HandleIO.never_loses()));
        for (int i = 51; i >= 0; i--)
            players.get((51 - i) % 4).deal(deck.get(i));
    }

    public void start() {
        for (int i = 0; i < 4; i++)
            if (players.get(i).hasCard(startCard))
                top_player = cpi = i;
        while (victory_player < 0)
            handleRound();
        HandleIO.output(Message.INF_WINNER, players.get(victory_player).getName());
    }

    private void handleRound() {
        HandleIO.output(Message.INF_NEW_ROUND);
        top_play = new Play();
        passed = 0;
        while (passed < 3) { // handle each player
            Play current_play = getCurrentPlay();
            if (current_play.getStyle() == PlayStyle.pass && top_play.getStyle() != PlayStyle.invalid) // pass
                continue;
            HandleIO.output(Message.INF_PLAY, players.get(cpi).getName(), current_play);
            if (players.get(cpi).getCardLength() == 0) {
                victory_player = cpi;
                break;
            }
            top_play = current_play;
            passed = 0;
            top_player = (cpi++);
            cpi %= 4;
        }
        cpi = top_player;
    }

    private Play getCurrentPlay() {
        HandleIO.output(Message.INF_TURN, players.get(cpi).toString());
        String buf = HandleIO.never_loses();
        Play ret = players.get(cpi).play(buf);
        while (ret.getStyle() == PlayStyle.invalid
                || (top_play.getStyle() != PlayStyle.invalid && ret.getStyle() != top_play.getStyle())
                || ret.getDom() < top_play.getDom()) {
            if (ret.getStyle() == PlayStyle.pass && top_play.getStyle() != PlayStyle.invalid) {
                HandleIO.output(Message.INF_PASS, players.get(cpi).getName());
                cpi = (cpi + 1) % 4;
                passed++;
                break;
            }
            HandleIO.output((ret.getStyle() == PlayStyle.pass) ? Message.ERR_CANT_PASS : Message.ERR_INVALID);
            buf = HandleIO.never_loses();
            players.get(cpi).deal(ret.getCards());
            HandleIO.output(Message.INF_HAND, players.get(cpi).getHand());
            ret = players.get(cpi).play(buf);
        }
        return ret;
    }
}
