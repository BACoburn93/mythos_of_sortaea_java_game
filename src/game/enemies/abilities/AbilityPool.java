package enemies.abilities;

import abilities.single_target.SingleTargetAbility;

import java.util.*;

public class AbilityPool {
    private final Map<SingleTargetAbility, Integer> weightedAbilities = new LinkedHashMap<>();
    private final Set<SingleTargetAbility> exclusiveAbilities = new HashSet<>();

    public Map<SingleTargetAbility, Integer> getWeightedAbilities() {
        return new LinkedHashMap<>(weightedAbilities);
    }

    public void addWeightedAbility(SingleTargetAbility ability, int weight) {
        weightedAbilities.put(ability, weight);
    }

    public void addExclusiveAbility(SingleTargetAbility ability) {
        exclusiveAbilities.add(ability);
    }

    public SingleTargetAbility getRandomAbility(Random rng) {
        int totalWeight = weightedAbilities.values().stream().mapToInt(i -> i).sum();
        int roll = rng.nextInt(totalWeight) + 1;
        int cumulative = 0;

        for (Map.Entry<SingleTargetAbility, Integer> entry : weightedAbilities.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) {
                return entry.getKey();
            }
        }
        return null; // Shouldn't happen
    }

    public Set<SingleTargetAbility> getExclusiveAbilities() {
        return exclusiveAbilities;
    }

    public List<SingleTargetAbility> getAllWeightedAbilities() {
        return new ArrayList<>(weightedAbilities.keySet());
    }

    public List<SingleTargetAbility> getRandomUniqueAbilities(Random rng, int count) {
        List<SingleTargetAbility> selectedAbilities = new ArrayList<>();
        Map<SingleTargetAbility, Integer> pool = new LinkedHashMap<>(weightedAbilities);

        while (count > 0 && !pool.isEmpty()) {
            // Calculate total weight
            int totalWeight = pool.values().stream().mapToInt(Integer::intValue).sum();

            // Roll between 1 and totalWeight
            int roll = rng.nextInt(totalWeight) + 1;
            int cumulative = 0;

            SingleTargetAbility chosen = null;
            for (Map.Entry<SingleTargetAbility, Integer> entry : pool.entrySet()) {
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
