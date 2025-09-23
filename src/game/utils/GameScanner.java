package utils;

import java.util.Scanner;

public class GameScanner {
    private final Scanner scanner = new Scanner(System.in);

    public String nextLine() {
        String input = scanner.nextLine();
        handleGameCommands(input);
        return input;
    }

    public void handleGameCommands(String input) {
        switch(input.trim().toLowerCase()) {
            case "help" -> showGuide();
            case "guide" -> showGuide();
            case "combat guide" -> showCombatGuide();
            case "save" -> saveGame();
            case "quit" -> exitGame();
        }
    }

    public void showGuide() {
        System.out.println("===== GUIDE =====");
        System.out.println();
        System.out.println("All commands shown in the guide are called WITHOUT the use of the single quotes.");
        System.out.println("For commands in combat, type 'combat guide'. So, type combat guide.");
        System.out.println();
    }

    public void showCombatGuide() {
        System.out.println("========== COMBAT GUIDE ==========");
        System.out.println();
        System.out.println("-----ACTIONS-----");
        System.out.printf("%-20s %s%n", "'Ability':", "Gives a list of abilities that the character whose turn it is can use.");
        System.out.printf("%-20s %s%n", "'Abildesc':", "Gives a list of abilities and a visual description.");
        System.out.printf("%-20s %s%n", "'Ability Name':", "Uses an ability of the chosen name if you can use it.");
        System.out.printf("%-20s %s%n", "'Item':", "Gives a list of consumables you can use.");
        System.out.printf("%-20s %s%n", "'Item Name':", "Uses an item of the chosen name if you have at least one.");
        System.out.println();
        System.out.println("-----TARGETS-----");
        System.out.printf("%-20s %s%n", "'Target Name':", "Targets the specified creature with the ability you chose. Alternatively, you can use the target's index number.");
        System.out.println();
        System.out.println("-----REACTIONS-----");
        System.out.printf("%-20s %s%n", "'Character Name':", "When asked to choose a character, type that characters name in order to");
        System.out.printf("%-20s %s%n", "", "have that character utilize a reaction, otherwise hit ENTER to pass");
        System.out.printf("%-20s %s%n", "'Defend':", "Reduces damage dealt by physical attacks based on the chosen characters Defense.");
        System.out.printf("%-20s %s%n", "'Parry':", "Has a chance to reduce damage from a physical attack to 0 based on the characters Agility.");
        System.out.printf("%-20s %s%n", "'Item':", "Lists the items you have remaining.");
        System.out.printf("%-20s %s%n", "'Observe':", "Nothing at the moment.");
        System.out.printf("%-20s %s%n", "'Pass':", "Alternate command to pass the turn.");
        System.out.println();
    }


    public void saveGame() {
        System.out.println("Saving Game...");
    }

    public void exitGame() {
        System.out.print("Are you sure you wish to quit? All unsaved progress will be lost. :(Yes or No): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("yes") || input.equals("y")) {
            System.out.println("Exiting game...");
            System.exit(0);
        } else {
            System.out.println("Exit cancelled. Returning to game...");
        }
    }

    public void close() {
        scanner.close();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
