package utils;

import java.util.HashMap;
import java.util.Map;

import actors.attributes.Attributes;
import actors.resistances.Resistances;

public class StringUtils {
    public static String capitalize(String string) {
        if(string == null || string.isEmpty()) {
            return string;
        }

        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static void stringDivider(String string, String divider, int count) {
        System.out.println(divider.repeat(count));
        System.out.println(string);
        System.out.println(divider.repeat(count));
    }

    public static void twoStringDivider(String stringOne, String stringTwo, String divider, int count) {
        System.out.println(divider.repeat(count));
        System.out.println(stringOne);
        System.out.println(stringTwo);
        System.out.println(divider.repeat(count));
    }

    public static void threeStringDivider(String stringOne, String stringTwo, String stringThree, String divider, int count) {
        System.out.println(divider.repeat(count));
        System.out.println(stringOne);
        System.out.println(stringTwo);
        System.out.println(stringThree);
        System.out.println(divider.repeat(count));
    }

    public static void stringDividerTop(String string, String divider, int count) {
        System.out.println(divider.repeat(count));
        System.out.println(string);
    }

    public static void stringDividerBottom(String string, String divider, int count) {
        System.out.println(string);
        System.out.println(divider.repeat(count));
    }

    public static void stringTitle(String title, String accent, int count) {
        System.out.println(accent.repeat(count) + " " + title + " " + accent.repeat(count));
    }


    public static boolean startsWithVowel(String name) {
        char firstChar = Character.toUpperCase(name.charAt(0));
        return firstChar == 'A' || firstChar == 'E' || firstChar == 'I' || firstChar == 'O' || firstChar == 'U';
    }

    public static String titleDivider(String title, int totalWidth) {
        StringBuilder sb = new StringBuilder();

        int titleLength = title.length() + 2; 
        int padding = (totalWidth - titleLength) / 2;
        padding = Math.max(0, padding);

        sb.append("=".repeat(padding))
        .append(" ")
        .append(title)
        .append(" ")
        .append("=".repeat(totalWidth - padding - titleLength));
        // .append("\n");

        return sb.toString();
    }

    public static java.util.List<String> wrapText(String text, int width) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        if (text == null) return lines;
        while (text.length() > width) {
            int wrapAt = text.lastIndexOf(' ', width);
            if (wrapAt <= 0) wrapAt = width;
            lines.add(text.substring(0, wrapAt));
            text = text.substring(wrapAt).trim();
        }
        if (!text.isEmpty()) lines.add(text);
        return lines;
    }

    public static String formatAttributes(Attributes attrs) {
        StringBuilder sb = new StringBuilder();

        if (attrs.getStrength().getValue() != 0) sb.append(formatAttr("STR", attrs.getStrength().getValue()));
        if (attrs.getAgility().getValue() != 0) sb.append(formatAttr("AGI", attrs.getAgility().getValue()));
        if (attrs.getKnowledge().getValue() != 0) sb.append(formatAttr("KNOW", attrs.getKnowledge().getValue()));
        if (attrs.getDefense().getValue() != 0) sb.append(formatAttr("DEF", attrs.getDefense().getValue()));
        if (attrs.getResilience().getValue() != 0) sb.append(formatAttr("RES", attrs.getResilience().getValue()));
        if (attrs.getSpirit().getValue() != 0) sb.append(formatAttr("SPIR", attrs.getSpirit().getValue()));
        if (attrs.getLuck().getValue() != 0) sb.append(formatAttr("LUCK", attrs.getLuck().getValue()));

        return sb.toString().replaceAll(", $", ""); // Trim trailing comma
    }


    public static String formatResistances(Resistances res) {
        StringBuilder sb = new StringBuilder();

        if (res.getBludgeoning().getValue() != 0) sb.append(formatAttr("BLUDGE", res.getBludgeoning().getValue()));
        if (res.getPiercing().getValue() != 0) sb.append(formatAttr("PIERC", res.getPiercing().getValue()));
        if (res.getSlashing().getValue() != 0) sb.append(formatAttr("SLASH", res.getSlashing().getValue()));
        if (res.getEarth().getValue() != 0) sb.append(formatAttr("EARTH", res.getEarth().getValue()));
        if (res.getFire().getValue() != 0) sb.append(formatAttr("FIRE", res.getFire().getValue()));
        if (res.getIce().getValue() != 0) sb.append(formatAttr("ICE", res.getIce().getValue()));
        if (res.getLightning().getValue() != 0) sb.append(formatAttr("LGTN", res.getLightning().getValue()));
        if (res.getVenom().getValue() != 0) sb.append(formatAttr("VENOM", res.getVenom().getValue()));
        if (res.getWater().getValue() != 0) sb.append(formatAttr("WATER", res.getWater().getValue()));
        if (res.getWind().getValue() != 0) sb.append(formatAttr("WIND", res.getWind().getValue()));
        if (res.getDarkness().getValue() != 0) sb.append(formatAttr("DARK", res.getDarkness().getValue()));
        if (res.getLight().getValue() != 0) sb.append(formatAttr("LIGHT", res.getLight().getValue()));

        return sb.toString().replaceAll(", $", ""); // Trim trailing comma
    }


    private static String formatAttr(String name, double value) {
        return String.format("%-7s %6.2f, ", name, value);
    }

    public static String formatInt(double value) {
        return String.valueOf((int) value);
    }

    public static String formatName(String baseType, String prefix, String suffix, int count) {
        StringBuilder name = new StringBuilder();

        if (prefix != null && !prefix.isBlank()) {
            name.append(prefix);
        }

        boolean prefixEndsWithSpace = prefix != null && !prefix.isBlank() && prefix.endsWith(" ");

        if (prefixEndsWithSpace || prefix == null || prefix.isBlank()) {
            name.append(capitalize(baseType));
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

    public static int getNextCount(String baseType, String prefix, String suffix) {
        Map<String, Integer> comboCounts = new HashMap<>();

        String key = (baseType + "|" + prefix + "|" + suffix).toLowerCase();
        int count = comboCounts.getOrDefault(key, 0) + 1;
        comboCounts.put(key, count);
        
        return count;
    }

    public static <T> void printOptionsGrid
    (
        java.util.List<T> options,
        java.util.function.Function<T, String> displayMapper,
        int columns,
        int spacing
    ) 
    {
        System.out.println();
        
        if (options == null || options.isEmpty()) return;
        int col = 0;
        String format = "%%%ds) %%-%ds";
        int maxIndexWidth = String.valueOf(options.size()).length();
        int maxOptionWidth = options.stream()
            .map(displayMapper)
            .mapToInt(String::length)
            .max()
            .orElse(10);

        String colFormat = String.format(format, maxIndexWidth, maxOptionWidth + spacing);

        for (int i = 0; i < options.size(); i++) {
            String display = displayMapper.apply(options.get(i));
            System.out.printf(colFormat, i + 1, display);
            col++;
            if (col == columns) {
                System.out.println();
                col = 0;
            }
        }
        if (col != 0) System.out.println();

        System.out.println();
    }

    public static String toSlotKey(String enumName) {
        String lower = enumName.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

}
