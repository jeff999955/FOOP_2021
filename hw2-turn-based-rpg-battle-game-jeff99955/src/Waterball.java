import java.util.List;

public class Waterball extends Skill {

    private static int cestDamage = 120;

    public Waterball() {
        super(TargetType.Enemy, 1, 50, "Waterball");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Unit target = targetList.get(0);
        Output.printSkillTarget(this.getName(), caster, targetList);
        int damage = cestDamage + caster.getState().getAdditionDamage();;
        Output.Damage(caster, target, damage);
        target.takeDamage(damage);
    }

}
