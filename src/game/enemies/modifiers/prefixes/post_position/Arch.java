package enemies.modifiers.prefixes.post_position;

import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.MathUtils;

public class Arch implements EnemyPrefix {
    @Override
    public EnemyPrefix.Position getPosition() {
        return EnemyPrefix.Position.POST;
    }

    @Override
    public void apply(Enemy enemy) {
        double factor = MathUtils.randomInRange(2.15, 0.35);

        enemy.updateLevelAndExperience(enemy.getLevel());

        enemy.getAttributes().multiplyAll(factor);
        enemy.getResistances().multiplyAll(factor);

        enemy.getHealthValues().multiplyMaxAndSetToMax(factor);
        enemy.getManaValues().multiplyMaxAndSetToMax(factor);

        enemy.setActionsPerTurn(enemy.getActionsPerTurn() + 1);
    }

    @Override
    public String getName() {
        return "Arch";
    }
}
