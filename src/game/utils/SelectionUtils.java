package utils;

import java.util.List;
import java.util.function.Function;
import ui.CombatUIStrings;
import utils.GameScanner;

public class SelectionUtils {
    public static <T> T selectFromList(
            List<T> list,
            GameScanner scanner,
            Function<T, String> nameGetter,
            Function<T, String> toStringer,
            String prompt,
            String listCommand,
            int pageSize
    ) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                return null;
            }
            if (input.equalsIgnoreCase(listCommand) || input.equalsIgnoreCase("list")) {
                CombatUIStrings.displayPaginatedList(
                    list,
                    pageSize,
                    scanner.getScanner(),
                    toStringer
                );
                continue;
            }

            // Try index
            if (input.matches("\\d+")) {
                int idx = Integer.parseInt(input) - 1;
                if (idx >= 0 && idx < list.size()) {
                    return list.get(idx);
                }
            }

            // Try name match
            for (T item : list) {
                if (nameGetter.apply(item).equalsIgnoreCase(input)) {
                    return item;
                }
            }

            System.out.println("No such item found.");
        }
    }
}
