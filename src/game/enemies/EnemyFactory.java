package enemies;

import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.EnemySuffix;
import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import abilities.enemy.EnemyAbilityPools;

public class EnemyFactory {
    private static final Random rng = new Random();

    // create with explicit prefix/suffix
    public static Enemy createEnemy(String baseType, EnemyPrefix prefix, EnemySuffix suffix) {
        Function<String, Enemy> ctor = EnemyRegistry.getConstructor(baseType);
        if (ctor == null) throw new IllegalArgumentException("Unknown enemy type: " + baseType);

        // compose display name and instantiate with that name
        int count = StringUtils.getNextCount(baseType, (prefix != null) ? prefix.getName() : "", (suffix != null) ? suffix.getName() : "");
        String displayName = StringUtils.formatName(baseType, (prefix != null) ? prefix.getName() : "", (suffix != null) ? suffix.getName() : "", count);
        Enemy enemy = ctor.apply(displayName);

        // apply modifiers
        if (prefix != null) prefix.apply(enemy);
        if (suffix != null) suffix.apply(enemy);

        // assign abilities
        assignAbilities(enemy, baseType.toLowerCase());

        return enemy;
    }

    // create and roll prefix/suffix using metadata in EnemyDatabase
    public static Enemy createEnemy(String baseType) {
        if (baseType == null) throw new IllegalArgumentException("baseType is null");
        String key = baseType.trim().toLowerCase();

        java.util.function.Function<String, Enemy> ctor = EnemyRegistry.getConstructor(key);
        if (ctor == null) {
            throw new IllegalArgumentException(
                "Unknown enemy type: " + baseType +
                " (available: " + String.join(", ", EnemyRegistry.allKeys()) + ")"
            );
        }

        double pChance = EnemyDatabase.getPrefixChance(key);
        double sChance = EnemyDatabase.getSuffixChance(key);

        // initial selection (may be PRE or POST)
        EnemyPrefix chosenP = (rng.nextDouble() < pChance) ? pickWeighted(rng, EnemyDatabase.getPrefixPool(key)) : null;
        EnemySuffix chosenS = (rng.nextDouble() < sChance) ? pickWeighted(rng, EnemyDatabase.getSuffixPool(key)) : null;

        // If the chosen prefix is a POST prefix, pick an additional PRE prefix (exclude the POST pick).
        EnemyPrefix prePrefix = null;
        EnemyPrefix postPrefix = null;
        if (chosenP != null && chosenP.getPosition() == EnemyPrefix.Position.POST) {
            postPrefix = chosenP;
            prePrefix = pickWeightedExcluding(rng, EnemyDatabase.getPrefixPool(key), postPrefix);
        } else {
            // chosenP is PRE (or null) -> use as prePrefix
            prePrefix = chosenP;
        }

        // Compose names: prePrefix (may be null) then postPrefix (may be null)
        String preName = (prePrefix == null) ? "" : prePrefix.getName();
        String postName = (postPrefix == null) ? "" : postPrefix.getName();
        String sName = (chosenS == null) ? "" : chosenS.getName();

        String combinedPrefix = joinNonEmpty(preName, postName);
        int count = StringUtils.getNextCount(baseType, combinedPrefix, sName);
        String displayName = StringUtils.formatName(baseType, combinedPrefix, sName, count);
        Enemy enemy = ctor.apply(displayName);

        // apply prefixes in order: PRE then POST
        if (prePrefix != null) prePrefix.apply(enemy);
        if (postPrefix != null) postPrefix.apply(enemy);

        if (chosenS != null) chosenS.apply(enemy);

        // assign abilities
        assignAbilities(enemy, key);

        return enemy;
    }

    // create with multiple prefixes/suffixes
    public static Enemy createEnemy(String baseType, List<EnemyPrefix> prefixes, List<EnemySuffix> suffixes) {
        if (baseType == null) throw new IllegalArgumentException("baseType is null");
        String key = baseType.trim().toLowerCase();

        Function<String, Enemy> ctor = EnemyRegistry.getConstructor(key);
        if (ctor == null) {
            throw new IllegalArgumentException(
                "Unknown enemy type: " + baseType +
                " (available: " + String.join(", ", EnemyRegistry.allKeys()) + ")"
            );
        }

        String prePName = (prefixes == null || prefixes.isEmpty())
            ? ""
            : prefixes.stream().filter(Objects::nonNull)
                      .filter(p -> p.getPosition() == EnemyPrefix.Position.PRE)
                      .map(EnemyPrefix::getName)
                      .collect(Collectors.joining(" "));
        String postPName = (prefixes == null || prefixes.isEmpty())
            ? ""
            : prefixes.stream().filter(Objects::nonNull)
                      .filter(p -> p.getPosition() == EnemyPrefix.Position.POST)
                      .map(EnemyPrefix::getName)
                      .collect(Collectors.joining(" "));
        String sName = (suffixes == null || suffixes.isEmpty())
            ? ""
            : suffixes.stream().filter(Objects::nonNull).map(EnemySuffix::getName).collect(Collectors.joining(" "));

        String combinedPrefix = joinNonEmpty(prePName, postPName);
        int count = StringUtils.getNextCount(baseType, combinedPrefix, sName);
        String displayName = StringUtils.formatName(baseType, combinedPrefix, sName, count);
        Enemy enemy = ctor.apply(displayName);

        // apply prefixes in order: PRE first, then POST
        if (prefixes != null) {
            prefixes.stream().filter(Objects::nonNull)
                    .filter(p -> p.getPosition() == EnemyPrefix.Position.PRE)
                    .forEach(p -> p.apply(enemy));
            prefixes.stream().filter(Objects::nonNull)
                    .filter(p -> p.getPosition() == EnemyPrefix.Position.POST)
                    .forEach(p -> p.apply(enemy));
        }
        // then apply suffixes as before
        if (suffixes != null) for (EnemySuffix s : suffixes) if (s != null) s.apply(enemy);

        // assign abilities
        assignAbilities(enemy, key);

        return enemy;
    }

    // helper: join two non-empty strings with space
    private static String joinNonEmpty(String... parts) {
        return Arrays.stream(parts)
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.joining(""));
    }

    // Helper: pick a weighted random from a pool
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
    
    // helper: apply abilities from pools to an enemy
    private static void assignAbilities(Enemy enemy, String key) {
        if (enemy == null || key == null) return;
        var pool = EnemyAbilityPools.getPool(key);
        if (pool == null) return;
        int maxAbilities = pool.getWeightedAbilities().size();
        int numAbilities = maxAbilities == 0 ? 0 : rng.nextInt(maxAbilities) + 1;
        var abilitiesToAdd = pool.getRandomUniqueAbilities(rng, numAbilities);
        for (var a : abilitiesToAdd) enemy.addAbility(a);
        for (var exclusive : pool.getExclusiveAbilities()) enemy.addAbility(exclusive);
    }

    public static int getSpawnWeightForType(String type) {
        return EnemyDatabase.getSpawnWeight(type);
    }

    // helper: pick a weighted item from pool but exclude a specified instance (returns null if none available)
    private static <T> T pickWeightedExcluding(java.util.Random rng, java.util.List<EnemyDatabase.Weighted<T>> pool, T exclude) {
        if (pool == null || pool.isEmpty()) return null;
        java.util.List<EnemyDatabase.Weighted<T>> copy = pool.stream()
            .filter(w -> !Objects.equals(w.value, exclude))
            .collect(Collectors.toList());
        if (copy.isEmpty()) return null;

        double total = 0.0;
        for (var w : copy) total += Math.max(0.0, w.weight);
        if (total <= 0.0) return copy.get(0).value;

        double r = rng.nextDouble() * total;
        double acc = 0.0;
        for (var w : copy) {
            acc += Math.max(0.0, w.weight);
            if (r < acc) return w.value;
        }
        return copy.get(copy.size() - 1).value;
    }
}