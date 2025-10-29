package items.equipment.sets;

import actors.Stat;

public class SetRegistrar {
    private SetRegistrar() { }

    public static void init(){
        // --- Register leather set bonuses (example) ---
        // 2-piece: +20% RES
        SetBonus leather2 = new SetBonus();
        leather2.addPercent(Stat.RESILIENCE, .2);
        SetBonusRegistry.register("leather", 2, leather2);

        // 4-piece: +30% AGI
        SetBonus leather4 = new SetBonus();
        leather4.addPercent(Stat.AGILITY, .3);
        leather4.addFlat(Stat.HEALTH_REGEN, 5);
        SetBonusRegistry.register("leather", 4, leather4);
    }
}
