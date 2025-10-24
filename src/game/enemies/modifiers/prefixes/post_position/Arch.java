package enemies.modifiers.prefixes;

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

        enemy.getAttributes().multiplyStrength(factor);
        enemy.getAttributes().multiplyAgility(factor);
        enemy.getAttributes().multiplyDefense(factor);
        enemy.getAttributes().multiplyKnowledge(factor);
        enemy.getAttributes().multiplyResilience(factor);
        enemy.getAttributes().multiplySpirit(factor);
        enemy.getAttributes().multiplyLuck(factor);

        enemy.getResistances().multiplyBludgeoning(factor);
        enemy.getResistances().multiplyPiercing(factor);
        enemy.getResistances().multiplySlashing(factor);
        enemy.getResistances().multiplyEarth(factor);
        enemy.getResistances().multiplyFire(factor);
        enemy.getResistances().multiplyIce(factor);
        enemy.getResistances().multiplyLightning(factor);
        enemy.getResistances().multiplyVenom(factor);
        enemy.getResistances().multiplyWater(factor);
        enemy.getResistances().multiplyWind(factor);
        enemy.getResistances().multiplyDarkness(factor);
        enemy.getResistances().multiplyLight(factor);

        enemy.getHealthValues().multiplyMaxAndSetToMax(factor);
        enemy.getManaValues().multiplyMaxAndSetToMax(factor);

        enemy.setActionsPerTurn(enemy.getActionsPerTurn() + 1);
    }

    @Override
    public String getName() {
        return "Arch";
    }
}
