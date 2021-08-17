import java.util.List;

public class Poison extends Skill{

    private static int cestDamage = 30;
    public Poison() {
        super(TargetType.Enemy, 1, 80, "Poison");
    }
    public static int getDamage() {
        return cestDamage;
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Unit target = targetList.get(0);
        Output.printSkillTarget(this.getName(), caster, targetList);
        target.setState("Poisoned", 3);
    }
    
}
