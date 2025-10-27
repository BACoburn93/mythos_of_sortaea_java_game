package enemies.modifiers.prefixes.pre_position;

import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.MathUtils;

public class Wrathful implements EnemyPrefix {
    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(enemy.getLevel() * 0.1);
        
        enemy.getAttributes().multiplyStrength(MathUtils.randomInRange(1.3)); 
        enemy.getAttributes().multiplyAgility(MathUtils.randomInRange(1.3)); 
        enemy.getAttributes().multiplyDefense(MathUtils.randomInRange(0.85));

        enemy.getHealthValues().multiplyMaxAndSetToMax(MathUtils.randomInRange(0.85));
    }

    @Override
    public String getName() {
        return "Wrathful ";
    }
}
