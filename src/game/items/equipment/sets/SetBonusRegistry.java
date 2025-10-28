package items.equipment.sets;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Registry mapping tag -> threshold -> SetBonus.
 * Register your set bonuses at game startup.
 */
public class SetBonusRegistry {
    private static final Map<String, TreeMap<Integer, SetBonus>> registry = new HashMap<>();

    public static void register(String tag, int threshold, SetBonus bonus) {
        if (tag == null || bonus == null) return;
        TreeMap<Integer, SetBonus> map = registry.computeIfAbsent(tag.toLowerCase(), k -> new TreeMap<>());
        map.put(threshold, bonus);
    }

    public static SortedMap<Integer, SetBonus> getForTag(String tag) {
        TreeMap<Integer, SetBonus> m = registry.get(tag == null ? null : tag.toLowerCase());
        return (m == null) ? new TreeMap<>() : Collections.unmodifiableSortedMap(m);
    }

    public static java.util.Set<String> allTags() { return Collections.unmodifiableSet(registry.keySet()); }
}