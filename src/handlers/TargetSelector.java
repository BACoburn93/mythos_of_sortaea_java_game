package handlers;

import actors.Actor;
import actors.ActorTypes;
import utils.GameScanner;

import java.util.*;

public class TargetSelector {
    private final ArrayList<Actor> actors;

    public TargetSelector(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public Actor chooseEnemyTarget(GameScanner scanner) {
        ArrayList<Actor> enemies = new ArrayList<>();
        for (Actor actor : actors) {
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
                Actor enemy = enemies.get(i);
                System.out.printf("%d. %s (%d HP)%n", i + 1, enemy.getName(), enemy.getHealthValues().getValue());
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
            for (Actor enemy : enemies) {
                if (enemy.getName().equalsIgnoreCase(input)) {
                    return enemy;
                }
            }

            System.out.println("Invalid target. Try again.");
        }
    }
}
