package enemies.modifiers.suffixes;

import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.Suffix;

public class Cryomancer implements Suffix {
    @Override
    public void apply(Enemy enemy) {
        enemy.addAbility(AbilityDatabase.IMPALING_ICE);
        enemy.setName(enemy.getName() + " Cryomancer");
    }

    @Override
    public String getName() {
        return "Cryomancer";
    }
}