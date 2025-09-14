package containers;

import abilities.Ability;
import abilities.actions.ActionTypes;
import abilities.interfaces.ActorInterface;
import abilities.reactions.Reaction;
import actors.Actor;
import actors.ActorTypes;
import characters.Character;
import characters.Party;
import enemies.Enemy;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.StringUtils;
import utils.ListUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CombatContainer {
    public ArrayList<Actor> actors = new ArrayList<>();
    public ArrayList<Enemy> enemies;
    public Party party;

    public CombatContainer(Party party, ArrayList<Enemy> enemies) {
        this.party = party;
        this.enemies = enemies;
    }

    public void startCombat(Party party, ArrayList<Enemy> enemies) {
        this.actors = chooseOrder(party, enemies);
        GameScanner combatLoop = new GameScanner();

        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("Enemy " + (i + 1) + " " + enemies.get(i).getName());
        }

while (isCharacterAlive() && !enemies.isEmpty()) {
    for (ActorInterface actor : this.actors) {
        if (actor.getHealth() > 0) {
            handleStartTurn(actor);

            if (actor instanceof Character character) {
                character.setActionPoints(character.getMaxActionPoints());
                StringUtils.twoStringDivider(
                    "The valiant " + character.getJob() + ", " + actor.getName() + " is ready to act!",
                    "Type the action you would like to use or type HELP to get a list of actions that you have access to.",
                    "=", 50
                );

                handleCharacterActions(combatLoop, character);

            } else if (actor instanceof Enemy enemy) {
                StringUtils.stringDivider(
                    "The wretched " + enemy.getName() + " looks menacing...", "=", 50
                );

                handleCharacterReactions(combatLoop);
                enemy.chooseEnemyAbility(party);
            }

            handleEndTurn(actor);
        }
    }
}

        if (!isCharacterAlive()) {
            StringUtils.stringDivider("You have been defeated!", "*", 50);
        }
        if (!isEnemyAlive()) {
            StringUtils.stringDivider("All enemies are defeated! You are victorious!", "*", 50);
        }
        combatLoop.close();
    }

    private static ArrayList<Actor> chooseOrder(Party party, ArrayList<Enemy> enemies) {
        ArrayList<Actor> initiativeOrder = new ArrayList<>();

        for (int i = 0; i < party.partySize; i++) {
            initiativeOrder.add(party.characters.get(i));
        }

        initiativeOrder.addAll(enemies);

        initiativeOrder.sort(Comparator.comparingInt(Actor::getInitiative).reversed());

        utils.StringUtils.stringDivider(
                "Initiative Order: " + initiativeOrder.stream()
                        .map(Actor::getName)
                        .toList()
                , "=", 50);

        return initiativeOrder;
    }

    private Actor chooseTarget(String target) {
        for (Actor actor : this.actors) {
            if (Objects.equals(target.toLowerCase(), actor.getName().toLowerCase())) {
                return actor;
            }
        }
        return null;
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


    private void handleCharacterActions(GameScanner combatLoop, Character character) {
        while (character.getActionPoints() > 0) {
            boolean validTarget = false;

            StringUtils.stringDivider(character.getActionPoints() + "/" +
                    character.getMaxActionPoints() + " Action Points.",
                    "-", 50);

            String action = combatLoop.nextLine();

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

                // If ability, list usable abilities with more information
                if (Objects.equals(action.toUpperCase(), ActionTypes.ABILITY.toString())) {
                    printAbilitiesWithDivider(character.getAbilities());
                }

                // List ability names with description
                if (Objects.equals(action.toUpperCase(), ActionTypes.ABILDESC.toString()) ||
                        Objects.equals(action.toLowerCase(), "ability description")) {
                    printAbilitiesWithDescription(character.getAbilities());
                }

                // // Equip, list out current equipment and prompt to choose one to equip
                if (Objects.equals(action.toUpperCase(), ActionTypes.EQUIP.toString())) {
                    handleEquip(combatLoop, character);
                }

                if (Objects.equals(action.toUpperCase(), ActionTypes.UNEQUIP.toString())) {
                    handleUnequip(combatLoop, character);
                }

                // Will maybe eventually view surrounding area
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
                    while (!validTarget) {
                        System.out.println("Which enemy would you like to target?");
                        System.out.print(CombatUIStrings.formatTargetEnemyList(getEnemyTargets(this.actors)));
                        
                        String input = combatLoop.nextLine();
                        List<Actor> enemies = getEnemyTargets(this.actors);

                        Actor chosenTarget = null;

                        // Check if input is a valid number within enemy list range
                        if (input.matches("\\d+")) {
                            int index = Integer.parseInt(input) - 1;
                            if (index >= 0 && index < enemies.size()) {
                                chosenTarget = enemies.get(index);
                            }
                        } else if (isValidTarget(input)) {
                            chosenTarget = chooseTarget(input);
                        }

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

                            System.out.println(chosenTarget.getName() + " has " + chosenTarget.getHealthValues().getValue()
                                            + " hit points remaining.");

                            if (chosenTarget.getHealthValues().getValue() < 0) {
                                this.actors = handleKillEnemy((Enemy) chosenTarget);
                            }

                            validTarget = true;
                        } else {
                            System.out.println("Invalid target. Please enter a valid enemy name or number.");
                        }
                    }
                }
            } else {
                System.out.println("=".repeat(50));
                System.out.println("Invalid Action, please try again. If you need help, type HELP.");
            }
        }
    }

    private void handleCharacterReactions(GameScanner combatLoop) {
        boolean validCharacter = false;
        boolean validReaction = false;
        Character chosenCharacter = null;

        while (!validReaction && !validCharacter) {
            while (!validCharacter) {
                System.out.println("Party Members:");
                for (int i = 0; i < party.characters.size(); i++) {
                    Character c = party.characters.get(i);
                    System.out.printf("%d. %s (%d/%d HP)\n", i + 1, c.getName(), c.getHealth(), c.getHealthValues().getMaxValue());
                }

                System.out.println("Choose a character to use a reaction (by name or number), or hit ENTER to pass.");
                String characterToChoose = combatLoop.nextLine();
                if (characterToChoose.isEmpty()) {
                    break;
                }
                
                chosenCharacter = ListUtils.getByInput(characterToChoose, party.characters, Character::getName);
                if (chosenCharacter != null && chosenCharacter.getActionPoints() > 0) validCharacter = true;
                else if (chosenCharacter != null) {
                    System.out.println(chosenCharacter.getName() + " is out of Action Points and will pass by default.");
                }
            }
            if (validCharacter) {
                System.out.println("Defend, Parry, Item, Observe, or Pass?");
                String reaction = combatLoop.nextLine();
                Reaction chosenReaction = chosenCharacter.chooseReaction(reaction);

                System.out.println("=".repeat(50));

                if (chosenCharacter.isValidReaction(reaction)) {
                    if (reaction.equalsIgnoreCase("help")) {
                        System.out.println("Optional actions are: Defend, Parry, Item, Observe, or Pass.");
                    } else if (chosenCharacter.isValidReaction(reaction)) {
                        chosenCharacter.setActionPoints(chosenCharacter.getActionPoints() - chosenReaction.getActionCost());
                        chosenCharacter.handleReaction(reaction.toLowerCase());
                        validReaction = true;
                    } else {
                        assert chosenReaction != null;
                        if (chosenCharacter.getActionPoints() >= chosenReaction.getActionCost()) {
                            System.out.println("You chose to use the action: " + chosenReaction.getName());

                            if (chosenCharacter.getActionPoints() == 0) {
                                System.out.println("No remaining Action Points.");
                            }
                            chosenCharacter.setActionPoints(chosenCharacter.getActionPoints() - chosenReaction.getActionCost());
                            validReaction = true;

                        } else {
                            System.out.println("Insufficient Action Points, please choose another action.");
                        }
                    }
                } else {
                    System.out.println("Invalid reaction, please try again. If you need help, type HELP.");
                }
                StringUtils.stringDivider(chosenCharacter.getActionPoints() + "/" +
                        chosenCharacter.getMaxActionPoints() + " Action Points.",
                        "-", 50);
            } else {
                break;
            }
        }
    }

    private ArrayList<Actor> handleKillEnemy(Enemy enemy) {
        System.out.println(enemy.getName() + " has been slain.");

        for (Character c : this.party.characters) {
            c.addExperience((int) Math.floor((double) enemy.getExperience() / party.partySize));
        }

        int enemyIndex = -1;
        for (int i = 0; i < this.actors.size(); i++) {
            if (this.actors.get(i) == enemy) {
                enemyIndex = i;
                break;
            }
        }

        if (enemyIndex != -1) {
            this.actors = removeDefeatedEnemy(this.actors, enemyIndex);
        } else {
            System.out.println("Enemy not found.");
        }

        return this.actors;
    }

    private void handleEndTurn(ActorInterface actor) {
        actor.handleStatusConditions();
    }

    private void handleStartTurn(ActorInterface actor) {
        if (actor.getHealthValues().getRegenValue() > 0) {
            actor.getHealthValues().setValue(
                    actor.getHealthValues().getValue() +
                            actor.getHealthValues().getRegenValue()
            );
        }
        if (actor.getManaValues().getRegenValue() > 0) {
            actor.getManaValues().setValue(
                    actor.getManaValues().getValue() +
                            actor.getManaValues().getRegenValue()
            );
        }
        actor.preventOverloadingResourceValues();
    }

    public boolean isCharacterAlive() {
        for (int i = 0; i < party.partySize; i++) {
            if (party.characters.get(i).getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnemyAlive() {
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() > 0) return true;
        }
        return false;
    }

    private ArrayList<Actor> removeDefeatedEnemy(ArrayList<Actor> actors, int index) {
        ArrayList<Actor> newActors = new ArrayList<>();

        for (int i = 0, j = 0; i < actors.size(); i++) {
            if (i != index) {
                newActors.add(j++, actors.get(i));
            }
        }

        return newActors;
    }

    private boolean isValidTarget(String target) {
        boolean isTargetingCharacter = false;

        for (int i = 0; i < party.characters.size(); i++) {
            if ((party.characters.get(i).getName().equalsIgnoreCase(target))) {
                isTargetingCharacter = true;
                break;
            }
        }
        for (Actor actor : this.actors) {
            if (Objects.equals(actor.getName().toLowerCase(), target.toLowerCase())
                    && !isTargetingCharacter) return true;
        }
        return false;
    }

    // Helper for equipping: get Equipment by name, index, or slot type
    private Equipment getEquipmentByInput(String input, List<Equipment> equipmentList) {
        // Try index (1-based)
        try {
            int idx = Integer.parseInt(input);
            if (idx >= 1 && idx <= equipmentList.size()) {
                return equipmentList.get(idx - 1);
            }
        } catch (NumberFormatException ignored) {}

        // Try by name or slot type
        for (Equipment eq : equipmentList) {
            if (input.equalsIgnoreCase(eq.getName()) ||
                input.equalsIgnoreCase(eq.getEquipmentType().toString())) {
                return eq;
            }
        }
        return null;
    }

    // Helper for unequipping: get EquipmentTypes slot by input (name, index, or slot), HEAD first
    private EquipmentTypes getEquippedSlotByInput(String input, Map<EquipmentTypes, Equipment> slots) {
        List<EquipmentTypes> orderedSlots = new ArrayList<>(slots.keySet());
        orderedSlots.remove(EquipmentTypes.HEAD);
        orderedSlots.add(0, EquipmentTypes.HEAD);

        // Try index (1-based)
        try {
            int idx = Integer.parseInt(input);
            if (idx >= 1 && idx <= orderedSlots.size()) {
                return orderedSlots.get(idx - 1);
            }
        } catch (NumberFormatException ignored) {}

        // Try by slot name or equipped item name
        for (EquipmentTypes slot : orderedSlots) {
            Equipment eq = slots.get(slot);
            if (input.equalsIgnoreCase(slot.toString()) ||
                (eq != null && input.equalsIgnoreCase(eq.getName()))) {
                return slot;
            }
        }
        return null;
    }

    private void handleEquip(GameScanner combatLoop, Character character) {
        List<Equipment> equipmentList = party.getSharedEquipment(); // Use party inventory

        System.out.print(CombatUIStrings.formatEquipItemList(equipmentList));
        System.out.println("Type the equipment name, slot, or its number to equip:");
        String chosenEquipment = combatLoop.nextLine();

        Equipment eq = getEquipmentByInput(chosenEquipment, equipmentList);
        if (eq != null) {
            System.out.println("Equipping " + eq.getName());
            character.equipItem(eq);
            System.out.println("Character's Attributes after equipping: " + character.getAttributes());
            System.out.println("Character's Resistances after equipping: " + character.getResistances());
        } else {
            System.out.println("No such equipment found.");
        }
    }

    private void handleUnequip(GameScanner combatLoop, Character character) {
        Map<EquipmentTypes, Equipment> slots = character.getEquipmentSlots();
        List<EquipmentTypes> orderedSlots = new ArrayList<>(slots.keySet());
        orderedSlots.remove(EquipmentTypes.HEAD);
        orderedSlots.add(0, EquipmentTypes.HEAD);

        for (int i = 0; i < orderedSlots.size(); i++) {
            EquipmentTypes slot = orderedSlots.get(i);
            Equipment eq = slots.get(slot);
            String eqName = (eq != null) ? eq.getName() : "Empty";
            System.out.printf("%d. %-8s: %s%n", i + 1, slot, eqName);
        }
        System.out.println("Type the item name, slot name (e.g., HEAD), or its number to unequip:");
        String input = combatLoop.nextLine();

        EquipmentTypes slot = getEquippedSlotByInput(input, slots);
        Equipment eq = (slot != null) ? slots.get(slot) : null;
        if (slot != null && eq != null) {
            character.unequipItem(slot);
            System.out.println("Character's Attributes after unequipping: " + character.getAttributes());
            System.out.println("Character's Resistances after unequipping: " + character.getResistances());
        } else {
            System.out.println("No such equipped item found.");
        }
    }

    private List<Actor> getEnemyTargets(List<Actor> allActors) {
        return allActors.stream()
            .filter(a -> a.getActorType() != ActorTypes.CHARACTER)
            .collect(Collectors.toList());
    }
}
