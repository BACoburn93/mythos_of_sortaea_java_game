package enemies.modifiers.prefixes;

import enemies.Enemy;
import enemies.modifiers.Prefix;
import utils.MathUtils;

public class Berserker implements Prefix {
    @Override
    public void apply(Enemy enemy) {
        enemy.getAttributes().multiplyStrength(MathUtils.randomInRange(1.3)); 
        enemy.getAttributes().multiplyAgility(MathUtils.randomInRange(1.3)); 
        enemy.getAttributes().multiplyDefense(MathUtils.randomInRange(0.85));

        enemy.getHealthValues().multiplyMaxAndSetToMax(MathUtils.randomInRange(0.85));

        enemy.setName("Berserker " + enemy.getName());
    }

    @Override
    public String getName() {
        return "Berserker ";
    }
}
