import tw.waterball.foop.hw2.provided.AI;
import utils.Inputs;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Unit {
    private int HP, MP, STR, stateTime;
    private Troop affiliation;
    private String name;
    private State currentState;
    private final Set<Unit> curseCaster;
    private final List<Skill> skills;
    private final AI ai;
    public HashMap<String, State> states;

    public Unit(String nm, int hp, int mp, int str, Troop af, HashMap<String, State> stMap) {
        name = nm;
        HP = hp;
        MP = mp;
        STR = str;
        affiliation = af;
        curseCaster = new HashSet<>();
        skills = new ArrayList<>();
        ai = new AI();
        stateTime = 0;
        skills.add(new BasicAttack());
        states = stMap;
        currentState = states.get("Normal");
    }

    public void setHP(int hp) {
        HP = hp;
    }

    public int getHP() {
        return HP;
    }

    public void setMP(int mp) {
        MP = mp;
    }

    public int getMP() {
        return MP;
    }

    public void setSTR(int str) {
        STR = str;
    }

    public int getSTR() {
        return STR;
    }

    public int getAffiliation() {
        return affiliation.getID();
    }

    public Troop getTroop() {
        return affiliation;
    }

    public String getName() {
        return name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill s) {
        this.skills.add(s);
    }

    public boolean isAlly(Unit u) {
        return this.affiliation == u.affiliation;
    }

    public void takeDamage(int dmg) {
        setHP(getHP() - dmg);
        if (getHP() <= 0) {
            Output.printDied(this);
            for (Unit u : curseCaster) {
                u.setHP(u.getHP() + this.getMP());
            }
        }
    }

    public boolean isDead() {
        return (this.HP <= 0);
    }

    public void updateState() {
        if (!getStateName().equals("Normal"))
            stateTime--;
        if (stateTime == 0)
            currentState = states.get("Normal");
    }

    public String getStateName() {
        return currentState.getName();
    }

    public State getState() {
        return currentState;
    }

    public void setState(String s, int time) {
        currentState = states.get(s);
        stateTime = time;
    }

    @Override
    public String toString() {
        return String.format("[%d]%s", affiliation.getID(), name);
    }

    public Skill selectAction() {
        int sel;
        printAction();
        if (name.equals("Hero")) {
            sel = Integer.parseInt(Inputs.in.nextLine());
            while (MP < skills.get(sel).getRequiredMP()) {
                System.out.println("You can't perform the action: insufficient MP.");
                printAction();
                sel = Integer.parseInt(Inputs.in.nextLine());
            }
        } else {
            List<Integer> choices = new ArrayList<>();
            for (int i = 0; i < skills.size(); i++)
                if (MP >= skills.get(i).getRequiredMP())
                    choices.add(i);
            sel = ai.selectAction(choices);
        }
        return skills.get(sel);
    }

    public void printAction() {
        System.out.print("Select an action:");
        for (int i = 0; i < skills.size(); i++)
            System.out.printf(" (%d) %s", i, skills.get(i));
        System.out.print("\n");
    }

    public List<Unit> decideTargets(Skill s, ArrayList<Troop> alt) {
        List<Unit> ally = new ArrayList<>();
        List<Unit> enemy = new ArrayList<>();
        List<Unit> all = new ArrayList<>();
        for (Troop t : alt) {
            for (Unit u : t.getMembers())
                if (!u.isDead() && u != this) {
                    if (isAlly(u))
                        ally.add(u);
                    else
                        enemy.add(u);
                    all.add(u);
                }
        }
        List<Unit> ret = new ArrayList<>();
        switch (s.getTargetType()) {
            case All:
                ret = all;
                break;
            case Ally:
                ret = ally;
                break;
            case Enemy:
                ret = enemy;
                break;
            case Self:
                ret.add(this);
                break;
            default:
                break;
        }
        if (s.getRequiredTarget() > 0 && s.getTargetType() != TargetType.Self && s.getRequiredTarget() < ret.size()) {
            List<Integer> toSelect;
            if (name.equals("Hero"))
                toSelect = selectTarget(ret, s.getRequiredTarget());
            else
                toSelect = ai.selectTarget(ret.size(), s.getRequiredTarget());
            List<Unit> tmp = new ArrayList<>();
            for (int i : toSelect)
                tmp.add(ret.get(i));
            ret = tmp;
        }
        return ret;
    }

    public List<Integer> selectTarget(List<Unit> candidates, int requiredTarget) {
        List<Integer> ret = new ArrayList<>();
        Output.printAvailableTargets(requiredTarget, candidates);
        String buf = Inputs.in.nextLine();
        // System.out.printf("xx%sxx\n", buf);
        String[] split = buf.split(",\\s");
        for (String s : split)
            ret.add(Integer.parseInt(s));
        return ret;
    }

    public boolean takeAction(ArrayList<Troop> t) {
        boolean cC = currentState.canCast(this);
        if (cC == false)
            return false;
        Skill s = selectAction();
        List<Unit> targetList = decideTargets(s, t);
        s.consumeMP(this);
        s.perform(this, targetList);
        return true;
    }

    public String getTurn() {
        return String.format("%s's turn (HP: %d, MP: %d, STR: %d, State: %s).", toString(), HP, MP, STR,
                currentState.getName());
    }

    public void addCurseCaster(Unit u) {
        curseCaster.add(u);
    }
}
