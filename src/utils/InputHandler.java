package utils;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public static <T> T getItemByInput(String input, List<T> itemList, Function<T, String> nameExtractor) {
        input = input.trim().toLowerCase();

        // Try to parse as number
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < itemList.size()) {
                return itemList.get(index);
            }
        } catch (NumberFormatException e) {
            // Not a number
        }

        // Try to find by name using provided name extractor
        for (T item : itemList) {
            String names = nameExtractor.apply(item);
            if (names == null) continue;

            for (String name : names.split("\\|")) {
                if (name.trim().equalsIgnoreCase(input)) {
                    return item;
                }
            }
        }

        return null;
    }
}
