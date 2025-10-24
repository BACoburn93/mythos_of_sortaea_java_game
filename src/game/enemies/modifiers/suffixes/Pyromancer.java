package enemies.modifiers.suffixes;

import java.util.ArrayList;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.EnemySuffix;
import utils.ChooseAbilities;

public class Pyromancer implements EnemySuffix, ChooseAbilities {
    private static final ArrayList<Ability> possibleAbilities = new ArrayList<>();

    static {
        // Register Pyromancer abilities
        possibleAbilities.add(AbilityDatabase.MOTE_OF_FIRE); // Tier 0
        possibleAbilities.add(AbilityDatabase.DANCING_CINDERS); // Tier 1
        possibleAbilities.add(AbilityDatabase.FIREBALL); // Tier 2
        possibleAbilities.add(AbilityDatabase.SPHERE_OF_FLAMES); // Tier 3
        possibleAbilities.add(AbilityDatabase.PYROCLASM); // Tier 4
    }

    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(enemy.getLevel() * 1.5);

        enemy.getAttributes().multiplyKnowledge(1.3);
        enemy.getManaValues().multiplyMaxAndSetToMax(1.5);

        chooseAbilities(enemy, possibleAbilities);
    }

    @Override
    public String getName() {
        return " Pyromancer";
    }
}
