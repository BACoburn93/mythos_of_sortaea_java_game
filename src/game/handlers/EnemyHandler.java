package handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import abilities.Ability;
import characters.Character;
import characters.Party;
import enemies.Enemy;
import utils.ActionRequest;

public class EnemyHandler {

    public List<ActionRequest> planActions(Enemy enemy, Party targets) {
        List<ActionRequest> plan = new ArrayList<>();
        ArrayList<Character> validTargets = targets.validTargetsInParty();
        if (validTargets.isEmpty() || enemy.getAbilities() == null || enemy.getAbilities().isEmpty()) return plan;

        Random rnd = new Random();
        int remainingActions = enemy.getActionsPerTurn(); 

        while (remainingActions > 0) {
            List<Ability> affordable = new ArrayList<>();
            for (Ability a : enemy.getAbilities()) {
                if (a != null && enemy.getManaValues().getValue() >= a.getManaCost()) {
                    affordable.add(a);
                }
            }
            if (affordable.isEmpty()) break;

            Ability chosen = affordable.get(rnd.nextInt(affordable.size()));
            Character target = validTargets.get(rnd.nextInt(validTargets.size()));
            
            plan.add(new ActionRequest(enemy, chosen, target));

            remainingActions -= 1;
        }
        return plan;
    }
}
