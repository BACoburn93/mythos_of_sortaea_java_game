package enemies;

import java.util.function.Function;

public enum EnemyKey {
    GOBLIN("goblin"),
    ORC("orc"),
    COGANOS("coganos"),
    DRAGON("dragon");

    private final String key;
    EnemyKey(String key) { this.key = key; }
    public String key() { return key; }

    public Function<String, Enemy> supplier() { return EnemyRegistry.getConstructor(this); }

    @Override public String toString() { return key; }
}
