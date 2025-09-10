package utils;

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


    private boolean startsWithVowel(String name) {
        char firstChar = Character.toUpperCase(name.charAt(0));
        return firstChar == 'A' || firstChar == 'E' || firstChar == 'I' || firstChar == 'O' || firstChar == 'U';
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
}
