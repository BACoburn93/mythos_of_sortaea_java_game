package items.equipment.sets;

import actors.Stat;

public class SetRegistrar {
    private SetRegistrar() { }

    public static void init(){
        // --- Register leather set bonuses ---
        // 2-piece: +20% RES
        SetBonus leather2 = new SetBonus();
        leather2.addPercent(Stat.RESILIENCE, .2);
        SetBonusRegistry.register("leather", 2, leather2);

        // 4-piece: +30% AGI
        SetBonus leather4 = new SetBonus();
        leather4.addPercent(Stat.AGILITY, .3);
        leather4.addFlat(Stat.HEALTH_REGEN, 5);
        SetBonusRegistry.register("leather", 4, leather4);


        // --- Register enflamed set bonuses ---
        SetBonus enflamed2 = new SetBonus();
        enflamed2.addPercent(Stat.STRENGTH, .15);
        SetBonusRegistry.register("enflamed", 2, enflamed2);

        SetBonus enflamed4 = new SetBonus();
        enflamed4.addPercent(Stat.KNOWLEDGE, .15);
        SetBonusRegistry.register("enflamed", 4, enflamed4);

        SetBonus enflamed6 = new SetBonus();
        enflamed6.addPercent(Stat.SPIRIT, .2);
        enflamed6.addFlat(Stat.MANA_REGEN, 5);
        SetBonusRegistry.register("enflamed", 6, enflamed6);


        // --- Register ancient set bonuses ---
        SetBonus ancient2 = new SetBonus();
        ancient2.addFlat(Stat.STRENGTH, 15);
        SetBonusRegistry.register("ancient", 2, ancient2);

        SetBonus ancient4 = new SetBonus();
        ancient4.addFlat(Stat.KNOWLEDGE, 15);
        SetBonusRegistry.register("ancient", 4, ancient4);

        SetBonus ancient6 = new SetBonus();
        ancient6.addFlat(Stat.SPIRIT, 20);
        ancient6.addFlat(Stat.HEALTH_REGEN, 10);
        SetBonusRegistry.register("ancient", 6, ancient6);
    }
}
