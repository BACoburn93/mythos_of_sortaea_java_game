package enemies;

import enemies.abilities.AbilityPool;
import enemies.modifiers.Prefix;
import enemies.modifiers.Suffix;
import enemies.types.*;
// import enemies.util.EnemyNameTracker;
import utils.StringUtils;

import java.util.List;
import java.util.Random;

import abilities.single_target.SingleTargetAbility;

public class EnemyFactory {
    private static final Random rng = new Random();

    public static Enemy createEnemy(String baseType, Prefix prefix, Suffix suffix) {
        String prefixName = (prefix != null) ? prefix.getName() : "";
        String suffixName = (suffix != null) ? suffix.getName() : "";

        int count = StringUtils.getNextCount(baseType, prefixName, suffixName);
        String displayName = StringUtils.formatName(baseType, prefixName, suffixName, count);

        Enemy enemy;

        switch (baseType.toLowerCase()) {
            case "goblin" -> enemy = new Goblin(displayName);
            case "orc" -> enemy = new Orc(displayName);
            case "red dragon" -> enemy = new Dragon(displayName);
            default -> throw new IllegalArgumentException("Unknown enemy type: " + baseType);
        }

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

    // public static void resetCounters() {
    //     EnemyNameTracker.reset();
    // }
}
