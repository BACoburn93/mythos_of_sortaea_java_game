package model.navigation.regions.initializers;

import java.util.Map;

import enemies.EnemyKey;

public class ForestInit {
    public static final Map<String, Double> forestEnemies = new java.util.HashMap<>();
    static {
        forestEnemies.put(EnemyKey.GOBLIN.key(), 0.5);
        forestEnemies.put(EnemyKey.ORC.key(), 0.15);
        forestEnemies.put(EnemyKey.DRAGON.key(), 0.18);
        forestEnemies.put(EnemyKey.MARLBORO.key(), 0.17);
    }
}
