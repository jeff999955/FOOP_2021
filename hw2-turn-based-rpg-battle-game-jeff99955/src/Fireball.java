import java.util.List;

public class Fireball extends Skill {

    private static int cestDamage = 50;

    public Fireball() {
        super(TargetType.Enemy, -1, 50, "Fireball");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Output.printSkillTarget(this.getName(), caster, targetList);
        int damage = cestDamage + caster.getState().getAdditionDamage();;
        for (Unit target : targetList) {
            Output.Damage(caster, target, damage);
            target.takeDamage(damage);
        }
    }

}
