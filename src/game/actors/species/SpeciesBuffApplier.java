package actors.species;

import actors.attributes.Attributes;
import actors.types.CombatActor;

import java.util.HashMap;
import java.util.Map;


public final class SpeciesBuffApplier {
    private static final Map<String, Attributes> BUFFS = new HashMap<>();

//  ABERRATION, BEAST, CELESTIAL, CONSTRUCT, DEMON, DEVIL, DRAGON, ELEMENTAL, FEY, GIANT, HUMANOID, INSECT, LYCANTHROPE, OOZE, PLANT, UNDEAD

    static {
        // Aberration subspecies buffs
        BUFFS.put("ABERRATION:HYDRA",      new Attributes(4, 2, 0, 0, 0, 0, 0)); // +4 STR, +2 AGI
        BUFFS.put("ABERRATION:MARLBORO",   new Attributes(0, -2, 4, 0, 0, 0, 0)); // -2 AGI, +4 END
        BUFFS.put("ABERRATION:MIND FLAYER", new Attributes(0, 0, 0, 4, 0, 0, 0)); // +4 KNO

        // Beast subspecies buffs
        BUFFS.put("BEAST:BEAR", new Attributes(3, -1, 2, 0, 0, 0, 0)); // +3 STR, -1 AGI, +2 END
        BUFFS.put("BEAST:BOAR", new Attributes(2, 0, 3, 0, 0, 0, 0)); // +2 STR, +3 END
        BUFFS.put("BEAST:LION", new Attributes(2, 2, 0, 0, 0, 0, 0)); // +2 STR, +2 AGI
        BUFFS.put("BEAST:TIGER", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        BUFFS.put("BEAST:WOLF", new Attributes(0, 3, 0, 0, 0, 0, 0)); // +3 AGI

        // Celestial subspecies buffs
        BUFFS.put("CELESTIAL:ANGEL", new Attributes(0, 0, 0, 2, 0, 0, 0)); // +2 KNO
        BUFFS.put("CELESTIAL:DEVIL", new Attributes(2, 1, 0, 0, 0, 0, 0)); // +2 STR, +1 AGI
        BUFFS.put("CELESTIAL:SERAPH", new Attributes(0, 2, 0, 2, 0, 0, 0)); // +2 AGI, +2 KNO
        BUFFS.put("CELESTIAL:SOLAR", new Attributes(3, 3, 0, 0, 0, 0, 0)); // +3 STR, +3 AGI

        // Construct subspecies buffs
        BUFFS.put("CONSTRUCT:AUTOMATON", new Attributes(2, 0, 3, 0, 0, 0, 0)); // +2 STR, +3 END
        BUFFS.put("CONSTRUCT:GOLEM", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        BUFFS.put("CONSTRUCT:WARFORGED", new Attributes(3, 1, 2, 0, 0, 0, 0)); // +3 STR, +1 AGI, +2 END

        // Demon subspecies buffs
        BUFFS.put("DEMON:HELL HOUND", new Attributes(2, 2, 0, 0, 0, 0, 0)); // +2 STR, +2 AGI
        BUFFS.put("DEMON:IMP", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        BUFFS.put("DEMON:MARILITH", new Attributes(4, 2, 0, 0, 0, 0, 0)); // +4 STR, +2 AGI
        BUFFS.put("DEMON:QUASIT", new Attributes(0, 2, 0, 2, 0, 0, 0)); // +2 AGI, +2 KNO
        BUFFS.put("DEMON:GLABREZU", new Attributes(3, 0, 2, 2, 0, 0, 0)); // +3 STR, +2 END, +2 KNO

        // Devil subspecies buffs
        BUFFS.put("DEVIL:BARBED", new Attributes(3, 1, 2, 0, 0, 0, 0)); // +3 STR, +1 AGI, +2 END
        BUFFS.put("DEVIL:ERINYES", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        BUFFS.put("DEVIL:HORNED", new Attributes(2, 2, 2, 0, 0, 0, 0)); // +2 STR, +2 AGI, +2 END
        BUFFS.put("DEVIL:IMP", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        BUFFS.put("DEVIL:PITFIEND", new Attributes(4, 0, 3, 0, 0, 0, 0)); // +4 STR, +3 END

        // Dragon subspecies buffs
        BUFFS.put("DRAGON:DRAKE", new Attributes(3, 2, 0, 0, 0, 0, 0)); // +3 STR, +2 AGI
        BUFFS.put("DRAGON:DRAGONTURTLE", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        BUFFS.put("DRAGON:WYVERN", new Attributes(2, 3, 0, 0, 0, 0, 0)); // +2 STR, +3 AGI
        BUFFS.put("DRAGON:WYRM", new Attributes(3, 3, 3, 3, 3, 3, 3)); // +3 all

        // Elemental subspecies buffs
        BUFFS.put("ELEMENTAL:AIR ELEMENTAL", new Attributes(0, 4, 0, 0, 0, 0, 0)); // +4 AGI
        BUFFS.put("ELEMENTAL:EARTH ELEMENTAL", new Attributes(4, 0, 4, 0, 0, 0, 0)); // +4 STR, +4 END
        BUFFS.put("ELEMENTAL:FIRE ELEMENTAL", new Attributes(3, 2, 0, 0, 0, 0, 0)); // +3 STR, +2 AGI
        BUFFS.put("ELEMENTAL:WATER ELEMENTAL", new Attributes(0, 2, 3, 0, 0, 0, 0)); // +2 AGI, +3 END

        // Fey subspecies buffs
        BUFFS.put("FEY:DRYAD", new Attributes(0, 2, 0, 2, 0, 0, 0)); // +2 AGI, +2 KNO
        BUFFS.put("FEY:FAIRIE", new Attributes(-1, 4, 0, 0, 0, 0, 0)); // -1 STR, +4 AGI
        BUFFS.put("FEY:SATYR", new Attributes(1, 3, 0, 1, 0, 0, 0)); // +1 STR, +3 AGI, +1 KNO
        BUFFS.put("FEY:SIREN", new Attributes(0, 3, 0, 2, 0, 0, 0)); // +3 AGI, +2 KNO
        BUFFS.put("FEY:WITCH", new Attributes(0, 0, 0, 4, 0, 0, 0)); // +4 KNO

        // Giant subspecies buffs
        BUFFS.put("GIANT:BOG GIANT", new Attributes(4, 0, 3, 0, 0, 0, 0)); // +4 STR, +3 END
        BUFFS.put("GIANT:CYCLOPS", new Attributes(5, -2, 2, 0, 0, 0, 0)); // +5 STR, -2 AGI, +2 END

        // Humanoid subspecies buffs
        BUFFS.put("HUMANOID:DROW",  new Attributes(0, 2, 0, 0, 0, 0, 0)); // +2 AGI
        BUFFS.put("HUMANOID:DWARF", new Attributes(2, 0, 0, 0, 0, 0, 0)); // +2 STR
        BUFFS.put("HUMANOID:ELF",   new Attributes(0, 2, 0, 0, 0, 0, 0)); // +2 AGI
        BUFFS.put("HUMANOID:GNOME", new Attributes(-2, 1, 3, 0, 0, 0, 0)); // -2 STR, +1 AGI, +3 KNO
        BUFFS.put("HUMANOID:GOBLIN", new Attributes(1000, 2, -1, 0, 1, 0, 0)); // +2 AGI, -1 KNO
        BUFFS.put("HUMANOID:HALFLING", new Attributes(-3, 3, 0, -1, 0, 0, 3)); // -3 STR, +3 AGI, -1 END, +3 LCK
        BUFFS.put("HUMANOID:HUMAN", new Attributes(1, 1, 0, 0, 0, 0, 0)); // versatile
        BUFFS.put("HUMANOID:ORC",   new Attributes(3, -1, 0, 0, 0, 0, 0)); // +3 STR, -1 AGI

        // Insect subspecies buffs
        BUFFS.put("INSECT:ANT", new Attributes(1, 3, 0, 0, 0, 0, 0)); // +1 STR, +3 AGI
        BUFFS.put("INSECT:BEETLE", new Attributes(3, 0, 3, 0, 0, 0, 0)); // +3 STR, +3 END
        BUFFS.put("INSECT:WASP", new Attributes(0, 4, 0, 0, 0, 0, 0)); // +4 AGI

        // Lycanthrope subspecies buffs
        BUFFS.put("LYCANTHROPE:WEREBEAR", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        BUFFS.put("LYCANTHROPE:WEREWOLF", new Attributes(2, 3, 0, 0, 0, 0, 0)); // +2 STR, +3 AGI

        // Ooze subspecies buffs
        BUFFS.put("OOZE:BLACK PUDDING", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        BUFFS.put("OOZE:GELATINOUS CUBE", new Attributes(2, -2, 6, 0, 0, 0, 0)); // +2 STR, -2 AGI, +6 END
        BUFFS.put("OOZE:SLIME", new Attributes(0, -2, 6, 0, 0, 0, 0)); // -2 AGI, +6 END
        BUFFS.put("OOZE:STOATHE", new Attributes(3, 0, 3, 0, 0, 0, 0)); // +3 STR, +3 END

        // Plant subspecies buffs
        BUFFS.put("PLANT:SHROOM", new Attributes(0, 0, 2, 2, 0, 0, 0)); // +2 END, +2 KNO
        BUFFS.put("PLANT:TREANT", new Attributes(3, -2, 4, 0, 0, 0, 0)); // +3 STR, -2 AGI, +4 END

        // Undead subspecies buffs
        BUFFS.put("UNDEAD:VAMPIRE", new Attributes(2, 3, 0, 2, 0, 0, 0)); // +2 STR, +3 AGI, +2 KNO
        BUFFS.put("UNDEAD:ZOMBIE", new Attributes(4, -2, 4, 0, 0, 0, 0)); // +4 STR, -2 AGI, +4 END
        BUFFS.put("UNDEAD:SKELETON", new Attributes(2, 0, 2, 2, 0, 0, 0)); // +2 STR, +2 END, +2 KNO
        BUFFS.put("UNDEAD:GHOST", new Attributes(0, 4, 0, 2, 0, 0, 0)); // +4 AGI, +2 KNO
        BUFFS.put("UNDEAD:MUMMY", new Attributes(3, -2, 4, 0, 0, 0, 0)); // +3 STR, -2 AGI, +4 END
        BUFFS.put("UNDEAD:LICH", new Attributes(0, 0, 6, 4, 4, 0, 0)); // +6 KNO, +4 DEF, +4 RES
        
    }

    public static void applySpeciesBuff(CombatActor c, SpeciesType st) {
        if (c == null || st == null) return;
        String key = st.key().toUpperCase();
        Attributes bonus = BUFFS.get(key);
        if (bonus != null) {
            // Attributes.add(...) exists in codebase. This mutates character attributes.
            c.getAttributes().add(bonus);
            System.out.println("[SpeciesBuff] Applied " + key + " -> " + bonus);
        }
    }
}