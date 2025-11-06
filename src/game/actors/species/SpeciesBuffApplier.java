package actors.species;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.types.CombatActor;

import java.util.HashMap;
import java.util.Map;


public final class SpeciesBuffApplier {
    private static final Map<String, Attributes> ATTR_BUFFS = new HashMap<>();
    private static final Map<String, Resistances> RESISTANCE_BUFFS = new HashMap<>();

//  ABERRATION, ANIMAL, CELESTIAL, CONSTRUCT, DEMON, DEVIL, DRAGON, ELEMENTAL, FAERIE, GIANT, HUMANOID, INSECT, LYCANTHROPE, OOZE, PLANT, UNDEAD

    static {
        // Aberration subspecies buffs
        // Hydra
        ATTR_BUFFS.put("ABERRATION:HYDRA", new Attributes(4, 2, 0, 3, 3, 0, 2)); // +4 STR, +2 AGI, +3 END, +3 KNO, +2 LCK
        RESISTANCE_BUFFS.put("ABERRATION:HYDRA", new Resistances(2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0));

        ATTR_BUFFS.put("ABERRATION:COGANOS", new Attributes(0, -2, 4, 0, 0, 0, 0)); // -2 AGI, +4 END
        ATTR_BUFFS.put("ABERRATION:MIND FLAYER", new Attributes(0, 0, 0, 4, 0, 0, 0)); // +4 KNO

        // Animal subspecies buffs
        ATTR_BUFFS.put("ANIMAL:BEAR", new Attributes(3, -1, 2, 0, 0, 0, 0)); // +3 STR, -1 AGI, +2 END
        ATTR_BUFFS.put("ANIMAL:BOAR", new Attributes(2, 0, 3, 0, 0, 0, 0)); // +2 STR, +3 END
        ATTR_BUFFS.put("ANIMAL:LION", new Attributes(2, 2, 0, 0, 0, 0, 0)); // +2 STR, +2 AGI
        ATTR_BUFFS.put("ANIMAL:TIGER", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        ATTR_BUFFS.put("ANIMAL:WOLF", new Attributes(0, 3, 0, 0, 0, 0, 0)); // +3 AGI

        // Celestial subspecies buffs
        ATTR_BUFFS.put("CELESTIAL:ANGEL", new Attributes(0, 0, 0, 2, 0, 0, 0)); // +2 KNO
        ATTR_BUFFS.put("CELESTIAL:DEVIL", new Attributes(2, 1, 0, 0, 0, 0, 0)); // +2 STR, +1 AGI
        ATTR_BUFFS.put("CELESTIAL:SERAPH", new Attributes(0, 2, 0, 2, 0, 0, 0)); // +2 AGI, +2 KNO
        ATTR_BUFFS.put("CELESTIAL:SOLAR", new Attributes(3, 3, 0, 0, 0, 0, 0)); // +3 STR, +3 AGI

        // Construct subspecies buffs
        ATTR_BUFFS.put("CONSTRUCT:AUTOMATON", new Attributes(2, 0, 3, 0, 0, 0, 0)); // +2 STR, +3 END
        ATTR_BUFFS.put("CONSTRUCT:GOLEM", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        ATTR_BUFFS.put("CONSTRUCT:WARFORGED", new Attributes(3, 1, 2, 0, 0, 0, 0)); // +3 STR, +1 AGI, +2 END

        // Demon subspecies buffs
        ATTR_BUFFS.put("DEMON:HELL HOUND", new Attributes(2, 2, 0, 0, 0, 0, 0)); // +2 STR, +2 AGI
        ATTR_BUFFS.put("DEMON:IMP", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        ATTR_BUFFS.put("DEMON:MARILITH", new Attributes(4, 2, 0, 0, 0, 0, 0)); // +4 STR, +2 AGI
        ATTR_BUFFS.put("DEMON:QUASIT", new Attributes(0, 2, 0, 2, 0, 0, 0)); // +2 AGI, +2 KNO
        ATTR_BUFFS.put("DEMON:GLABREZU", new Attributes(3, 0, 2, 2, 0, 0, 0)); // +3 STR, +2 END, +2 KNO

        // Devil subspecies buffs
        ATTR_BUFFS.put("DEVIL:BARBED", new Attributes(3, 1, 2, 0, 0, 0, 0)); // +3 STR, +1 AGI, +2 END
        ATTR_BUFFS.put("DEVIL:ERINYES", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        ATTR_BUFFS.put("DEVIL:HORNED", new Attributes(2, 2, 2, 0, 0, 0, 0)); // +2 STR, +2 AGI, +2 END
        ATTR_BUFFS.put("DEVIL:IMP", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        ATTR_BUFFS.put("DEVIL:PITFIEND", new Attributes(4, 0, 3, 0, 0, 0, 0)); // +4 STR, +3 END

        // Dragon subspecies buffs
        ATTR_BUFFS.put("DRAGON:DRAKE", new Attributes(3, 2, 0, 0, 0, 0, 0)); // +3 STR, +2 AGI
        ATTR_BUFFS.put("DRAGON:DRAGONTURTLE", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        ATTR_BUFFS.put("DRAGON:WYVERN", new Attributes(2, 3, 0, 0, 0, 0, 0)); // +2 STR, +3 AGI
        // Wyrm
        ATTR_BUFFS.put("DRAGON:WYRM", new Attributes(3, 3, 3, 3, 3, 3, 3)); // +3 all
        RESISTANCE_BUFFS.put("DRAGON:WYRM", new Resistances(5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0));

        // Elemental subspecies buffs
        ATTR_BUFFS.put("ELEMENTAL:AIR ELEMENTAL", new Attributes(0, 4, 0, 0, 0, 0, 0)); // +4 AGI
        ATTR_BUFFS.put("ELEMENTAL:EARTH ELEMENTAL", new Attributes(4, 0, 4, 0, 0, 0, 0)); // +4 STR, +4 END
        ATTR_BUFFS.put("ELEMENTAL:FIRE ELEMENTAL", new Attributes(3, 2, 0, 0, 0, 0, 0)); // +3 STR, +2 AGI
        ATTR_BUFFS.put("ELEMENTAL:WATER ELEMENTAL", new Attributes(0, 2, 3, 0, 0, 0, 0)); // +2 AGI, +3 END

        // Fey subspecies buffs
        ATTR_BUFFS.put("FAERIE:DRYAD", new Attributes(0, 2, 0, 2, 0, 0, 0)); // +2 AGI, +2 KNO
        ATTR_BUFFS.put("FAERIE:FAIRIE", new Attributes(-1, 4, 0, 0, 0, 0, 0)); // -1 STR, +4 AGI
        ATTR_BUFFS.put("FAERIE:SATYR", new Attributes(1, 3, 0, 1, 0, 0, 0)); // +1 STR, +3 AGI, +1 KNO
        ATTR_BUFFS.put("FAERIE:SIREN", new Attributes(0, 3, 0, 2, 0, 0, 0)); // +3 AGI, +2 KNO
        ATTR_BUFFS.put("FAERIE:WITCH", new Attributes(0, 0, 0, 4, 0, 0, 0)); // +4 KNO

        // Giant subspecies buffs
        ATTR_BUFFS.put("GIANT:BOG GIANT", new Attributes(4, 0, 3, 0, 0, 0, 0)); // +4 STR, +3 END
        ATTR_BUFFS.put("GIANT:CYCLOPS", new Attributes(5, -2, 2, 0, 0, 0, 0)); // +5 STR, -2 AGI, +2 END

        // Humanoid subspecies buffs
        ATTR_BUFFS.put("HUMANOID:DROW", new Attributes(0, 2, 0, 0, 0, 0, 0)); // +2 AGI
        ATTR_BUFFS.put("HUMANOID:DWARF", new Attributes(2, 0, 0, 0, 0, 0, 0)); // +2 STR
        ATTR_BUFFS.put("HUMANOID:ELF", new Attributes(0, 2, 0, 0, 0, 0, 0)); // +2 AGI
        ATTR_BUFFS.put("HUMANOID:GNOME", new Attributes(-2, 1, 3, 0, 0, 0, 0)); // -2 STR, +1 AGI, +3 KNO
        ATTR_BUFFS.put("HUMANOID:GOBLIN", new Attributes(1, 2, -1, 0, 1, 0, 0)); // +2 AGI, -1 KNO
        ATTR_BUFFS.put("HUMANOID:HALFLING", new Attributes(-3, 3, 0, -1, 0, 0, 3)); // -3 STR, +3 AGI, -1 END, +3 LCK
        ATTR_BUFFS.put("HUMANOID:HUMAN", new Attributes(1, 1, 0, 0, 0, 0, 0)); // versatile
        ATTR_BUFFS.put("HUMANOID:ORC", new Attributes(3, -1, 0, 0, 0, 0, 0)); // +3 STR, -1 AGI

        // Insect subspecies buffs
        ATTR_BUFFS.put("INSECT:ANT", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        ATTR_BUFFS.put("INSECT:BEETLE", new Attributes(3, 0, 3, 0, 0, 0, 0)); // +3 STR, +3 END
        ATTR_BUFFS.put("INSECT:WASP", new Attributes(0, 4, 0, 0, 0, 0, 0)); // +4 AGI

        // Lycanthrope subspecies buffs
        ATTR_BUFFS.put("LYCANTHROPE:WEREBEAR", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        ATTR_BUFFS.put("LYCANTHROPE:WEREWOLF", new Attributes(2, 3, 0, 0, 0, 0, 0)); // +2 STR, +3 AGI

        // Ooze subspecies buffs
        ATTR_BUFFS.put("OOZE:BLACK PUDDING", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        ATTR_BUFFS.put("OOZE:GELATINOUS CUBE", new Attributes(2, -2, 6, 0, 0, 0, 0)); // +2 STR, -2 AGI, +6 END
        ATTR_BUFFS.put("OOZE:SLIME", new Attributes(0, -2, 6, 0, 0, 0, 0)); // -2 AGI, +6 END
        ATTR_BUFFS.put("OOZE:STOATHE", new Attributes(3, 0, 3, 0, 0, 0, 0)); // +3 STR, +3 END

        // Plant subspecies buffs
        ATTR_BUFFS.put("PLANT:SHROOM", new Attributes(0, 0, 2, 2, 0, 0, 0)); // +2 END, +2 KNO
        ATTR_BUFFS.put("PLANT:TREANT", new Attributes(3, -2, 4, 0, 0, 0, 0)); // +3 STR, -2 AGI, +4 END

        // Undead subspecies buffs
        ATTR_BUFFS.put("UNDEAD:VAMPIRE", new Attributes(2, 3, 0, 2, 0, 0, 0)); // +2 STR, +3 AGI, +2 KNO
        ATTR_BUFFS.put("UNDEAD:ZOMBIE", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        ATTR_BUFFS.put("UNDEAD:SKELETON", new Attributes(2, 0, 2, 2, 0, 0, 0)); // +2 STR, +2 END, +2 KNO
        ATTR_BUFFS.put("UNDEAD:GHOST", new Attributes(0, 4, 0, 2, 0, 0, 0)); // +4 AGI, +2 KNO
        ATTR_BUFFS.put("UNDEAD:MUMMY", new Attributes(3, -2, 4, 0, 0, 0, 0)); // +3 STR, -2 AGI, +4 END
        ATTR_BUFFS.put("UNDEAD:LICH", new Attributes(0, 0, 6, 4, 4, 0, 0)); // +6 KNO, +4 DEF, +4 RES
        
    }

    public static void applySpeciesBuff(CombatActor c, SpeciesType st) {
        if (c == null || st == null) return;
        String key = st.key().toUpperCase();
        Attributes attrBonus = ATTR_BUFFS.get(key);
        Resistances resBonus = RESISTANCE_BUFFS.get(key);

        if (attrBonus != null) c.getAttributes().add(attrBonus);
        if (resBonus != null) c.getResistances().add(resBonus);
    }
}