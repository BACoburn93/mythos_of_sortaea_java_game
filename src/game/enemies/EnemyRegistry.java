package enemies;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

public final class EnemyRegistry {
    private static final Map<String, Function<String, Enemy>> CONSTRUCTORS = new HashMap<>();

    private EnemyRegistry() {}

    public static void register(String key, Function<String, Enemy> constructor) {
        if (key == null || constructor == null) return;
        CONSTRUCTORS.put(key.trim().toLowerCase(), constructor);
    }

    public static Function<String, Enemy> getConstructor(String key) {
        if (key == null) return null;
        return CONSTRUCTORS.get(key.trim().toLowerCase());
    }

    // convenience overload using enum
    public static Function<String, Enemy> getConstructor(EnemyKey key) {
        return (key == null) ? null : getConstructor(key.key());
    }

    public static java.util.Set<String> allKeys() {
        return java.util.Collections.unmodifiableSet(CONSTRUCTORS.keySet());
    }
}
