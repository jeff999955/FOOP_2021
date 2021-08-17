import java.util.List;

public abstract class Skill {
    private TargetType targettype;
    private int requiredTarget;
    private int requiredMP;
    private String name;

    public Skill(TargetType tt, int rt, int rm, String nm) {
        targettype = tt;
        requiredTarget = rt;
        requiredMP = rm;
        name = nm;
    }
    public String getName() {
        return name;
    }

    public int getRequiredMP() {
        return requiredMP;
    }

    public TargetType getTargetType() {
        return targettype;
    }

    public int getRequiredTarget() {
        return requiredTarget;
    }

    @Override
    public String toString() {
        return name;
    }

    protected void consumeMP(Unit caster) {
        caster.setMP(caster.getMP() - requiredMP);
    }
    // OCP
    protected abstract void perform(Unit caster, List<Unit> targetList);
}
