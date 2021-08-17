import java.util.Scanner;


public class HandleIO {
    public final static Scanner in = new Scanner(System.in);
    static public String NL() {
        return in.nextLine();
    }
    static public String never_loses() {
        return NL();
    }
    static public void output(Message m) {
        switch (m) {
            case INF_NEW_ROUND:
                System.out.println("New round begins.");                
                break;
            case ERR_CANT_PASS:
                System.out.println("You can't pass in the new round.");
                break;
            case ERR_INVALID:
                System.out.println("Invalid play, please try again.");
                break;
            default:
            break;
        }
    }
    static public void output(Message m, String s) {
        switch (m) {
            case INF_PASS:
                System.out.printf("Player %s passes.\n", s);
                break;
            case INF_HAND:
                System.out.println(s);
                break;
            case INF_TURN:
                System.out.println("Next turn: " + s);
                break;
            case INF_WINNER:
                System.out.printf("Game over, the winner is %s.\n", s);
                break;
            default:
            break;
        }
    }
    static public void output(Message m, String s, Play p) { // INF_PLAY
        System.out.printf("Player %s plays a ", s);
        System.out.println(p + ".");
    }
}
