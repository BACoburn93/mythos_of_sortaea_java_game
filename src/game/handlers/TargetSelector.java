package handlers;

import actors.ActorTypes;
import actors.types.CombatActor;
import utils.GameScanner;
import utils.StringUtils;

import java.util.*;

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
            for (int i = 0; i < enemies.size(); i++) {
                CombatActor enemy = enemies.get(i);
                System.out.printf("%d. %s (%s HP)%n", i + 1, enemy.getName(), StringUtils.formatInt(enemy.getHealthValues().getValue()));
            }

            System.out.println("Choose a target by name or number:");
            String input = scanner.nextLine();

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

            System.out.println("Invalid target. Try again.");
        }
    }
}
