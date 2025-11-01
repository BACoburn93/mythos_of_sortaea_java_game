package enemies.modifiers.prefixes.post_position;

import java.util.ArrayList;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import enemies.Enemy;
import enemies.modifiers.EnemyPrefix;
import utils.ChooseAbilities;

public class White implements EnemyPrefix, ChooseAbilities {
    private static final ArrayList<Ability> possibleAbilities = new ArrayList<>();

    @Override
    public EnemyPrefix.Position getPosition() {
        return EnemyPrefix.Position.POST;
    }

    static {
        // Register White abilities
        possibleAbilities.add(AbilityDatabase.DRAGON_BREATH); 
    }

    @Override
    public void apply(Enemy enemy) {
        enemy.updateLevelAndExperience(enemy.getLevel() * 0.1);

        enemy.getAttributes().multiplyStrength(1.08);
        enemy.getAttributes().multiplyAgility(1.12);
        enemy.getAttributes().multiplyDefense(1.08);
        enemy.getAttributes().multiplyKnowledge(1.1);
        enemy.getAttributes().multiplyResilience(1.12);
        enemy.getAttributes().multiplySpirit(1.08);
        enemy.getAttributes().multiplyLuck(1.12);

        enemy.getManaValues().multiplyMaxAndSetToMax(1.12);

        chooseAbilities(enemy, possibleAbilities);
    }

    @Override
    public String getName() {
        return "White ";
    }
}

