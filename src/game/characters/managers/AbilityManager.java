package characters.managers;

import abilities.Ability;
import characters.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public final class AbilityManager {
    private static final AbilityManager INSTANCE = new AbilityManager();
    private final Random rnd = new Random();

    private AbilityManager() {}

    public static AbilityManager getInstance() { return INSTANCE; }

    public List<Ability> getLearnableAbilities(Character c) {
        List<Ability> all = new ArrayList<>(c.getJobObj().getPoolAbilities());
        List<Ability> potential = new ArrayList<>(c.getJobObj().getJobAbilities());
        potential.removeAll(c.getAbilities()); // exclude already known
        for (Ability a : all) {
            boolean known = c.getAbilities().stream().anyMatch(x -> x.getName().equals(a.getName()));
            boolean itemKnown = c.getCharItems() != null && c.getAbilities().stream().anyMatch(x -> x.getName().equals(a.getName()));
            if (!known && !itemKnown && a.getLevelRequirement() <= c.getLevel()) {
                potential.add(a);
            }
        }
        // remove duplicates
        List<Ability> dedup = new ArrayList<>();
        for (Ability a : potential) if (dedup.stream().noneMatch(x -> x.getName().equals(a.getName()))) dedup.add(a);
        return dedup;
    }

    public List<Ability> randomChoices(List<Ability> pool, int n) {
        if (pool == null || pool.isEmpty()) return Collections.emptyList();
        List<Ability> copy = new ArrayList<>(pool);
        Collections.shuffle(copy, rnd);
        return copy.subList(0, Math.min(n, copy.size()));
    }

    // Add ability to character (ensures no duplicates)
    public boolean learnAbility(Character c, Ability ability) {
        if (c == null || ability == null) return false;
        boolean already = c.getAbilities().stream().anyMatch(a -> a.getName().equals(ability.getName()));
        if (already) return false;
        c.addAbility(ability);
        return true;
    }
}
