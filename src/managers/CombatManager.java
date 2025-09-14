package managers;

import java.util.ArrayList;
import java.util.Comparator;

import actors.Actor;
import characters.Party;
import characters.Character;
import enemies.Enemy;
import handlers.ActionHandler;
import handlers.ReactionHandler;
import handlers.EquipmentHandler;

public class CombatManager {
    private ArrayList<Actor> turnOrder;
    private Party party;
    private ArrayList<Enemy> enemies;
    private ActionHandler actionHandler;
    private ReactionHandler reactionHandler;
    private EquipmentHandler equipmentHandler;

    public CombatManager(Party party, ArrayList<Enemy> enemies,
                         ActionHandler actionHandler,
                         ReactionHandler reactionHandler,
                         EquipmentHandler equipmentHandler) {
        this.party = party;
        this.enemies = enemies;
        this.actionHandler = actionHandler;
        this.reactionHandler = reactionHandler;
        this.equipmentHandler = equipmentHandler;
    }

    private ArrayList<Actor> chooseOrder(Party party, ArrayList<Enemy> enemies) {
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

    private void setupTurnOrder() {
        this.turnOrder = chooseOrder(party, enemies);
    }

    private boolean partyAlive() {
        for (Character c : party.characters) {
            if (c.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    public void startCombat() {
        setupTurnOrder();

        while (partyAlive() && !enemies.isEmpty()) {
            for (Actor actor : turnOrder) {
                if (!partyAlive() || enemies.isEmpty()) {
                    break; // exit early if combat ended mid-round
                }
                if (actor.getHealth() > 0) {
                    if (actor instanceof Character c) {
                        actionHandler.handleTurn(c);
                    } else if (actor instanceof Enemy e) {
                        reactionHandler.handleReaction(party.characters, turnOrder);
                        e.chooseEnemyAbility(party);
                    }
                }
            }
        }
        System.out.println("Combat ended!");
    }
}
