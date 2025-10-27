package enemies.modifiers.prefixes.pre_position;

import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.ChooseAbilities;
import utils.MathUtils;

public class Young implements EnemyPrefix, ChooseAbilities {

    @Override
    public void apply(Enemy enemy) {
        double factor = MathUtils.randomInRange(0.6, 0.1);

        enemy.updateLevelAndExperience(-enemy.getLevel() * 0.4);

        enemy.getAttributes().multiplyAll(factor);
        enemy.getResistances().multiplyAll(factor);

        enemy.getHealthValues().multiplyMaxAndSetToMax(0.6);
        enemy.getManaValues().multiplyMaxAndSetToMax(0.6);
        
        enemy.setActionsPerTurn(enemy.getActionsPerTurn() - 1);
        enemy.setSpawnWeight(enemy.getSpawnWeight() - 1);
    }

    @Override
    public String getName() {
        return "Young ";
    }
}


