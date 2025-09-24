package enemies.util;

import utils.StringUtils;

public class EnemyNameFormatter {
    public static String format(String baseType, String prefix, String suffix, int count) {
        StringBuilder name = new StringBuilder();

        if (prefix != null && !prefix.isBlank()) {
            name.append(prefix);
        }

        // Check if prefix ends with a space to establish capitalization
        boolean prefixEndsWithSpace = prefix != null && !prefix.isBlank() && prefix.endsWith(" ");

        if (prefixEndsWithSpace) {
            name.append(StringUtils.capitalize(baseType));
        } else {
            name.append(baseType);
        }

        if (suffix != null && !suffix.isBlank()) {
            name.append(suffix);
        }

        if (count > 1) {
            name.append(" #").append(count);
        }

        return name.toString();
    }
}
