import java.util.List;

public class Cheerup extends Skill {

    private static int additionDamage = 50;

    public Cheerup() {
        super(TargetType.Ally, 3, 100, "Cheerup");
    }

    public static int getAdditionDamage() {
        return additionDamage;
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Output.printSkillTarget(this.getName(), caster, targetList);
        for (Unit u : targetList)
            u.setState("Cheerup", 3);
    }

}
