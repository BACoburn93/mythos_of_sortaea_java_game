package ui;

import utils.StringUtils;

public class MenuUIStrings {
    public static void titleScreen() {
        DrawnText.printTitle();
        DrawnEnemy.printRedDragon();
    }

    public static void mainMenu() {
        System.out.println(StringUtils.titleDivider("Main Menu", 61));
    }
}
