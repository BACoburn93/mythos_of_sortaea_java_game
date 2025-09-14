package handlers;

import actors.Actor;
import characters.Character;
import enemies.Enemy;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.StringUtils;
import abilities.Ability;
import abilities.actions.ActionTypes;

import java.lang.reflect.Method;
import java.util.*;

public class ActionHandler {
    private final GameScanner scanner;
    private final EquipmentHandler equipmentHandler;
    private final TargetSelector targetSelector;
    private ArrayList<Actor> actors;
    private ArrayList<Enemy> enemies;

    public ActionHandler(GameScanner scanner, ArrayList<Actor> actors, ArrayList<Enemy> enemies, EquipmentHandler equipmentHandler) {
        this.scanner = scanner;
        this.actors = actors;
        this.enemies = enemies;
        this.equipmentHandler = equipmentHandler;
        this.targetSelector = new TargetSelector(actors);
    }

    public void handleTurn(Character character) {
        character.setActionPoints(character.getMaxActionPoints());
        
        while (character.getActionPoints() > 0) {
            // boolean validTarget = false;

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
                StringUtils.stringDivider("Ability, Ability Description/Abildesc, Item, or End", "=", 50);
            } else if (character.isValidAction(action)) {
                character.handleItem(action);

                if (character.getActionPoints() <= 0) {
                    System.out.println("No Ability Points remaining, ending turn.");
                    break;
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.ABILITY.toString())) {
                    sortAbilities(character.getAbilities(), AbilitySortKey.NAME, true);
                    CombatUIStrings.printAbilitiesWithDivider(character.getAbilities());
                }

                if (Objects.equals(action.toUpperCase(), "SORT")) {
                    System.out.println("What would you like to sort? (Abilities):");
                    String sortTarget = scanner.nextLine().trim().toLowerCase();

                    if (sortTarget.equals("abilities") || sortTarget.equals("ability")) {
                        System.out.println("Sort Abilities by: Name, Mana Cost, Action Cost, Damages");
                        String sortBy = scanner.nextLine().trim().toLowerCase();

                        AbilitySortKey sortKey;
                        switch (sortBy) {
                            case "name" -> sortKey = AbilitySortKey.NAME;
                            case "mana cost" -> sortKey = AbilitySortKey.MANA_COST;
                            case "action cost" -> sortKey = AbilitySortKey.ACTION_COST;
                            case "damages" -> sortKey = AbilitySortKey.DAMAGES;
                            default -> {
                                System.out.println("Invalid sort key. Defaulting to Name.");
                                sortKey = AbilitySortKey.NAME;
                            }
                        }

                        System.out.println("Ascending? (yes/no):");
                        String ascendingInput = scanner.nextLine().trim().toLowerCase();
                        boolean ascending = ascendingInput.equals("yes") || ascendingInput.equals("y");

                        sortAbilities(character.getAbilities(), sortKey, ascending);
                        CombatUIStrings.printAbilitiesWithDivider(character.getAbilities());
                    } else {
                        System.out.println("Unknown sort target: " + sortTarget);
                    }
                }


                if (Objects.equals(action.toUpperCase(), ActionTypes.ABILDESC.toString()) ||
                        Objects.equals(action.toLowerCase(), "ability description")) {
                    CombatUIStrings.printAbilitiesWithDescription(character.getAbilities());
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.EQUIP.toString())) {
                    equipmentHandler.handleEquip(scanner, character);
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.UNEQUIP.toString())) {
                    equipmentHandler.handleUnequip(scanner, character);
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
                        // validTarget = true;
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

    public enum AbilitySortKey {
        NAME,
        MANA_COST,
        ACTION_COST,
        WEAPON_TYPES,
        ARMOR_TYPES,
        SHIELD_TYPES,
        DAMAGES
    }

    private void sortAbilities(List<Ability> abilities, AbilitySortKey sortKey, boolean ascending) {
        Comparator<Ability> comparator;

        switch (sortKey) {
            case NAME:
                comparator = Comparator.comparing(Ability::getName);
                break;
            case MANA_COST:
                comparator = Comparator.comparingInt(Ability::getManaCost);
                break;
            case ACTION_COST:
                comparator = Comparator.comparingInt(Ability::getActionCost);
                break;
            // case WEAPON_TYPES:
            //     comparator = Comparator.comparing(a -> a.getWeaponTypes().toString());
            //     break;
            // case ARMOR_TYPES:
            //     comparator = Comparator.comparing(a -> a.getArmorTypes().toString());
            //     break;
            // case SHIELD_TYPES:
            //     comparator = Comparator.comparing(a -> a.getShieldTypes().toString());
            //     break;
            case DAMAGES:
                comparator = Comparator.comparing(a -> a.getDamages().toString());
                break;
            default:
                throw new IllegalArgumentException("Unsupported sort key: " + sortKey);
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        abilities.sort(comparator);
    }


    private ArrayList<Actor> handleKillEnemy(Enemy enemy) {
        System.out.println(enemy.getName() + " has been slain.");
        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }
}
