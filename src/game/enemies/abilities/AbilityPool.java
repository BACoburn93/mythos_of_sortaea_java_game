package enemies.abilities;

import abilities.single_target.TargetingAbility;

import java.util.*;

public class AbilityPool {
    private final Map<TargetingAbility, Integer> weightedAbilities = new LinkedHashMap<>();
    private final Set<TargetingAbility> exclusiveAbilities = new HashSet<>();

    public Map<TargetingAbility, Integer> getWeightedAbilities() {
        return new LinkedHashMap<>(weightedAbilities);
    }

    public void addWeightedAbility(TargetingAbility ability, int weight) {
        weightedAbilities.put(ability, weight);
    }

    public void addExclusiveAbility(TargetingAbility ability) {
        exclusiveAbilities.add(ability);
    }

    public TargetingAbility getRandomAbility(Random rng) {
        int totalWeight = weightedAbilities.values().stream().mapToInt(i -> i).sum();
        int roll = rng.nextInt(totalWeight) + 1;
        int cumulative = 0;

        for (Map.Entry<TargetingAbility, Integer> entry : weightedAbilities.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) {
                return entry.getKey();
            }
        }
        return null; // Shouldn't happen
    }

    public Set<TargetingAbility> getExclusiveAbilities() {
        return exclusiveAbilities;
    }

    public List<TargetingAbility> getAllWeightedAbilities() {
        return new ArrayList<>(weightedAbilities.keySet());
    }

    public List<TargetingAbility> getRandomUniqueAbilities(Random rng, int count) {
        List<TargetingAbility> selectedAbilities = new ArrayList<>();
        Map<TargetingAbility, Integer> pool = new LinkedHashMap<>(weightedAbilities);

        while (count > 0 && !pool.isEmpty()) {
            // Calculate total weight
            int totalWeight = pool.values().stream().mapToInt(Integer::intValue).sum();

            // Roll between 1 and totalWeight
            int roll = rng.nextInt(totalWeight) + 1;
            int cumulative = 0;

            TargetingAbility chosen = null;
            for (Map.Entry<TargetingAbility, Integer> entry : pool.entrySet()) {
                cumulative += entry.getValue();
                if (roll <= cumulative) {
                    chosen = entry.getKey();
                    break;
                }
            }

            if (chosen != null) {
                selectedAbilities.add(chosen);
                pool.remove(chosen);  // Remove chosen ability to avoid duplicates
                count--;
            }
        }

        return selectedAbilities;
    }
}
