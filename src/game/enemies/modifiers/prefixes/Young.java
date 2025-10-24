package enemies.modifiers.prefixes;

import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.ChooseAbilities;

public class Young implements EnemyPrefix, ChooseAbilities {

    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(-enemy.getLevel() * 0.2);

        enemy.getAttributes().multiplyStrength(0.7);
        enemy.getAttributes().multiplyAgility(0.7);
        enemy.getAttributes().multiplyDefense(0.7);
        enemy.getAttributes().multiplyKnowledge(0.7);
        enemy.getAttributes().multiplyResilience(0.7);
        enemy.getAttributes().multiplySpirit(0.7);
        enemy.getAttributes().multiplyLuck(0.7);

        enemy.getManaValues().multiplyMaxAndSetToMax(0.8);
    }

    @Override
    public String getName() {
        return "Young ";
    }
}


