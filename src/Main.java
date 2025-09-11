import containers.CombatContainer;
import containers.GameContainer;
import enemies.EnemyDatabase;
import utils.InputHandler;
import utils.StringUtils;

public class Main {
    public void run() {
        GameContainer newGame = new GameContainer();
        java.util.ArrayList<enemies.Enemy> enemies = EnemyDatabase.getDefaultEnemies();
        CombatContainer combat = new CombatContainer(newGame.party, enemies);
        InputHandler inputHandler = new InputHandler();

        // Boolean to handle stopping the game state
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            // Render the game state
            StringUtils.stringDivider("Mythos of Sortaea - Main Menu", "=", 50);
            System.out.println("1. Start Combat");
            System.out.println("2. Exit");

            // Input handling
            String choice = inputHandler.getInput("Choose an option: ");

            // Update the game state based on input
            switch (choice) {
                case "1":
                    combat.startCombat(newGame.party, enemies);
                    break;
                case "2":
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

