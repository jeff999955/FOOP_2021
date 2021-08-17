import java.util.ArrayList;

public class Troop {
    private ArrayList<Unit> members;
    private int ID;

    public Troop(int ID) {
        this.members = new ArrayList<>();
        this.ID = ID;
    }

    public void addMember(Unit u) {
        this.members.add(u);
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Unit> getMembers() {
        return members;
    }

    public boolean isAnnihilated() {
        int cnt = 0;
        for (Unit u : members)
            cnt += (u.isDead() ? 1 : 0);
        return (cnt == members.size());
    }

    @Override
    public String toString() {
        String ret = "Troop " + ID + ":\n";
        for (Unit u : members) {
            ret += u.toString() + "skills:";
            for (Skill s : u.getSkills())
                ret += " " + s.toString();
            ret += "\n";
        }
        return ret;
    }
}
