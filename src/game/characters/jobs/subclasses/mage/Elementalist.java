package characters.jobs.subclasses.mage;

import abilities.database.MageAbilities;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import characters.jobs.subclasses.Subclass;
import characters.prerequisites.LevelPrerequisite;
import characters.prerequisites.AttributePrerequisite;
import characters.prerequisites.ResistancePrerequisite;
import characters.prerequisites.QuestPrerequisite;
import characters.prerequisites.CharacterExclusivePrerequisite;
import characters.prerequisites.AndPrerequisite;
import characters.prerequisites.OrPrerequisite;

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

        // Example prerequisites for prestige upgrades:
        // Prestige 2: character level >= 5 AND Knowledge >= 30
        addPrerequisite(new AndPrerequisite(
                new LevelPrerequisite(5),
                new AttributePrerequisite("knowledge", 25)
        ), 2);

        // Prestige 3: level >= 10 AND (Knowledge >= 30 OR complete "elemental_trial" quest)
        addPrerequisite(new AndPrerequisite(
                new LevelPrerequisite(10),
                new OrPrerequisite(
                        new AttributePrerequisite("knowledge", 40),
                        new QuestPrerequisite("elemental_trial")
                )
        ), 3);

        // Prestige 4: level >= 15 AND Fire resistance >= 100
        addPrerequisite(new AndPrerequisite(
                new LevelPrerequisite(15),
                new ResistancePrerequisite("earth", 50),
                new ResistancePrerequisite("fire", 50),
                new ResistancePrerequisite("ice", 50),
                new ResistancePrerequisite("lightning", 50),
                new ResistancePrerequisite("water", 50),
                new ResistancePrerequisite("wind", 50)
        ), 4);

        // Prestige 5 (endgame): level >= 20 AND (complete "mastery_of_elements" quest OR character is "Merlin")
        addPrerequisite(new AndPrerequisite(
                new LevelPrerequisite(20),
                new OrPrerequisite(
                    new QuestPrerequisite("mastery_of_elements"),
                    new CharacterExclusivePrerequisite("Merlin")
                )
        ), 5);
    }
}