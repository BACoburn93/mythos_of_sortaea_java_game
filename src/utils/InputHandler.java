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

    public <E extends Enum<E>> E promptEnumSelection(Class<E> enumClass, String prompt) {
        E[] values = enumClass.getEnumConstants();

        System.out.println(prompt);
        
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%d. %s%n", i + 1, formatEnumName(values[i].name()));
        }

        String input = scanner.nextLine().trim().toLowerCase();

        try {
            int index = Integer.parseInt(input);
            if (index >= 1 && index <= values.length) {
                return values[index - 1];
            }
        } catch (NumberFormatException ignored) {
            for (E value : values) {
                if (value.name().toLowerCase().startsWith(input)) {
                    return value;
                }
            }
        }

        System.out.println("Invalid input. Defaulting to: " + formatEnumName(values[0].name()));
        return values[0]; // Default fallback
    }

    public String formatEnumName(String name) {
        return name.charAt(0) + name.substring(1).toLowerCase().replace('_', ' ');
    }

}
