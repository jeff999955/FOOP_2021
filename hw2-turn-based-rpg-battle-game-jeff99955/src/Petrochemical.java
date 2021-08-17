import java.util.List;

public class Petrochemical extends Skill {

    public Petrochemical() {
        super(TargetType.Enemy, 1, 100, "Petrochemical");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Unit target = targetList.get(0);
        Output.printSkillTarget(this.getName(), caster, targetList);
        target.setState(this.getName(), 3);
    }

}
