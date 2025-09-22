package managers;

import actors.types.CombatActor;
import containers.GameContainer;
import enemies.EnemyDatabase;
import handlers.*;
import model.navigation.GameFlowManager;
import ui.MenuUIStrings;
import utils.GameScanner;
import utils.InputHandler;

import java.util.ArrayList;

public class GameManager {
    private final InputHandler inputHandler = new InputHandler();
    private final GameScanner gameScanner = new GameScanner();
    private boolean gameIsRunning = true;

    public void start() {
        MenuUIStrings.titleScreen();

        while (gameIsRunning) {
            MenuUIStrings.mainMenu();
            String choice = inputHandler.getInput("Choose an option: ");

            switch (choice) {
                case "1":
                    startNewGame();
                    break;

                case "2":
                    runTestCombat();
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    gameIsRunning = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        inputHandler.close();
    }

    private void startNewGame() {
        GameContainer newGame = new GameContainer();
        System.out.println("Your party is ready:");
        System.out.println(newGame.party.printPartySummary());

        GameFlowManager flowManager = new GameFlowManager();
        flowManager.startExploration(newGame.party);
    }

    private void runTestCombat() {
        // Create test game state
        GameContainer testGame = new GameContainer();

        // Create enemies and actors
        ArrayList<enemies.Enemy> testEnemies = EnemyDatabase.getDefaultEnemies();
        ArrayList<CombatActor> allActors = new ArrayList<>(testGame.party.characters);
        allActors.addAll(testEnemies);

        // Create handlers
        EquipmentHandler equipmentHandler = new EquipmentHandler(testGame.party);
        ActionHandler actionHandler = new ActionHandler(gameScanner, allActors, testGame.party, testEnemies, equipmentHandler);
        ReactionHandler reactionHandler = new ReactionHandler(gameScanner);

        // Start combat
        CombatManager combatManager = new CombatManager(
                testGame.party,
                testEnemies,
                actionHandler,
                reactionHandler
        );
        combatManager.startCombat();
    }
}

