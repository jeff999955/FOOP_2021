
public abstract class State {
    public final String name;

    public State(String namae) {
        name = namae;
    }

    public String getName() {
        return name;
    }

    protected boolean canCast(Unit u) {
        return true;
    }

    protected int getAdditionDamage() {
        return 0;
    }
}