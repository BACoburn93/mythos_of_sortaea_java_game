package ui;

import utils.StringUtils;

public class MenuUIStrings {
    public static void titleScreen() {
        DrawnText.printTitle();
        DrawnEnemy.printRedDragon();
    }

    public static void mainMenu() {
        System.out.println(StringUtils.titleDivider("Main Menu", 50));
        System.out.println("1. Start Game");
        System.out.println("2. Combat Test");
        System.out.println("3. Exit");
    }
}
