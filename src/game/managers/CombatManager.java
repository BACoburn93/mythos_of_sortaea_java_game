package managers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import abilities.Ability;
import actors.types.CombatActor;
import characters.Party;
import characters.Character;
import enemies.Enemy;
import handlers.ActionHandler;
import handlers.EnemyHandler;
import handlers.ReactionHandler;
import handlers.ability.AbilityHandler;
import utils.ActionRequest;

public class CombatManager {
    private ArrayList<CombatActor> turnOrder;
    private Party party;
    private ArrayList<Enemy> enemies;
    private AbilityHandler abilityHandler;
    private ActionHandler actionHandler;
    private ReactionHandler reactionHandler;
    private final EnemyHandler enemyHandler = new EnemyHandler();
    private int totalExp = 0;
    
    public CombatManager(Party party, ArrayList<Enemy> enemies, AbilityHandler abilityHandler,
                         ActionHandler actionHandler,
                         ReactionHandler reactionHandler) {
        this.party = party;
        this.enemies = enemies;
        this.abilityHandler = abilityHandler;
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

        totalExp = enemies.stream().mapToInt(Enemy::getExperience).sum();

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
                        reactionHandler.handleReaction(turnOrder, party.characters);
                        // e.chooseEnemyAbility(party);

                        var plan = enemyHandler.planActions(e, party);
                        for (ActionRequest req : plan) {
                            if (req.enemy.getHealth() <= 0) break;
                            if (req.target.getHealth() <= 0) continue;

                            if (req.enemy.getManaValues().getValue() < req.ability.getManaCost()) continue;

                            req.enemy.spendMana(req.ability);
                            // dispatch through the instance ability handler
                            Random rng = new Random();
                            abilityHandler.executeAbility(req.enemy, req.target, req.ability, rng);
                        }
                    }
                    actor.handleEndTurn();
                }
            }
        }
        System.out.println("Total Experience earned: " + totalExp);
        System.out.println("Combat ended!");
    }

    public void postCombat() {
        if (partyAlive()) {
            for (Character character : party.characters) {
                character.handlePostCombat();
            }

            distributeExperience(totalExp);
        } else {
            System.out.println("Your party was defeated...");

            // Handle defeat scenario (e.g., game over, retreat, etc.)
        }

        totalExp = 0;
        enemies.clear();
    }   

    public void distributeExperience(int totalExp) {
        System.out.println("Distributing " + totalExp + " experience points among party members.");
        int partySize = party.characters.size();
        if (partySize == 0) return;

        int expPerCharacter = totalExp / partySize;

        for (characters.Character c : party.characters) {
            c.addExperience(expPerCharacter);
        }
    }
}
