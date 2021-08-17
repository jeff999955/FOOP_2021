import java.util.HashMap;

/**
 * The RPG game will be started from the Main class.
 */
public class Main {
    public static void main(String[] args) {
        HashMap<String, Skill> HMSS = new HashMap<>();
        HashMap<String, State> HMSST = new HashMap<>();
        HMSS.put(BasicAttack.class.getSimpleName(), new BasicAttack());
        HMSS.put(Waterball.class.getSimpleName(), new Waterball());
        HMSS.put(Fireball.class.getSimpleName(), new Fireball());
        HMSS.put(SelfHealing.class.getSimpleName(), new SelfHealing());
        HMSS.put(Petrochemical.class.getSimpleName(), new Petrochemical());
        HMSS.put(Poison.class.getSimpleName(), new Poison());
        HMSS.put(Summon.class.getSimpleName(), new Summon());
        HMSS.put(SelfExplosion.class.getSimpleName(), new SelfExplosion());
        HMSS.put(Cheerup.class.getSimpleName(), new Cheerup());
        HMSS.put(Curse.class.getSimpleName(), new Curse());
        HMSS.put(OnePunch.class.getSimpleName(), new OnePunch());
        HMSST.put("Normal", new NormalState());
        HMSST.put("Petrochemical", new PetrochemicalState());
        HMSST.put("Poisoned", new PoisonState());
        HMSST.put("Cheerup", new CheerupState());
        RPG rpg = new RPG(2, HMSS, HMSST);
        rpg.load();
        rpg.start();
    }
}
