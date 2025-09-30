package model.navigation.regions.initializers;

import java.util.Map;

public class ForestInit {
    public static final Map<String, Double> forestEnemies = new java.util.HashMap<>();
    static {
        forestEnemies.put("goblin", 0.605);
        forestEnemies.put("orc", 0.005);
        forestEnemies.put("dragon", 0.09);
        forestEnemies.put("marlboro", 0.3);
    }
}
