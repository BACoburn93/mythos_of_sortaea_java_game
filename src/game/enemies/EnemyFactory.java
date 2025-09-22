package enemies;

import enemies.modifiers.Prefix;
import enemies.modifiers.Suffix;
import enemies.types.*;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {
    private static final Map<String, Integer> enemyTypeCount = new HashMap<>();

    public static Enemy createEnemy(String baseType, Prefix prefix, Suffix suffix) {
        String key = baseType.toLowerCase();
        int count = enemyTypeCount.getOrDefault(key, 0) + 1;
        enemyTypeCount.put(key, count);

        String displayName = formatEnemyName(baseType, count);
        Enemy enemy;

        // Instantiate base enemy
        switch (key) {
            case "goblin" -> enemy = new Goblin(displayName);
            case "orc" -> enemy = new Orc(displayName);
            case "red dragon" -> enemy = new Dragon(displayName);
            default -> throw new IllegalArgumentException("Unknown enemy type: " + baseType);
        }

        // Apply modifiers
        if (prefix != null) prefix.apply(enemy);
        if (suffix != null) suffix.apply(enemy);

        return enemy;
    }

    private static String formatEnemyName(String baseType, int count) {
        int existingCount = enemyTypeCount.getOrDefault(baseType.toLowerCase(), 0);
        return existingCount > 1 ? baseType + " #" + existingCount : baseType;
    }

    public static void resetCounters() {
        enemyTypeCount.clear();
    }
}
