import java.util.List;

public class BasicAttack extends Skill {

    public BasicAttack() {
        super(TargetType.Enemy, 1, 0, "Basic Attack");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Unit target = targetList.get(0);
        System.out.printf("%s attacks %s.\n", caster, target);
        int damage = caster.getSTR() + caster.getState().getAdditionDamage();
        Output.Damage(caster, target, damage);
        target.takeDamage(damage);
    }

}
