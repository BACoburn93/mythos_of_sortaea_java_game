package utils;

import java.util.List;
import java.util.function.Function;

public class ListUtils {

    public static <T> T getByInput(String input, List<T> list, Function<T, String> nameGetter) {
        // Try index (1-based)
        try {
            int idx = Integer.parseInt(input);
            if (idx >= 1 && idx <= list.size()) {
                return list.get(idx - 1);
            }
        } catch (NumberFormatException ignored) {}

        for (T item : list) {
            if (input.equalsIgnoreCase(nameGetter.apply(item))) {
                return item;
            }
        }
        return null;
    }
}