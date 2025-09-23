package handlers;

import java.util.Random;
import java.util.Scanner;

import ui.CombatUIStrings;
import utils.GameScanner;
import characters.Character;
import enemies.Enemy;
import abilities.Ability;
import actors.types.CombatActor;

import java.util.ArrayList;

public class AbilityHandler {
    private GameScanner scanner;
    private TargetSelector targetSelector;
    private ArrayList<CombatActor> actors;
    private ArrayList<Enemy> enemies;

    public AbilityHandler(GameScanner scanner, TargetSelector targetSelector, ArrayList<CombatActor> actors, ArrayList<Enemy> enemies) {
        this.scanner = scanner;
        this.targetSelector = targetSelector;
        this.actors = actors;
        this.enemies = enemies;
    }

    public void handleAbilityAction(Character character) {
        Ability chosenAbility = null;

        while (chosenAbility == null) {
            System.out.print("Type the ability name to use, [L]ist to see abilities, or [Q]uit: ");
            String abilityInput = scanner.nextLine().trim();
            if (abilityInput.equalsIgnoreCase("q") || abilityInput.equalsIgnoreCase("quit")) {
                break;
            }
            if (abilityInput.equalsIgnoreCase("l") || abilityInput.equalsIgnoreCase("list")) {
                CombatUIStrings.displayPaginatedList(
                    character.getAbilities(),
                    3,
                    scanner.getScanner(),
                    ability -> ability.toString()
                );
            }
            if (character.isValidAbility(abilityInput)) {
                chosenAbility = character.chooseAbility(abilityInput);
            }
        }
        if (chosenAbility != null) {
            handleUseAbility(character, chosenAbility);
        }
    }

    public void handleUseAbility(Character character, Ability chosenAbility) {
        if (chosenAbility.getActionCost() > character.getActionPoints() || !character.canUseAbility(chosenAbility)) {
            CombatUIStrings.printAbilityPointUsage(character, chosenAbility);
        } else {
            CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

            if (chosenTarget != null) {
                Random random = new Random();
                boolean missedTarget = random.nextInt(100) < character.getStatusConditions().getBlind().getValue();

                character.spendMana(chosenAbility);
                character.setActionPoints(character.getActionPoints() - chosenAbility.getActionCost());

                if (!missedTarget) {
                    character.attack(character, chosenTarget, chosenAbility);
                } else {
                    CombatUIStrings.printMissedAttack(character, chosenTarget, chosenAbility);
                }

                CombatUIStrings.printHitPointsRemaining(chosenTarget);

                if (chosenTarget.getHealthValues().getValue() < 0) {
                    actors = handleKillEnemy(chosenTarget);
                }
            } 
        }
    }

    private ArrayList<CombatActor> handleKillEnemy(CombatActor enemy) {
        System.out.println(enemy.getName() + " has been slain.");
        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }
}
