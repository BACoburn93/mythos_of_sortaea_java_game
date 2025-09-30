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

        AbilityPool marlboroPool = new AbilityPool();
        marlboroPool.addWeightedAbility(AbilityDatabase.ROTTING_TENTACLE, 40);
        marlboroPool.addWeightedAbility(AbilityDatabase.VENOM_MAW, 30);
        marlboroPool.addWeightedAbility(AbilityDatabase.POISON_MIST, 20);
        marlboroPool.addWeightedAbility(AbilityDatabase.IMPALING_ICE, 10);

        pools.put("goblin", goblinPool);
        pools.put("orc", orcPool);
        pools.put("dragon", dragonPool);
        pools.put("marlboro", marlboroPool);
    }

    public static AbilityPool getPool(String enemyType) {
        return pools.get(enemyType.toLowerCase());
    }
}
