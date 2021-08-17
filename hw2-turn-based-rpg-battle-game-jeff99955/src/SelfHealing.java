import java.util.List;

public class SelfHealing extends Skill {

    private static int healCoefficient = 150;
    public SelfHealing() {
        super(TargetType.Self, 1, 50, "SelfHealing");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        System.out.printf("%s uses %s.\n", caster, this.getName());
        caster.setHP(caster.getHP() + healCoefficient);
    }
    
}
