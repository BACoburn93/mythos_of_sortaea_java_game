package abilities.enemy;

import abilities.database.AbilityDatabase;
import enemies.EnemyKey;

import java.util.HashMap;
import java.util.Map;

public class EnemyAbilityPools {
    private static final Map<String, EnemyAbilityPool> pools = new HashMap<>();

    static {
        EnemyAbilityPool goblinPool = new EnemyAbilityPool();
        goblinPool.addWeightedAbility(AbilityDatabase.FLASH_BANG, 10);
        goblinPool.addWeightedAbility(AbilityDatabase.PUNCH, 90);

        EnemyAbilityPool orcPool = new EnemyAbilityPool();
        orcPool.addWeightedAbility(AbilityDatabase.FLASH_BANG, 20);
        orcPool.addWeightedAbility(AbilityDatabase.PUNCH, 40);
        orcPool.addWeightedAbility(AbilityDatabase.KICK, 40);

        EnemyAbilityPool dragonPool = new EnemyAbilityPool();
        dragonPool.addExclusiveAbility(AbilityDatabase.DRAGON_BREATH);
        dragonPool.addWeightedAbility(AbilityDatabase.BITE, 40);
        dragonPool.addWeightedAbility(AbilityDatabase.TAIL, 30);
        dragonPool.addWeightedAbility(AbilityDatabase.CLAW, 30);

        EnemyAbilityPool marlboroPool = new EnemyAbilityPool();
        marlboroPool.addWeightedAbility(AbilityDatabase.ROTTING_TENTACLE, 40);
        marlboroPool.addWeightedAbility(AbilityDatabase.VENOM_MAW, 30);
        marlboroPool.addWeightedAbility(AbilityDatabase.POISON_MIST, 20);
        marlboroPool.addWeightedAbility(AbilityDatabase.IMPALING_ICE, 10);

        pools.put(EnemyKey.GOBLIN.key(), goblinPool);
        pools.put(EnemyKey.ORC.key(), orcPool);
        pools.put(EnemyKey.DRAGON.key(), dragonPool);
        pools.put(EnemyKey.MARLBORO.key(), marlboroPool);
    }

    public static EnemyAbilityPool getPool(String enemyType) {
        return pools.get(enemyType.toLowerCase());
    }
}
