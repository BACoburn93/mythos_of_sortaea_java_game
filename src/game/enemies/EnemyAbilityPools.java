package enemies;

import abilities.database.AbilityDatabase;
import enemies.abilities.AbilityPool;

import java.util.HashMap;
import java.util.Map;

public class EnemyAbilityPools {
    private static final Map<String, AbilityPool> pools = new HashMap<>();

    static {
        AbilityPool goblinPool = new AbilityPool();
        goblinPool.addWeightedAbility(AbilityDatabase.FLASH_BANG, 60);
        goblinPool.addWeightedAbility(AbilityDatabase.PUNCH, 40);

        AbilityPool orcPool = new AbilityPool();
        orcPool.addWeightedAbility(AbilityDatabase.PUNCH, 70);
        orcPool.addWeightedAbility(AbilityDatabase.KICK, 30);

        AbilityPool dragonPool = new AbilityPool();
        dragonPool.addExclusiveAbility(AbilityDatabase.FIRE_BREATH);
        dragonPool.addWeightedAbility(AbilityDatabase.BITE, 40);
        dragonPool.addWeightedAbility(AbilityDatabase.TAIL, 30);
        dragonPool.addWeightedAbility(AbilityDatabase.CLAW, 30);

        pools.put("goblin", goblinPool);
        pools.put("orc", orcPool);
        pools.put("dragon", dragonPool);
    }

    public static AbilityPool getPool(String enemyType) {
        return pools.get(enemyType.toLowerCase());
    }
}
