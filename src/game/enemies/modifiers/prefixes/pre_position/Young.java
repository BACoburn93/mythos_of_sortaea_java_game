package enemies.modifiers.prefixes.pre_position;

import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.ChooseAbilities;

public class Young implements EnemyPrefix, ChooseAbilities {

    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(-enemy.getLevel() * 0.4);

        enemy.getAttributes().multiplyStrength(0.6);
        enemy.getAttributes().multiplyAgility(0.6);
        enemy.getAttributes().multiplyDefense(0.6);
        enemy.getAttributes().multiplyKnowledge(0.6);
        enemy.getAttributes().multiplyResilience(0.6);
        enemy.getAttributes().multiplySpirit(0.6);
        enemy.getAttributes().multiplyLuck(0.6);

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


