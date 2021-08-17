import java.util.List;

public class Output {
    public static void Damage(Unit from, Unit to, int dmg) {
        System.out.printf("[%d]%s causes %d damage to [%d]%s.\n", from.getAffiliation(), from.getName(), dmg,
                to.getAffiliation(), to.getName());
    }

    public static void printAvailableTargets(int requiredTarget, List<Unit> candidates) {
        System.out.printf("Select %d targets:", requiredTarget);
        for (int i = 0; i < candidates.size(); ++i)
            System.out.printf(" (%d) %s", i, candidates.get(i));
        System.out.print("\n");
    }

    public static void printDied(Unit u) {
        System.out.printf("%s dies.\n", u);
    }

    public static void printSkillTarget(String name, Unit caster, List<Unit> targetList) {
        System.out.printf("%s uses %s on", caster, name);
        for (int i = 0; i < targetList.size(); i++)
            System.out.printf(" %s%c", targetList.get(i), (i == targetList.size() - 1) ? '.' : ',');
        System.out.print("\n");
    }
}
