package enemies.modifiers.prefixes;

import enemies.Enemy;
import enemies.modifiers.Prefix;

public class Wrathful implements Prefix {
    @Override
    public void apply(Enemy enemy) {
        enemy.getAttributes().increaseStrength(15);
        enemy.getAttributes().increaseAgility(10.0);
        enemy.getAttributes().decreaseDefense(10.0);
    }

    @Override
    public String getName() {
        return "Wrathful";
    }
}
