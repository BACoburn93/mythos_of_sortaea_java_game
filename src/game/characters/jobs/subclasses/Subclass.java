package characters.jobs.subclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abilities.Ability;
import characters.Character;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import characters.managers.AbilityManager;

public abstract class Subclass {
    private String name;
    private int prestigeLevel = 1;
    private String prestigeName;
    private HealthValues healthValues;
    private ManaValues manaValues;
    private Attributes attributes;
    private Resistances resistances;
    private final Map<Integer, List<Ability>> abilitiesByPrestige = new HashMap<>();
    private final Map<Integer, String> prestigeNameMap = new HashMap<>();

    // to do - create a prerequisites system for subclasses
    // e.g. requires level 10 Mage, requires x knowledge, requires specific quest completed, etc.
    // Have requirements for each prestige level as well.

    // Getters/Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrestigeLevel() {
        return prestigeLevel;
    }

    // internal setter that clamps
    private void setPrestigeLevelInternal(int prestigeLevel) {
        this.prestigeLevel = Math.max(1, Math.min(prestigeLevel, 5)); // Clamp between 1 and 5
    }

    public String getPrestigeName() {
        return prestigeName;
    }

    public void setPrestigeName(String prestigeName) {
        this.prestigeName = prestigeName;
    }

    public HealthValues getHealthValues() {
        return healthValues;
    }

    public void setHealthValues(HealthValues healthValues) {
        this.healthValues = healthValues;
    }

    public ManaValues getManaValues() {
        return manaValues;
    }

    public void setManaValues(ManaValues manaValues) {
        this.manaValues = manaValues;
    }
    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Resistances getResistances() {
        return resistances;
    }

    public void setResistances(Resistances resistances) {
        this.resistances = resistances;
    }

     // Get all registered abilities for all prestige levels (flattened).
    public List<Ability> getAbilities() {
        List<Ability> out = new ArrayList<>();
        for (var entry : abilitiesByPrestige.entrySet()) {
            if (entry.getValue() != null) out.addAll(entry.getValue());
        }
        return out;
    }

    // Helper Methods

    // Register an ability for a specific prestige level (1.. n). 
    public void addAbilityToSubclass(Ability ability, int forPrestigeLevel) {
        if (ability == null) return;
        int lvl = Math.max(1, forPrestigeLevel);
        var list = abilitiesByPrestige.computeIfAbsent(lvl, k -> new ArrayList<>());
        if (!list.contains(ability)) list.add(ability);
    }
    
    // Convenience: register for prestige level 1. 
    public void addAbilityToSubclass(Ability ability) {
        addAbilityToSubclass(ability, 1);
    }

    //  Grant abilities to the character up to the current prestigeLevel.
    //  Only abilities registered for levels <= prestigeLevel are granted.
    public void grantAbilitiesForCharacter(Character character) {
        if (character == null) return;
        for (int lvl = 1; lvl <= this.prestigeLevel; lvl++) {
            List<Ability> list = abilitiesByPrestige.get(lvl);
            if (list == null) continue;
            for (Ability abil : list) {
                try {
                    AbilityManager.getInstance().learnAbility(character, abil);
                } catch (Throwable ignored) { }
            }
        }
    }

    // Grant a single ability immediately to the character. 
    public void addAbility(Character character, Ability ability) {
        if (character == null || ability == null) return;
        try { AbilityManager.getInstance().learnAbility(character, ability); } catch (Throwable ignored) { }
    }
    
    // Register a display name for a prestige level. 
    public void registerPrestigeName(int level, String name) {
        if (level <= 0 || name == null) return;
        prestigeNameMap.put(level, name);
    }

    public void addCharacterValuesFromSubclass(Character character) {
        if (character == null) return;
        if (healthValues != null) {
            character.increaseMaxHealth(healthValues.getMaxValue());
            character.increaseHealthRegen(healthValues.getRegenValue());
        }
        if (manaValues != null) {
            character.increaseMaxMana(manaValues.getMaxValue());
            character.increaseManaRegen(manaValues.getRegenValue());
        }
        if (attributes != null) character.getAttributes().add(attributes);
        if (resistances != null) character.getResistances().add(resistances);
    }
    

    public void applyPrestigeIncrease(Character character, int newPrestigeLevel) {
        if (character == null) return;
        int clamped = Math.max(1, Math.min(newPrestigeLevel, 5));
        if (clamped == this.prestigeLevel) return;
        int old = this.prestigeLevel;
        int delta = clamped - old;
        if (delta <= 0) {
            // downgrade not supported yet (may not be needed)
            setPrestigeLevelInternal(clamped);
            updatePrestigeName();
            return;
        }

        // apply subclass values delta times (per-level increments)
        for (int i = 0; i < delta; i++) {
            if (healthValues != null) {
                character.increaseMaxHealth(healthValues.getMaxValue());
                character.increaseHealthRegen(healthValues.getRegenValue());
            }
            if (manaValues != null) {
                character.increaseMaxMana(manaValues.getMaxValue());
                character.increaseManaRegen(manaValues.getRegenValue());
            }
            if (attributes != null) character.getAttributes().add(attributes);
            if (resistances != null) character.getResistances().add(resistances);
        }

        // update prestige level
        setPrestigeLevelInternal(clamped);
        // update prestige name
        updatePrestigeName();

        // grant abilities for the newly reached levels (grant for levels > old and <= new)
        for (int lvl = old + 1; lvl <= clamped; lvl++) {
            List<Ability> list = abilitiesByPrestige.get(lvl);
            if (list == null) continue;
            for (Ability abil : list) {
                try { AbilityManager.getInstance().learnAbility(character, abil); } catch (Throwable ignored) {}
            }
        }
    }

    private void updatePrestigeName() {
        String n = prestigeNameMap.get(this.prestigeLevel);
        if (n != null) this.prestigeName = n;
        else this.prestigeName = this.name + " (Prestige " + this.prestigeLevel + ")";
    }
}
