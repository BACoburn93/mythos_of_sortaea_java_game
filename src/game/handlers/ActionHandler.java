package handlers;

import characters.Character;
import characters.Party;
import enemies.Enemy;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.InputHandler;
import utils.StringUtils;
import abilities.actions.ActionTypes;
import actors.types.CombatActor;

import java.util.*;

public class ActionHandler {
    private final GameScanner scanner;
    private final AbilityHandler abilityHandler;
    private final EquipmentHandler equipmentHandler;
    private final SortHandler sortHandler;
    private final InputHandler inputHandler;
    private final ArrayList<CombatActor> actors;
    private Party party;
    

    public ActionHandler(GameScanner scanner, ArrayList<CombatActor> actors, Party party, ArrayList<Enemy> enemies, EquipmentHandler equipmentHandler) {
        this.scanner = scanner;
        this.abilityHandler = new AbilityHandler(scanner, new TargetSelector(actors), actors, enemies);
        this.inputHandler = new InputHandler();
        this.sortHandler = new SortHandler(scanner, inputHandler);
        this.party = party;
        this.equipmentHandler = equipmentHandler;
        this.actors = actors;
    }

    public void handleTurn(Character character) {
        character.setActionPoints(character.getMaxActionPoints());

        while (character.getActionPoints() > 0) {
            CombatUIStrings.formatPartyStat(party.getCharacters());

            // Use enum selection instead of raw string input
            ActionTypes action = inputHandler.promptEnumSelection(ActionTypes.class, " ");

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
                }
                case ABILITY -> {
                    abilityHandler.handleAbilityAction(character);
                }
                case EQUIP -> {
                    equipmentHandler.handleEquip(scanner, character);
                }
                case UNEQUIP -> {
                    equipmentHandler.handleUnequip(scanner, character);
                }
                case OBSERVE -> {
                    character.handleObserve(this.actors);
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

    private void handleSortAction(Party party, Character character) {
        sortHandler.handleSortAction(party, character);
    }
}