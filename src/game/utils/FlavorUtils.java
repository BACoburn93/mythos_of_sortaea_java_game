package utils;

import items.equipment.Equipment;
import items.equipment.modifiers.Prefix;
import items.equipment.modifiers.Suffix;

public final class FlavorUtils {
    private FlavorUtils() {}

    public static <T extends Equipment> T applyFlavor(T item, Prefix prefix, Suffix suffix) {
        if (item == null) return null;

        String name = (prefix != null ? prefix.getName().trim() + " " : "") + item.getName()
            + (suffix != null ? " " + suffix.getName().trim() : "");

        item.setName(name);

        if (prefix != null) prefix.apply(item);
        if (suffix != null) suffix.apply(item);
        
        return item;
    }
}
