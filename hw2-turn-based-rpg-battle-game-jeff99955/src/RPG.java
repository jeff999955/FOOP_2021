import java.util.HashMap;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
import utils.Inputs; // TA no input

public class RPG {
    private ArrayList<Troop> troops;
    private HashMap<String, Skill> SkillMap;
    private HashMap<String, State> StateMap;
    
    public RPG(int n_troop, HashMap<String,Skill> sMap, HashMap<String, State> stMap) {
        SkillMap = sMap;
        StateMap = stMap;
        troops = new ArrayList<>();
        for (int i = 0; i < n_troop; i++) troops.add(new Troop(i + 1));
    }

    public void load() {
        for (int i = 0; i < troops.size() && Inputs.in.hasNextLine(); i++) {
            String buf = Inputs.in.nextLine();
            if (buf.equals("#START-TROOP-" + (i + 1))) {
                while (Inputs.in.hasNextLine()) {
                    buf = Inputs.in.nextLine();
                    if (buf.equals("#END-TROOP-" + (i + 1)))
                        break;
                    String[] splitbuf = buf.split(" ");
                    Unit nu = new Unit(splitbuf[0], parseInt(splitbuf[1]), parseInt(splitbuf[2]), parseInt(splitbuf[3]), troops.get(i), StateMap);
                    for (int j = 4; j < splitbuf.length; j++) 
                        nu.addSkill(SkillMap.get(splitbuf[j]));
                    troops.get(i).addMember(nu);
                }
            } 
        }
    }

    public void debug() {
        for (int i = 0; i < troops.size() ; i++) {
            System.out.println(troops.get(i));
        }
    }

    public void start() {
        boolean shuuryou = false;
        while (! shuuryou) {
            for (Troop t : troops) {
                for (int i = 0; i < t.getMembers().size(); i++) {
                    int num_ann = 0;
                    for (Troop it : troops)
                        if (it.isAnnihilated())
                            num_ann++;
                    if (num_ann >= troops.size() - 1){
                        shuuryou = true;
                        break;
                    }
                    Unit u = t.getMembers().get(i);
                    if (u.isDead())
                        continue;
                    System.out.println(u.getTurn());
                    if (u.takeAction(troops) == false && u.isDead()) {
                        Output.printDied(u);
                        continue;                        
                    }
                    if (troops.get(0).getMembers().get(0).isDead()) {
                        shuuryou = true;
                        break;
                    }
                }
                if (shuuryou) break;
            }
            for (Troop t : troops)
				for (Unit u : t.getMembers()) 
					u.updateState();
        }
        if (troops.get(0).getMembers().get(0).isDead())
            System.out.println("You lose.");
		else
			System.out.println("You win.");
    }

}
