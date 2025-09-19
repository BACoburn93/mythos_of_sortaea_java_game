package handlers;

import characters.Character;
import characters.Party;
import enemies.Enemy;
import ui.CombatUIStrings;
import ui.GeneralUIStrings;
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
            CombatUIStrings.printActionPoints(character);

            // Use enum selection instead of raw string input
            ActionTypes action = inputHandler.promptEnumSelection(ActionTypes.class, "Choose an action:");

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
                    StringUtils.stringDivider("There's no helping you now.", "=", 50);
                }
                case ITEM -> {
                    character.handleItem("ITEM");
                    CombatUIStrings.printAbilityPointUsage(character, null);
                }
                case ABILITY -> {
                    CombatUIStrings.printAbilitiesWithDivider(character.getAbilities());

                    System.out.print("Type the ability name to use: ");
                    String abilityInput = scanner.nextLine().trim();

                    if (character.isValidAbility(abilityInput)) {
                        Ability chosenAbility = character.chooseAbility(abilityInput);
                        handleUseAbility(character, chosenAbility);
                    } else {
                        System.out.println("Invalid ability name.");
                    }
                }
                case ABILDESC -> {
                    CombatUIStrings.printAbilitiesWithDescription(character.getAbilities());
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

    // public void handleTurn(Character character) {
    //     character.setActionPoints(character.getMaxActionPoints());
        
    //     while (character.getActionPoints() > 0) {

    //         CombatUIStrings.printActionPoints(character);

    //         String action = scanner.nextLine();

    //         if (
    //                 action.equalsIgnoreCase("end") ||
    //                 action.equalsIgnoreCase("end turn") ||
    //                 action.equalsIgnoreCase("pass")
    //         ) {
    //             System.out.println("Ending turn.");
    //             break;
    //         } else if (Objects.equals(action.toUpperCase(), ActionTypes.HELP.toString())) {
    //             System.out.println("=".repeat(50));
    //             StringUtils.stringDivider("Ability, Ability Description/Abildesc, Item, or End", "=", 50);
    //         } else if (character.isValidAction(action)) {
    //             character.handleItem(action);

    //             CombatUIStrings.printAbilityPointUsage(character, null);

    //             if (Objects.equals(action.toUpperCase(), ActionTypes.ABILITY.toString())) {
    //                 CombatUIStrings.printAbilitiesWithDivider(character.getAbilities());
    //             }

    //             if (Objects.equals(action.toUpperCase(), "SORT")) {
    //                 handleSortAction(party, character);
    //             }


    //             if (Objects.equals(action.toUpperCase(), ActionTypes.ABILDESC.toString()) ||
    //                     Objects.equals(action.toLowerCase(), "ability description")) {
    //                 CombatUIStrings.printAbilitiesWithDescription(character.getAbilities());
    //             }

    //             if (Objects.equals(action.toUpperCase(), ActionTypes.EQUIP.toString())) {
    //                 equipmentHandler.handleEquip(scanner, character);
    //             }

    //             if (Objects.equals(action.toUpperCase(), ActionTypes.UNEQUIP.toString())) {
    //                 equipmentHandler.handleUnequip(scanner, character);
    //             }

    //             if (Objects.equals(action.toUpperCase(), ActionTypes.OBSERVE.toString()) &&
    //                     character.getStatusConditions().getBlind().getDuration() <= 0) {
    //                 System.out.println(character);
    //             }

    //         } else if (character.isValidAbility(action)) {
    //             Ability chosenAbility = character.chooseAbility(action);

    //             handleUseAbility(character, chosenAbility);

    //         } else {
    //             GeneralUIStrings.handleInvalidAction();
    //         }
    //     }
    // }