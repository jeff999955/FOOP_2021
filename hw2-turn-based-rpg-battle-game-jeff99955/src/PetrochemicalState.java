public class PetrochemicalState extends State {

    public PetrochemicalState() {
        super("Petrochemical");
    }

    @Override
    protected boolean canCast(Unit u) {
        return false;
    }
}
