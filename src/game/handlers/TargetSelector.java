package handlers;

import utils.GameScanner;
import utils.StringUtils;

import java.util.*;

import actors.ActorTypes;
import actors.types.CombatActor;
import enemies.Enemy;

public class TargetSelector {
    private final ArrayList<CombatActor> actors;

    public TargetSelector(ArrayList<CombatActor> actors) {
        this.actors = actors;
    }

    public CombatActor chooseEnemyTarget(GameScanner scanner) {
        ArrayList<CombatActor> enemies = new ArrayList<>();
        for (CombatActor actor : actors) {
            if (actor.getActorType() != ActorTypes.CHARACTER) {
                enemies.add(actor);
            }
        }

        if (enemies.isEmpty()) {
            System.out.println("No enemies available.");
            return null;
        }

        while (true) {
            System.out.println("Available targets:");
            // to do - change back to level once debugged
            StringUtils.printOptionsGrid(
                enemies,
                e -> e.getName() + 
                " (SIZE " + ((Enemy) e).getSpawnWeight() + ")" + " HP: " + 
                (int) e.getHealthValues().getValue() + "/" + (int) e.getHealthValues().getMaxValue(),
                2,
                4
            );

            System.out.println("Choose a target by name or number or type [Q]uit to go back:");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                return null;
            }

            // Try numeric index
            if (input.matches("\\d+")) {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < enemies.size()) {
                    return enemies.get(index);
                }
            }

            // Try name match
            for (CombatActor enemy : enemies) {
                if (enemy.getName().equalsIgnoreCase(input)) {
                    return enemy;
                }
            }

            System.out.println("Invalid target. Try again or type [Q]uit to go back.");
        }
    }
}
