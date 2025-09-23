package enemies.util;

import utils.StringUtils;

public class EnemyNameFormatter {
    public static String format(String baseType, String prefix, String suffix, int count) {
        StringBuilder name = new StringBuilder();

        if (prefix != null && !prefix.isBlank()) {
            name.append(prefix).append("");
        }

        name.append(StringUtils.capitalize(baseType));

        if (suffix != null && !suffix.isBlank()) {
            name.append("").append(suffix);
        }

        if (count > 1) {
            name.append(" #").append(count);
        }

        return name.toString();
    }
}
