package enemies;

import enemies.modifiers.Prefix;
import enemies.modifiers.Suffix;
import utils.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

// import enemies.abilities.EnemyAbilityPools;

public class EnemyFactory {
    private static final Random rng = new Random();

    private static <T> T pickWeighted(Random rng, List<EnemyDatabase.Weighted<T>> pool) {
        if (pool == null || pool.isEmpty()) return null;
        double total = 0.0;
        for (var w : pool) total += Math.max(0.0, w.weight);
        if (total <= 0.0) return null;
        double r = rng.nextDouble() * total;
        double acc = 0.0;
        for (var w : pool) {
            acc += Math.max(0.0, w.weight);
            if (r < acc) return w.value;
        }
        return pool.get(pool.size() - 1).value;
    }

    // create with explicit prefix/suffix
    public static Enemy createEnemy(String baseType, Prefix prefix, Suffix suffix) {
        Function<String, Enemy> ctor = EnemyRegistry.getConstructor(baseType);
        if (ctor == null) throw new IllegalArgumentException("Unknown enemy type: " + baseType);

        // compose display name and instantiate with that name
        int count = StringUtils.getNextCount(baseType, (prefix != null) ? prefix.getName() : "", (suffix != null) ? suffix.getName() : "");
        String displayName = StringUtils.formatName(baseType, (prefix != null) ? prefix.getName() : "", (suffix != null) ? suffix.getName() : "", count);
        Enemy enemy = ctor.apply(displayName);

        // apply modifiers
        if (prefix != null) prefix.apply(enemy);
        if (suffix != null) suffix.apply(enemy);

        // assign abilities (existing pool usage)
        var pool = EnemyAbilityPools.getPool(baseType.toLowerCase());
        int maxAbilities = pool.getWeightedAbilities().size();
        int numAbilities = maxAbilities == 0 ? 0 : rng.nextInt(maxAbilities) + 1;
        var abilitiesToAdd = pool.getRandomUniqueAbilities(rng, numAbilities);
        for (var a : abilitiesToAdd) enemy.addAbility(a);
        for (var exclusive : pool.getExclusiveAbilities()) enemy.addAbility(exclusive);

        return enemy;
    }

    // create and roll prefix/suffix using metadata in EnemyDatabase
    public static Enemy createEnemy(String baseType) {
        if (baseType == null) throw new IllegalArgumentException("baseType is null");
        String key = baseType == null ? null : baseType.trim().toLowerCase();

        java.util.function.Function<String, Enemy> ctor = EnemyRegistry.getConstructor(key);
        if (ctor == null) {
            throw new IllegalArgumentException(
                "Unknown enemy type: " + baseType +
                " (available: " + String.join(", ", EnemyRegistry.allKeys()) + ")"
            );
        }

        // decide prefix/suffix
        double pChance = EnemyDatabase.getPrefixChance(key);
        double sChance = EnemyDatabase.getSuffixChance(key);
        Prefix chosenP = (rng.nextDouble() < pChance) ? pickWeighted(rng, EnemyDatabase.getPrefixPool(key)) : null;
        Suffix chosenS = (rng.nextDouble() < sChance) ? pickWeighted(rng, EnemyDatabase.getSuffixPool(key)) : null;

        return createEnemy(key, chosenP, chosenS);
    }

    public static int getSpawnWeightForType(String type) {
        return EnemyDatabase.getSpawnWeight(type);
    }
}