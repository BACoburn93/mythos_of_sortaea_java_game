package enemies;

import enemies.abilities.AbilityPool;
import enemies.modifiers.Prefix;
import enemies.modifiers.Suffix;
import enemies.modifiers.prefixes.*;
import enemies.modifiers.suffixes.*;
import enemies.types.*;
// import enemies.util.EnemyNameTracker;
import utils.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

import abilities.single_target.SingleTargetAbility;

public class EnemyFactory {
    private static final Random rng = new Random();

    // Registry for enemy constructors
    private static final Map<String, Function<String, Enemy>> enemyConstructors = new HashMap<>();
    static {
        enemyConstructors.put("goblin", Goblin::new);
        enemyConstructors.put("orc", Orc::new);
        enemyConstructors.put("dragon", Dragon::new);
    }

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

        List<SingleTargetAbility> abilitiesToAdd = pool.getRandomUniqueAbilities(rng, numAbilities);
        for (SingleTargetAbility ability : abilitiesToAdd) {
            enemy.addAbility(ability);
        }

        for (var exclusive : pool.getExclusiveAbilities()) {
            enemy.addAbility(exclusive);
        }

        return enemy;
    }

    public static Enemy createEnemy(String baseType) {
        Function<String, Enemy> constructor = enemyConstructors.get(baseType.toLowerCase());
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
        List<SingleTargetAbility> abilitiesToAdd = pool.getRandomUniqueAbilities(rng, numAbilities);
        for (SingleTargetAbility ability : abilitiesToAdd) {
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
}