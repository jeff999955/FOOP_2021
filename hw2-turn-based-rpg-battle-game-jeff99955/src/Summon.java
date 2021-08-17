import java.util.List;

public class Summon extends Skill {

    public Summon() {
        super(TargetType.None, 0, 150, "Summon");
    }

    @Override
    protected void perform(Unit caster, List<Unit> targetList) {
        System.out.printf("%s uses %s.\n", caster, this.getName());
        Unit suraimu = new Unit("Slime", 100, 0, 50, caster.getTroop(), caster.states);
        caster.getTroop().addMember(suraimu);
    }

}
