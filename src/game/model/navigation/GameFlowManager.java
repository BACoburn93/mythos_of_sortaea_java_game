package model.navigation;

import characters.Party;
import handlers.ActionHandler;
import handlers.EquipmentHandler;
import handlers.ReactionHandler;
import managers.CombatManager;
import model.navigation.regions.Forest;
import utils.GameScanner;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import actors.types.CombatActor;

public class GameFlowManager {
    private GameScanner scanner = new GameScanner();
    private static final Random rng = new Random();
    private Forest currentRegion;

    public static <T> T getRandomOrNull(List<T> list) {
        int index = rng.nextInt(list.size());
        return list.get(index);
    }

    // Example: initialize region with a pool
    public GameFlowManager() {
        currentRegion = new Forest();
    }

    // Entry point for exploration
    public void startExploration(Party party) {
        Node startNode = buildStartingNode();
        explore(startNode, party);
    }

    private void explore(Node currentNode, Party party) {
        Node node = currentNode;

        while (true) { // Infinite loop for testing
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

                    combatManager.postCombat();
                }

                node = node.getNextNode(choice);
            }

            System.out.println("Your journey ends here. Restarting for testing...\n");
            node = buildStartingNode(); // Restart from the beginning for testing
        }
    }

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
            "Creatures of the Forest become hostile! Prepare for battle!",
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
            Arrays.asList(
                "Restart your adventure"
            )
        );

        // Connect nodes (using 1-based indices for options)
        start.addNextNode(1, battleNode);   // Venture deeper → battle
        start.addNextNode(2, campNode);     // Camp
        start.addNextNode(3, suppliesNode); // Supplies

        battleNode.addNextNode(1, endNode);  // Fight → end
        battleNode.addNextNode(2, campNode); // Run → camp

        campNode.addNextNode(1, endNode);    // Continue → end
        suppliesNode.addNextNode(1, endNode);// Continue → end

        endNode.addNextNode(1, start);

        return start;
    }

    // To change region, just assign a new subclass instance:
    public void changeRegion(String regionType) {
        switch (regionType.toLowerCase()) {
            case "forest":
                currentRegion = new Forest();
                System.out.println("You have entered the Forest region.");
                break;
            case "desert":
                // to do when testing region changing logic
                // currentRegion = new Desert();
                // System.out.println("You have entered the Desert region.");
                break;
            default:
                System.out.println("Unknown region.");
        }
    }
}

