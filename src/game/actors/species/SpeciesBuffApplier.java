package actors.species;

import actors.attributes.Attributes;
import characters.Character;

import java.util.HashMap;
import java.util.Map;


public final class SpeciesBuffApplier {
    private static final Map<String, Attributes> BUFFS = new HashMap<>();

    static {

        BUFFS.put("HUMANOID:DWARF", new Attributes(2, 0, 0, 0, 0, 0, 0)); // +2 STR
        BUFFS.put("HUMANOID:ELF",   new Attributes(0, 2, 0, 0, 0, 0, 0)); // +2 AGI
        BUFFS.put("HUMANOID:HUMAN", new Attributes(1, 1, 0, 0, 0, 0, 0)); // versatile
        BUFFS.put("HUMANOID:ORC",   new Attributes(3, -1, 0, 0, 0, 0, 0)); // +3 STR, -1 AGI
        BUFFS.put("HUMANOID:GOBLIN", new Attributes(0, 2, -1, 0, 1, 0, 0)); // +2 AGI, -1 KNO
        BUFFS.put("HUMANOID:GNOME", new Attributes(-2, 1, 3, 0, 0, 0, 0)); // -2 STR, +1 AGI, +3 KNO
        
    }

    public static void applySpeciesBuff(Character c, SpeciesType st) {
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