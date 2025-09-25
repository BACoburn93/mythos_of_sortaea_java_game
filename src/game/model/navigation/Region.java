package model.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import actors.types.CombatActor;
import characters.Party;
import enemies.Enemy;
import enemies.EnemyFactory;

public class Region {
    private String name;
    private Map<String, Double> enemyWeights; // enemy type -> spawn weight
    private int maxSpawnWeight = 4;
    private static final Random rng = new Random();

    public Region(String name, Map<String, Double> enemyWeights) {
        this.name = name;
        this.enemyWeights = enemyWeights;
    }

    public String getName() {
        return name;
    }

    public String getRandomEnemyType() {
        double totalWeight = enemyWeights.values().stream().mapToDouble(Double::doubleValue).sum();
        double roll = rng.nextDouble() * totalWeight;
        double cumulative = 0.0;

        // Iterate through the map to find the enemy type corresponding to the roll
        for (Map.Entry<String, Double> entry : enemyWeights.entrySet()) {
            cumulative += entry.getValue();
            if (roll <= cumulative) {
                return entry.getKey();
            }
        }
        // fallback
        return enemyWeights.keySet().iterator().next();
    }

    public int getMaxSpawnWeight() {
        return maxSpawnWeight;
    }

    public List<String> generateEnemyTypes() {
        List<String> enemyTypes = new ArrayList<>();
        int remainingWeight = maxSpawnWeight;

        while (remainingWeight > 0) {
            String type = getRandomEnemyType();
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
}
