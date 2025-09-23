package handlers;

import characters.Character;
import characters.Party;
import enemies.Enemy;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.InputHandler;
import utils.StringUtils;
import abilities.Ability;
import abilities.actions.ActionTypes;
import actors.types.CombatActor;

import java.util.*;

public class ActionHandler {
    private final GameScanner scanner;
    private final EquipmentHandler equipmentHandler;
    private final SortHandler sortHandler;
    private final InputHandler inputHandler;
    private final TargetSelector targetSelector;
    private ArrayList<CombatActor> actors;
    private Party party;
    private ArrayList<Enemy> enemies;

    public ActionHandler(GameScanner scanner, ArrayList<CombatActor> actors, Party party, ArrayList<Enemy> enemies, EquipmentHandler equipmentHandler) {
        this.scanner = scanner;
        this.inputHandler = new InputHandler();
        this.sortHandler = new SortHandler(scanner, inputHandler);
        this.actors = actors;
        this.party = party;
        this.enemies = enemies;
        this.equipmentHandler = equipmentHandler;
        this.targetSelector = new TargetSelector(actors);
    }

    public void handleTurn(Character character) {
        character.setActionPoints(character.getMaxActionPoints());

        while (character.getActionPoints() > 0) {
            CombatUIStrings.formatPartyStat(party.getCharacters());

            // Use enum selection instead of raw string input
            ActionTypes action = inputHandler.promptEnumSelection(ActionTypes.class, "Choose an action for " + character.getName());

            if (action == null) {
                System.out.println("Invalid action. Try again.");
                continue;
            }

            switch (action) {
                case END -> {
                    System.out.println("Ending turn.");
                    return;
                }
                case HELP -> {
                    StringUtils.stringDivider("There's no helping you now.", "", 50);
                }
                case ITEM -> {
                    character.handleItem("ITEM");
                    CombatUIStrings.printAbilityPointUsage(character, null);
                }
                case ABILITY -> {
                    // Paginated ability display with selection
                    Ability chosenAbility = null;
                    while (chosenAbility == null) {
                        CombatUIStrings.displayPaginatedList(
                            character.getAbilities(),
                            3,
                            scanner.getScanner(),
                            Ability::toString
                        );
                        System.out.print("Type the ability name to use or [Q]uit: ");
                        String abilityInput = scanner.nextLine().trim();
                        if (abilityInput.equalsIgnoreCase("q")) {
                            break;
                        }
                        if (character.isValidAbility(abilityInput)) {
                            chosenAbility = character.chooseAbility(abilityInput);
                        } else {
                            System.out.println("Invalid ability name.");
                        }
                    }
                    if (chosenAbility != null) {
                        handleUseAbility(character, chosenAbility);
                    }
                }
                case EQUIP -> {
                    equipmentHandler.handleEquip(scanner, character);
                }
                case UNEQUIP -> {
                    equipmentHandler.handleUnequip(scanner, character);
                }
                case OBSERVE -> {
                    if (character.getStatusConditions().getBlind().getDuration() <= 0) {
                        System.out.println(character);
                    } else {
                        System.out.println("You're blinded and can't observe.");
                    }
                }
                case SORT -> {
                    handleSortAction(party, character);
                }
                case QUIT -> {
                    scanner.exitGame();
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + action);
            }
        }
    }

    private void handleUseAbility(Character character, Ability chosenAbility) {
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

    private void handleSortAction(Party party, Character character) {
        sortHandler.handleSortAction(party, character);
    }


    private ArrayList<CombatActor> handleKillEnemy(CombatActor enemy) {
        System.out.println(enemy.getName() + " has been slain.");
        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }
}