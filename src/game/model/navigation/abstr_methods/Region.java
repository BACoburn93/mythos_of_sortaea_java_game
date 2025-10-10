package model.navigation.abstr_methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import actors.types.CombatActor;
import characters.Party;
import enemies.Enemy;
import enemies.EnemyFactory;
import model.navigation.EventNode;

public abstract class Region {
    private String name;
    // Store multiple possible event chains, each as a list of EventNodes
    private List<List<EventNode>> eventChains;
    private Map<String, Double> enemyWeights; // enemy type -> spawn weight
    private int maxSpawnWeight = 5;
    private static final Random rng = new Random();

    public Region(String name) {
        this.name = name;
        this.enemyWeights = initEnemyPool();
        this.eventChains = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // pick a random enemy type from those whose spawn weight fits the remaining budget
    public String getRandomEnemyTypeForBudget(int maxAllowedWeight) {
        Map<String, Double> candidates = enemyWeights.entrySet().stream()
            .filter(e -> EnemyFactory.getSpawnWeightForType(e.getKey()) <= maxAllowedWeight)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (candidates.isEmpty()) return null;

        double totalWeight = candidates.values().stream().mapToDouble(Double::doubleValue).sum();
        double roll = rng.nextDouble() * totalWeight;

        double cumulative = 0.0;
        for (Map.Entry<String, Double> entry : candidates.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) return entry.getKey();
        }
        return candidates.keySet().iterator().next();
    }

    public int getMaxSpawnWeight() {
        return maxSpawnWeight;
    }

    public List<String> generateEnemyTypes() {
        List<String> enemyTypes = new ArrayList<>();
        int remainingWeight = maxSpawnWeight;

        while (remainingWeight > 0) {
            String type = getRandomEnemyTypeForBudget(remainingWeight);
            if (type == null) break; // no candidates fit the remaining budget

            int enemyWeight = EnemyFactory.getSpawnWeightForType(type);
            if (enemyWeight <= remainingWeight) {
                enemyTypes.add(type);
                remainingWeight -= enemyWeight;
            } else {
                break;
            }
        }

        return enemyTypes;
    }

    public ArrayList<Enemy> generateEnemies() {
        ArrayList<enemies.Enemy> enemies = new ArrayList<>();
        List<String> enemyTypes = this.generateEnemyTypes();

        for (String enemyType : enemyTypes) {
            Enemy enemy = EnemyFactory.createEnemy(enemyType);
            enemies.add(enemy);
        }

        return enemies; 
    }

    public ArrayList<CombatActor> setupCombatActors(Party party, ArrayList<Enemy> enemies) {
        ArrayList<CombatActor> allActors = new ArrayList<>(party.characters);
        
        allActors.addAll(enemies);

        return allActors;
    }

    // Add an event chain to this region
    public void addEventChain(List<EventNode> chain) {
        eventChains.add(chain);
    }

    // Get all event chains
    public List<List<EventNode>> getEventChains() {
        return eventChains;
    }

    // Optionally, get a random event chain
    public List<EventNode> getRandomEventChain() {
        if (eventChains.isEmpty()) return null;
        return eventChains.get(rng.nextInt(eventChains.size()));
    }

    // Subclasses must implement this to provide their enemy pool
    protected abstract Map<String, Double> initEnemyPool();
}
