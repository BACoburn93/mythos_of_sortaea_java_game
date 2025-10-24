package enemies.modifiers.prefixes.post_position;

import java.util.ArrayList;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.ChooseAbilities;

public class Red implements EnemyPrefix, ChooseAbilities {
    private static final ArrayList<Ability> possibleAbilities = new ArrayList<>();

    @Override
    public EnemyPrefix.Position getPosition() {
        return EnemyPrefix.Position.POST;
    }

    static {
        // Register Red abilities
        possibleAbilities.add(AbilityDatabase.FIRE_BREATH); // Tier 3
    }

    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(enemy.getLevel() * 0.1);

        enemy.getAttributes().multiplyStrength(1.1);
        enemy.getAttributes().multiplyAgility(1.1);
        enemy.getAttributes().multiplyDefense(1.1);
        enemy.getAttributes().multiplyKnowledge(1.1);
        enemy.getAttributes().multiplyResilience(1.1);
        enemy.getAttributes().multiplySpirit(1.1);
        enemy.getAttributes().multiplyLuck(1.1);

        enemy.getManaValues().multiplyMaxAndSetToMax(1.1);

        chooseAbilities(enemy, possibleAbilities);
    }

    @Override
    public String getName() {
        return "Red ";
    }
}

