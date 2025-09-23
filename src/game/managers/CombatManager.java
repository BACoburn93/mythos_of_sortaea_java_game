package managers;

import java.util.ArrayList;
import java.util.Comparator;

import actors.types.CombatActor;
import characters.Party;
import characters.Character;
import enemies.Enemy;
import handlers.ActionHandler;
import handlers.ReactionHandler;

public class CombatManager {
    private ArrayList<CombatActor> turnOrder;
    private Party party;
    private ArrayList<Enemy> enemies;
    private ActionHandler actionHandler;
    private ReactionHandler reactionHandler;

    public CombatManager(Party party, ArrayList<Enemy> enemies,
                         ActionHandler actionHandler,
                         ReactionHandler reactionHandler) {
        this.party = party;
        this.enemies = enemies;
        this.actionHandler = actionHandler;
        this.reactionHandler = reactionHandler;
    }

    private ArrayList<CombatActor> chooseOrder(Party party, ArrayList<Enemy> enemies) {
        ArrayList<CombatActor> initiativeOrder = new ArrayList<>();

        for (int i = 0; i < party.partySize; i++) {
            initiativeOrder.add(party.characters.get(i));
        }

        initiativeOrder.addAll(enemies);

        initiativeOrder.sort(Comparator.comparingInt(CombatActor::getInitiative).reversed());

        utils.StringUtils.stringDivider(
                "Initiative Order: " + initiativeOrder.stream()
                        .map(CombatActor::getName)
                        .toList()
                , "", 50);

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
            for (CombatActor actor : turnOrder) {
                if (!partyAlive() || enemies.isEmpty()) {
                    break; // exit early if combat ended mid-round
                }
                if (actor.getHealth() > 0) {
                    actor.handleStartTurn();
                    if (actor instanceof Character c) {
                        actionHandler.handleTurn(c);
                    } else if (actor instanceof Enemy e) {
                        reactionHandler.handleReaction(party.characters, turnOrder);
                        e.chooseEnemyAbility(party);
                    }
                    actor.handleEndTurn();
                }
            }
        }
        System.out.println("Combat ended!");
    }
}
