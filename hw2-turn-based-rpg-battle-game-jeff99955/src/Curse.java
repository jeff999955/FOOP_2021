import java.util.List;

public class Curse extends Skill {

    public Curse() {
        super(TargetType.Enemy, 1, 100, "Curse");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        Unit target = targetList.get(0);
        Output.printSkillTarget(this.getName(), caster, targetList);
        target.addCurseCaster(caster);
    }

}
