package model.navigation;

import characters.Party;
import containers.GameContainer;
import enemies.EnemyDatabase;
import handlers.ActionHandler;
import handlers.EquipmentHandler;
import handlers.ReactionHandler;
import managers.CombatManager;
import utils.GameScanner;

import java.util.ArrayList;
import java.util.Arrays;

import actors.types.CombatActor;

public class GameFlowManager {
    private GameScanner scanner = new GameScanner();

    // Entry point for exploration
    public void startExploration(Party party) {
        Node startNode = buildStartingNode();
        explore(startNode, party);
    }

    private void explore(Node currentNode, Party party) {
        Node node = currentNode;

        while (node != null) {
            System.out.println("\n" + node.getDescription());

            var options = node.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, options.get(i));
            }

            int choice = -1;
            while (choice < 1 || choice > options.size()) {
                System.out.print("Choose an option: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Example: Hook for battle nodes
            if (node.getDescription().contains("battle") && choice == 1) {

                // Create enemies and actors
                ArrayList<enemies.Enemy> testEnemies = EnemyDatabase.goblinScenario();
                ArrayList<CombatActor> allActors = new ArrayList<>(party.characters);
                allActors.addAll(testEnemies);

                // Create handlers
                EquipmentHandler equipmentHandler = new EquipmentHandler(party);
                ActionHandler actionHandler = new ActionHandler(scanner, allActors, party, testEnemies, equipmentHandler);
                ReactionHandler reactionHandler = new ReactionHandler(scanner);

                // Start combat
                CombatManager combatManager = new CombatManager(
                        party,
                        testEnemies,
                        actionHandler,
                        reactionHandler
                );
                combatManager.startCombat();
            }

            node = node.getNextNode(choice);
        }

        System.out.println("Your journey ends here.");
    }

    // Build a simple branching starting node setup
    private Node buildStartingNode() {
        Node start = new Node(
            "You find yourself at the entrance of a dark forest. What will you do?",
            Arrays.asList(
                "Venture deeper into the forest",
                "Set up camp and rest",
                "Look for supplies"
            )
        );

        Node battleNode = new Node(
            "A wild goblin jumps out from behind a tree! Prepare for battle!",
            Arrays.asList(
                "Fight",
                "Run away"
            )
        );

        Node campNode = new Node(
            "You set up camp and regain your strength. What next?",
            Arrays.asList(
                "Continue your journey"
            )
        );

        Node suppliesNode = new Node(
            "You find some berries and a small pouch of gold.",
            Arrays.asList(
                "Continue your journey"
            )
        );

        Node endNode = new Node(
            "The path ends here. Your adventure concludes for now.",
            Arrays.asList()
        );

        // Connect nodes (using 1-based indices for options)
        start.addNextNode(1, battleNode);   // Venture deeper → battle
        start.addNextNode(2, campNode);     // Camp
        start.addNextNode(3, suppliesNode); // Supplies

        battleNode.addNextNode(1, endNode);  // Fight → end
        battleNode.addNextNode(2, campNode); // Run → camp

        campNode.addNextNode(1, endNode);    // Continue → end
        suppliesNode.addNextNode(1, endNode);// Continue → end

        return start;
    }
}

