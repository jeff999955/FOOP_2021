public class PoisonState extends State {

    public PoisonState() {
        super("Poisoned");
    }

    @Override
    protected boolean canCast(Unit u) {
        u.setHP(u.getHP() - 30);
        return (u.getHP() > 0);
    }

}
