package actors.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import actors.species.SpeciesType;


public final class SpeciesManager {
    private static final SpeciesManager I = new SpeciesManager();
    public static SpeciesManager getInstance() { return I; }

    // key -> (contextKey -> baseValue)
    private final Map<String, Map<String, Double>> modifiers = new HashMap<>();

    private SpeciesManager() {}

    public void registerModifier(String speciesOrKey, String contextKey, double value) {
        modifiers.computeIfAbsent(speciesOrKey.toUpperCase(), k -> new HashMap<>()).put(contextKey, value);
    }

    public double getModifierBase(String speciesOrKey, String contextKey) {
        var map = modifiers.get(speciesOrKey == null ? "" : speciesOrKey.toUpperCase());
        return (map == null) ? 0.0 : map.getOrDefault(contextKey, 0.0);
    }

    public double getMultiplierForType(SpeciesType target, String contextKey, int attackerSkillTier) {
        if (target == null || contextKey == null) return 1.0;
        String subsKey = target.key();
        double base = getModifierBase(subsKey, contextKey);
        if (base == 0.0) base = getModifierBase(target.getSpecies().name(), contextKey);
        if (base == 0.0) return 1.0;
        return 1.0 + base * Math.max(0, attackerSkillTier);
    }

    public double getDamageMultiplierForSkill(Collection<SpeciesType> targets, String contextKey, int attackerSkillTier) {
        if (targets == null || targets.isEmpty()) return 1.0;
        double combined = 1.0;
        for (SpeciesType st : targets) {
            combined *= getMultiplierForType(st, contextKey, attackerSkillTier);
        }
        return combined;
    }

    // compatibility helper for single species
    public double getDamageMultiplierForSkill(SpeciesType target, String contextKey, int attackerSkillTier) {
        return getMultiplierForType(target, contextKey, attackerSkillTier);
    }
}
