package characters.leveling;

import characters.Character;
import characters.jobs.Job;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;

import java.util.*;

public final class LevelScaler {

    public static final class LevelDelta {
        public final Attributes attributes;
        public final Resistances resistances;
        public final HealthValues healthValues;
        public final ManaValues manaValues;

        public LevelDelta(Attributes attributes, Resistances resistances,
                          HealthValues healthValues, ManaValues manaValues) {
            this.attributes = attributes;
            this.resistances = resistances;
            this.healthValues = healthValues;
            this.manaValues = manaValues;
        }

        public static LevelDelta none() {
            return new LevelDelta(null, null, null, null);
        }
    }

    public static final class NthModifier {
        public final int everyNLevels;
        public final LevelDelta delta;
        public NthModifier(int everyNLevels, LevelDelta delta) {
            this.everyNLevels = Math.max(1, everyNLevels);
            this.delta = delta;
        }
    }

    public static final class LevelProgression {
        public final LevelDelta basePerLevel;
        public final Map<Integer, LevelDelta> overrides;
        public final List<NthModifier> nthModifiers;

        public LevelProgression(LevelDelta basePerLevel) {
            this.basePerLevel = basePerLevel == null ? LevelDelta.none() : basePerLevel;
            this.overrides = new HashMap<>();
            this.nthModifiers = new ArrayList<>();
        }

        public LevelProgression overrideLevel(int level, LevelDelta delta) {
            if (level > 0 && delta != null) overrides.put(level, delta);
            return this;
        }

        public LevelProgression addNthModifier(NthModifier m) {
            if (m != null) nthModifiers.add(m);
            return this;
        }
    }

    private static final Map<String, LevelProgression> registry = new HashMap<>();

    public static void register(String jobType, LevelProgression progression) {
        if (jobType == null || progression == null) return;
        registry.put(jobType, progression);
    }

    public static LevelProgression get(String jobType) {
        return registry.get(jobType);
    }


    public static void applyLevelUp(Character character, Job job, int oldLevel, int newLevel) {
        if (character == null || job == null) return;
        String jt = job.getName();
        LevelProgression prog = registry.get(jt);
        if (prog == null) return;

        int from = Math.max(0, oldLevel) + 1;
        int to = Math.max(from, newLevel);

        for (int lvl = from; lvl <= to; lvl++) {
            // 1) base per-level
            applyDeltaToCharacter(character, prog.basePerLevel);

            // 2) any override for this specific level
            LevelDelta override = prog.overrides.get(lvl);
            if (override != null) applyDeltaToCharacter(character, override);

            // 3) nth modifiers
            for (NthModifier nm : prog.nthModifiers) {
                if (nm.everyNLevels > 0 && (lvl % nm.everyNLevels) == 0) {
                    applyDeltaToCharacter(character, nm.delta);
                }
            }
        }
    }

    private static void applyDeltaToCharacter(Character c, LevelDelta delta) {
        if (delta == null) return;
        try {
            if (delta.healthValues != null) {
                c.increaseMaxHealth(delta.healthValues.getMaxValue());
                c.increaseHealthRegen(delta.healthValues.getRegenValue());
            }
        } catch (Throwable ignored) {}
        try {
            if (delta.manaValues != null) {
                c.increaseMaxMana(delta.manaValues.getMaxValue());
                c.increaseManaRegen(delta.manaValues.getRegenValue());
            }
        } catch (Throwable ignored) {}
        try {
            if (delta.attributes != null) c.getAttributes().add(delta.attributes);
        } catch (Throwable ignored) {}
        try {
            if (delta.resistances != null) c.getResistances().add(delta.resistances);
        } catch (Throwable ignored) {}
    }
}