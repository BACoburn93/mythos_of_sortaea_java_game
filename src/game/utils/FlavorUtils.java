package utils;

import items.equipment.Equipment;
import items.equipment.modifiers.Prefix;
import items.equipment.modifiers.Suffix;

public final class FlavorUtils {
    private FlavorUtils() {}

    public static <T, P extends Modifier<T>, S extends Modifier<T>> T applyFlavor(T target, P prefix, S suffix) {
        if (prefix != null) prefix.apply(target);
        if (suffix != null) suffix.apply(target);

        return target;
    }

    public static Equipment applyFlavor(Equipment item, Prefix prefix, Suffix suffix) {
        if (item == null) return null;

        StringBuilder name = new StringBuilder();
        if (prefix != null && prefix.getName() != null && !prefix.getName().trim().isEmpty()) {
            name.append(prefix.getName().trim()).append(" ");
        }
        name.append(item.getName() == null ? "" : item.getName().trim());
        if (suffix != null && suffix.getName() != null && !suffix.getName().trim().isEmpty()) {
            name.append(" ").append(suffix.getName().trim());
        }
        item.setName(name.toString());

        if (prefix != null) prefix.apply(item);
        if (suffix != null) suffix.apply(item);

        return item;
    }

    // public static Enemy applyFlavor(Enemy enemy, Prefix prefix, Suffix suffix) {
    //     if (enemy == null) return null;

    //     StringBuilder name = new StringBuilder();
    //     if (prefix != null && prefix.getName() != null && !prefix.getName().trim().isEmpty()) {
    //         name.append(prefix.getName().trim()).append(" ");
    //     }
    //     name.append(enemy.getBaseType() == null ? "" : enemy.getBaseType().trim());
    //     if (suffix != null && suffix.getName() != null && !suffix.getName().trim().isEmpty()) {
    //         name.append(" ").append(suffix.getName().trim());
    //     }
    //     enemy.setDisplayName(name.toString());

    //     if (prefix != null) prefix.apply(enemy);
    //     if (suffix != null) suffix.apply(enemy);

    //     return enemy;
    // }
}
