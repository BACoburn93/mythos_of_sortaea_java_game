package handlers;

import actors.Actor;
import characters.Character;
import enemies.Enemy;
import utils.GameScanner;
import utils.StringUtils;
import abilities.Ability;
import abilities.actions.ActionTypes;

import java.util.*;

public class ActionHandler {
    private final GameScanner scanner;
    private final TargetSelector targetSelector;
    private ArrayList<Actor> actors;
    private ArrayList<Enemy> enemies;

    public ActionHandler(GameScanner scanner, ArrayList<Actor> actors, ArrayList<Enemy> enemies) {
        this.scanner = scanner;
        this.actors = actors;
        this.enemies = enemies;
        this.targetSelector = new TargetSelector(actors);
    }

    public void handleTurn(Character character) {
        while (character.getActionPoints() > 0) {
            boolean validTarget = false;

            StringUtils.stringDivider(character.getActionPoints() + "/" +
                    character.getMaxActionPoints() + " Action Points.",
                    "-", 50);

            String action = scanner.nextLine();

            if (
                    action.equalsIgnoreCase("end") ||
                            action.equalsIgnoreCase("end turn") ||
                            action.equalsIgnoreCase("pass")
            ) {
                System.out.println("Ending turn.");
                break;
            } else if (Objects.equals(action.toUpperCase(), ActionTypes.HELP.toString())) {
                System.out.println("=".repeat(50));
                StringUtils.stringDivider("Ability, Ability Description/Abildesc, Item, or End", "=", 100);
            } else if (character.isValidAction(action)) {
                character.handleItem(action);

                if (character.getActionPoints() <= 0) {
                    System.out.println("No Ability Points remaining, ending turn.");
                    break;
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.ABILITY.toString())) {
                    printAbilitiesWithDivider(character.getAbilities());
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.ABILDESC.toString()) ||
                        Objects.equals(action.toLowerCase(), "ability description")) {
                    printAbilitiesWithDescription(character.getAbilities());
                }

                // For now, these can be no-ops or printed messages.
                if (Objects.equals(action.toUpperCase(), ActionTypes.EQUIP.toString())) {
                    System.out.println("Equip not yet implemented in ActionHandler.");
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.UNEQUIP.toString())) {
                    System.out.println("Unequip not yet implemented in ActionHandler.");
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.OBSERVE.toString()) &&
                        character.getStatusConditions().getBlind().getDuration() <= 0) {
                    System.out.println(character);
                }

            } else if (character.isValidAbility(action)) {
                Ability chosenAbility = character.chooseAbility(action);

                if (chosenAbility.getActionCost() > character.getActionPoints() || !character.canUseAbility(chosenAbility)) {
                    System.out.println("=".repeat(50));

                    if (character.getActionPoints() <= 0) {
                        System.out.println("No Ability Points remaining, ending turn.");
                        validTarget = true;
                    } else if (!character.canUseAbility(chosenAbility)) {
                        System.out.println("Insufficient Mana, please use another ability.");
                    } else {
                        System.out.println("Insufficient Ability Points, please use another ability.");
                    }
                } else {
                    Actor chosenTarget = targetSelector.chooseEnemyTarget(scanner);
                    if (chosenTarget != null) {
                        Random random = new Random();
                        boolean missedTarget = random.nextInt(100) < character.getStatusConditions().getBlind().getValue();

                        if (!missedTarget) {
                            character.attack(character, chosenTarget, chosenAbility);
                        } else {
                            System.out.println(character.getName() + " missed " + chosenTarget.getName() +
                                    " with " + chosenAbility.getName());
                        }

                        character.spendMana(chosenAbility);
                        character.setActionPoints(character.getActionPoints() - chosenAbility.getActionCost());

                        System.out.println(chosenTarget.getName() + " has " +
                                chosenTarget.getHealthValues().getValue() + " hit points remaining.");

                        if (chosenTarget.getHealthValues().getValue() < 0) {
                            actors = handleKillEnemy((Enemy) chosenTarget);
                        }
                    }

                }
            } else {
                System.out.println("=".repeat(50));
                System.out.println("Invalid Action, please try again. If you need help, type HELP.");
            }
        }
    }

    private void printAbilitiesWithDivider(List<Ability> abilities) {
        for (Ability ability : abilities) {
            StringUtils.stringDividerTop(String.valueOf(ability), "-", 50);
        }
    }

    private void printAbilitiesWithDescription(List<Ability> abilities) {
        for (Ability ability : abilities) {
            StringUtils.stringDividerTop(ability.getName() + ": " + ability.getDescription(), "-", 50);
        }
    }

    private ArrayList<Actor> handleKillEnemy(Enemy enemy) {
        System.out.println(enemy.getName() + " has been slain.");
        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }
}
