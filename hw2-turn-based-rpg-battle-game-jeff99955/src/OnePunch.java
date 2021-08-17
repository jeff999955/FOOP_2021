import java.util.List;
import tw.waterball.foop.hw2.provided.Target;

public class OnePunch extends Skill {

    private static tw.waterball.foop.hw2.provided.OnePunch pop;

    public OnePunch() {
        super(TargetType.Enemy, 1, 180, "OnePunch");
        pop = new tw.waterball.foop.hw2.provided.OnePunch();
    }

    @Override
    public int getRequiredMP() {
        return pop.getMpCost();
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Output.printSkillTarget(this.getName(), caster, targetList);
        UtilUnit utilTarget = new UtilUnit();
        pop.perform(utilTarget);
        Unit target = targetList.get(0);
        int damage = utilTarget.getDamage();
        damage += caster.getState().getAdditionDamage();;
        Output.Damage(caster, target, damage);
        target.takeDamage(damage);
    }

}

class UtilUnit implements Target {
    private int _damage;

    @Override
    public void takeOnePunchDamage(int damage) {
        _damage = damage;
    }

    public int getDamage() {
        return _damage;
    }
}
