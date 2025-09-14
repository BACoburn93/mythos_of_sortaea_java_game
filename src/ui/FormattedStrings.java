package ui;

import java.util.List;

import abilities.interfaces.Nameable;
import abilities.interfaces.NameableWithQuantity;

public class FormattedStrings {
    // Formats a list of NameableWithQuantity objects into a numbered list string
    public static String formatQNumberedList(List<? extends NameableWithQuantity> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            var item = list.get(i);
            sb.append(String.format("%d. %-20s (x%d)%n", i + 1, item.getName(), item.getQuantity()));
        }
        return sb.toString();
    }

    // Formats a list of Nameable without quantity objects into a numbered list string
    public static String formatNumberedList(List<? extends Nameable> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            var item = list.get(i);
            sb.append(String.format("%d. %-20s%n", i + 1, item.getName()));
        }
        return sb.toString();
    }

    // Formats a command and its description into a consistent layout
    public static String formatKeyValue(String command, String description) {
        return String.format("%-20s %s%n", command, description);
    }

    // Prints a title with surrounding dividers for emphasis
    public static String titleDivider(String title, int totalWidth) {
        StringBuilder sb = new StringBuilder();

        int titleLength = title.length() + 2; // account for spaces around title
        int padding = (totalWidth - titleLength) / 2;
        padding = Math.max(0, padding); // prevent negative repeat

        sb.append("=".repeat(padding))
        .append(" ")
        .append(title)
        .append(" ")
        .append("=".repeat(totalWidth - padding - titleLength));
        // .append("\n");

        return sb.toString();
    }
}
