import java.util.ArrayList;

import actors.CombatActor;
// import containers.CombatContainer;
import containers.GameContainer;
import enemies.EnemyDatabase;
import ui.MenuUIStrings;
import utils.InputHandler;

public class Main {
    public void run() {
        // Render Title Screen
        MenuUIStrings.titleScreen();

        // int titleStrWidth = 30;
        // String titleStr = MenuUIStrings.titleDivider("Mythos of Sortaea", titleStrWidth);
        // StringUtils.stringDivider(titleStr, "=", titleStrWidth);

        // GameContainer newGame = new GameContainer();
        // java.util.ArrayList<enemies.Enemy> enemies = EnemyDatabase.getDefaultEnemies();
        // CombatContainer combat = new CombatContainer(newGame.party, enemies);
        InputHandler inputHandler = new InputHandler();

        // Boolean to handle stopping the game state
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            // Render the game state
            MenuUIStrings.mainMenu();

            // Input handling
            String choice = inputHandler.getInput("Choose an option: ");

            // Container to initialize the game state
            // GameContainer newGame = new GameContainer();

            // Update the game state based on input
            switch (choice) {
                case "1":
                    GameContainer testGame = new GameContainer();
                    ArrayList<enemies.Enemy> testEnemies = EnemyDatabase.getDefaultEnemies();
                    ArrayList<CombatActor> allActors = new ArrayList<>(testGame.party.characters);  // add characters
                    allActors.addAll(testEnemies);  // add enemies

                    // Instantiate scanner and handlers
                    utils.GameScanner gameScanner = new utils.GameScanner();
                    handlers.EquipmentHandler equipmentHandler = new handlers.EquipmentHandler(testGame.party);
                    handlers.ActionHandler actionHandler = new handlers.ActionHandler(gameScanner, allActors, testGame.party, testEnemies, equipmentHandler);
                    handlers.ReactionHandler reactionHandler = new handlers.ReactionHandler(gameScanner);
                    

                    // Create and start combat manager
                    managers.CombatManager combatManager = new managers.CombatManager(
                        testGame.party, testEnemies, actionHandler, reactionHandler
                    );
                    combatManager.startCombat();

                    break;

                case "2":
                    // combat.startCombat(newGame.party, enemies);
                    // GameContainer newGame = new GameContainer();
                    // java.util.ArrayList<enemies.Enemy> enemies = EnemyDatabase.getDefaultEnemies();
                    // CombatContainer combat = new CombatContainer(newGame.party, enemies);
                    // combat.startCombat(newGame.party, enemies);
                    System.out.println("Scenario not implemented yet.");
                    break;
                case "3":
                    gameIsRunning = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            } 
        }
        inputHandler.close();
    }

    // Entry point of the application
    public static void main(String[] args) {
        new Main().run();
    }
}

