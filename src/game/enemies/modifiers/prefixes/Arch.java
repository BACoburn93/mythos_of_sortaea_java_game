package enemies.modifiers.prefixes;

import enemies.Enemy;
import enemies.modifiers.Prefix;
import utils.MathUtils;

public class Arch implements Prefix {
    @Override
    public void apply(Enemy enemy) {
        double factor = MathUtils.randomInRange(2.15, 0.35);

        // Make sure to multiply both current value and max value for health/mana
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

        enemy.setName("Arch" + enemy.getName());
    }

    @Override
    public String getName() {
        return "Arch";
    }
}
