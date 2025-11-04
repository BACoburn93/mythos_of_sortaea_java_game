package characters.jobs.subclasses.mage;

import abilities.database.MageAbilities;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import characters.jobs.subclasses.Subclass;

public class Elementalist extends Subclass {

    public Elementalist() {
        super(); 

        setName("Elementalist");

        // Prestige display names
        registerPrestigeName(1, "Acolyte of Elements");
        registerPrestigeName(2, "Elemental Adept");
        registerPrestigeName(3, "Master Elementalist");
        registerPrestigeName(4, "Archmage of Elements");
        registerPrestigeName(5, "Primal Conduit");

        // Abilities granted per prestige level
        // Level 1: basic spell
        addAbilityToSubclass(MageAbilities.FIREBALL, 1);
        addAbilityToSubclass(MageAbilities.LIGHTNING_BOLT, 1);
        addAbilityToSubclass(MageAbilities.ICE_SPIKE, 1);

        // Level 4: stronger spell / AoE
        addAbilityToSubclass(MageAbilities.BLIZZARD, 4);

        // Level 5: Powerful ultimate abilities
        addAbilityToSubclass(MageAbilities.METEOR_SWARM, 5);

        // Resources and stats per prestige level
        setHealthValues(new HealthValues(10, 1));
        setManaValues(new ManaValues(10, 1));
        setAttributes(new Attributes(0, 0, 2, 1, 1, 0, 0));
        setResistances(new Resistances(-1, -1, -1, 4, 4, 4, 4, -1, 4, 4, -1, -1));
    }
}