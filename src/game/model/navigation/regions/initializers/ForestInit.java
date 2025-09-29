package model.navigation.regions.initializers;

import java.util.Map;

public class ForestInit {
    public static final Map<String, Double> forestEnemies = new java.util.HashMap<>();
    static {
        forestEnemies.put("goblin", 0.4);
        forestEnemies.put("orc", 0.15);
        forestEnemies.put("dragon", 0.1);
    }
}
