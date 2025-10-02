package enemies;

import enemies.types.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EnemyRegistry {
    private static final Map<String, Function<String, Enemy>> enemyConstructors = new HashMap<>();
    static {
        enemyConstructors.put("goblin", Goblin::new);
        enemyConstructors.put("orc", Orc::new);
        enemyConstructors.put("dragon", Dragon::new);
        enemyConstructors.put("marlboro", Marlboro::new);
    }

    public static Function<String, Enemy> getConstructor(String type) {
        return enemyConstructors.get(type.toLowerCase());
    }
}
