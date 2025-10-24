package enemies.modifiers.suffixes;

import java.util.ArrayList;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.EnemySuffix;
import utils.ChooseAbilities;

public class Cryomancer implements EnemySuffix, ChooseAbilities {
    private static final ArrayList<Ability> possibleAbilities = new ArrayList<>();

    static {
        // Register Pyromancer abilities
        possibleAbilities.add(AbilityDatabase.FROST_BOLT); // Tier 0
        possibleAbilities.add(AbilityDatabase.WINTERS_GRASP); // Tier 1
        possibleAbilities.add(AbilityDatabase.IMPALING_ICE); // Tier 2
        possibleAbilities.add(AbilityDatabase.BLACK_FROST_ABYSS); // Tier 6
    }

    @Override
    public void apply(Enemy enemy) {

        enemy.updateLevelAndExperience(enemy.getLevel() * 0.5);

        enemy.getAttributes().increaseKnowledge(15);

        enemy.getManaValues().multiplyMaxAndSetToMax(1.5);

        chooseAbilities(enemy, possibleAbilities);
    }

    @Override
    public String getName() {
        return " Cryomancer";
    }
}