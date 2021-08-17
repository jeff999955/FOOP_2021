import java.util.List;

public class SelfExplosion extends Skill {

    private static final int cestDamage = 150;

    public SelfExplosion() {
        super(TargetType.All, -1, 200, "SelfExplosion");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Output.printSkillTarget(this.getName(), caster, targetList);
        int damage = cestDamage + caster.getState().getAdditionDamage();;
        for (Unit target : targetList) {
            Output.Damage(caster, target, damage);
            target.takeDamage(damage);
        }
        caster.takeDamage(caster.getHP());
    }
    
}
