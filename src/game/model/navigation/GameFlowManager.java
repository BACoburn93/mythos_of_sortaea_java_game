package model.navigation;

import characters.Party;
import enemies.Enemy;
import enemies.EnemyFactory;
import handlers.ActionHandler;
import handlers.EquipmentHandler;
import handlers.ReactionHandler;
import managers.CombatManager;
import utils.GameScanner;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import actors.types.CombatActor;

public class GameFlowManager {
    private GameScanner scanner = new GameScanner();
    private static final Random rng = new Random();
    private Region currentRegion;

    public static <T> T getRandomOrNull(List<T> list) {
        int index = rng.nextInt(list.size());
        return list.get(index);
    }

    // Example: initialize region with a pool
    public GameFlowManager() {
        // Example region setup
        Map<String, Double> forestEnemies = new java.util.HashMap<>();
        forestEnemies.put("goblin", 0.4);
        forestEnemies.put("orc", 0.15);
        forestEnemies.put("dragon", 0.1);
        currentRegion = new Region("Dark Forest", forestEnemies);
    }

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

            StringUtils.printOptionsGrid(
                options,
                o -> o,
                1,
                0
            );

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
                // Set up actors for combat based on region
                ArrayList<enemies.Enemy> enemies = currentRegion.generateEnemies();
                ArrayList<CombatActor> allActors = currentRegion.setupCombatActors(party, enemies);

                // Create handlers
                EquipmentHandler equipmentHandler = new EquipmentHandler(party);
                ActionHandler actionHandler = new ActionHandler(scanner, allActors, party, enemies, equipmentHandler);
                ReactionHandler reactionHandler = new ReactionHandler(scanner);

                // Start combat
                CombatManager combatManager = new CombatManager(
                        party,
                        enemies,
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

