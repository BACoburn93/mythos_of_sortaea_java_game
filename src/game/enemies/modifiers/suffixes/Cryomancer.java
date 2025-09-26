package enemies.modifiers.suffixes;

import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.Suffix;

public class Cryomancer implements Suffix {
    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(1);

        enemy.getAttributes().increaseKnowledge(15);
        
        enemy.addAbility(AbilityDatabase.IMPALING_ICE);

        enemy.setName(enemy.getName() + " Cryomancer");
    }

    @Override
    public String getName() {
        return " Cryomancer";
    }
}