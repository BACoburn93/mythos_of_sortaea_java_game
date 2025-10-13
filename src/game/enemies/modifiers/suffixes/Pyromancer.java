package enemies.modifiers.suffixes;

import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.EnemySuffix;

public class Pyromancer implements EnemySuffix {
    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(1);

        enemy.getAttributes().increaseKnowledge(10);
        
        enemy.addAbility(AbilityDatabase.FIREBALL);
    }

    @Override
    public String getName() {
        return " Pyromancer";
    }
}
