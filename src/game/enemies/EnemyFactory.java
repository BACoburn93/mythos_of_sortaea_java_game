package enemies;

import enemies.abilities.AbilityPool;
import enemies.modifiers.Prefix;
import enemies.modifiers.Suffix;
import utils.StringUtils;
import utils.Factory;
import utils.FlavorUtils;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import abilities.ability_types.TargetingAbility;

import java.util.Map;
import java.util.HashMap;

public class EnemyFactory implements Factory<Enemy, Prefix, Suffix> {
    private static final Random rng = new Random();

    private static final Map<String, Function<String, Enemy>> enemyConstructors = new HashMap<>();

    public static Enemy createEnemy(String baseType, Prefix prefix, Suffix suffix) {
        String prefixName = (prefix != null) ? prefix.getName() : "";
        String suffixName = (suffix != null) ? suffix.getName() : "";

        int count = StringUtils.getNextCount(baseType, prefixName, suffixName);
        String displayName = StringUtils.formatName(baseType, prefixName, suffixName, count);

        Function<String, Enemy> constructor = enemyConstructors.get(baseType.toLowerCase());
        if (constructor == null) throw new IllegalArgumentException("Unknown enemy type: " + baseType);
        Enemy enemy = constructor.apply(displayName);

        // Apply modifiers
        if (prefix != null) prefix.apply(enemy);
        if (suffix != null) suffix.apply(enemy);

        // Add randomized and exclusive abilities
        AbilityPool pool = EnemyAbilityPools.getPool(baseType.toLowerCase());
        int maxAbilities = pool.getWeightedAbilities().size();
        int numAbilities = maxAbilities == 0 ? 0 : rng.nextInt(maxAbilities) + 1;

        List<TargetingAbility> abilitiesToAdd = pool.getRandomUniqueAbilities(rng, numAbilities);
        for (TargetingAbility ability : abilitiesToAdd) {
            enemy.addAbility(ability);
        }

        for (var exclusive : pool.getExclusiveAbilities()) {
            enemy.addAbility(exclusive);
        }

        return enemy;
    }

    public static Enemy createEnemy(String baseType) {
        // Lookup constructor for the given baseType from the Registry
        Function<String, Enemy> constructor = EnemyRegistry.getConstructor(baseType);

        if (constructor == null) throw new IllegalArgumentException("Unknown enemy type: " + baseType);

        // Temporarily create enemy to access its prefix/suffix pools
        Enemy enemy = constructor.apply(StringUtils.capitalize(baseType));

        // Acquire random prefix/suffix based on defined chances
        Prefix acquiredPrefix = enemy.acquirePrefix();
        Suffix acquiredSuffix = enemy.acquireSuffix();

        // Set prefix/suffix first so apply() can use them
        enemy.setPrefix(acquiredPrefix);
        enemy.setSuffix(acquiredSuffix);

        // Apply modifiers after setting them
        if (acquiredPrefix != null) {
            acquiredPrefix.apply(enemy);
        }
        if (acquiredSuffix != null) {
            acquiredSuffix.apply(enemy);
        }

        // Add abilities after modifiers are applied
        AbilityPool pool = EnemyAbilityPools.getPool(baseType.toLowerCase());
        int maxAbilities = pool.getWeightedAbilities().size();
        int numAbilities = maxAbilities == 0 ? 0 : rng.nextInt(maxAbilities) + 1;

        // Get random unique abilities from the pool
        List<TargetingAbility> abilitiesToAdd = pool.getRandomUniqueAbilities(rng, numAbilities);
        for (TargetingAbility ability : abilitiesToAdd) {
            enemy.addAbility(ability);
        }

        // Add exclusive abilities
        for (var exclusive : pool.getExclusiveAbilities()) {
            enemy.addAbility(exclusive);
        }

        return enemy;
    }

    public static int getSpawnWeightForType(String type) {
        Function<String, Enemy> constructor = enemyConstructors.get(type.toLowerCase());
        if (constructor == null) return 1; // fallback/default weight
        Enemy tempEnemy = constructor.apply(StringUtils.capitalize(type));
        
        return tempEnemy.getSpawnWeight();
    }

    @Override
    public Enemy create(Supplier<Enemy> ctor, Prefix prefix, Suffix suffix) {
        Enemy e = ctor.get();
        return FlavorUtils.applyFlavor(e, prefix, suffix);
    }
}