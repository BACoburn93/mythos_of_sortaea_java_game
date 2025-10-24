package enemies.modifiers.suffixes;

import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.EnemySuffix;

public class Cryomancer implements EnemySuffix {
    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(1);

        enemy.getAttributes().increaseKnowledge(15);
        
        enemy.addAbility(AbilityDatabase.IMPALING_ICE);

        
    }

    @Override
    public String getName() {
        return " Cryomancer";
    }
}