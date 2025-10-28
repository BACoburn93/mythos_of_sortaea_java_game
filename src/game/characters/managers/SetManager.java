package characters.managers;

import characters.Character;
import characters.managers.AttributeSetBonusApplier;
import items.equipment.equipment_slots.EquipmentSlot;
import items.equipment.sets.SetBonus;
import items.equipment.sets.SetBonusRegistry;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

// Central manager for set-bonus computation and reconciliation.
// - Computes aggregated SetBonus per registered tag for a Character (based on equipped items).
// - Tracks previously-applied aggregated per-character so only diffs are applied/removed.
// - Uses a pluggable SetBonusApplier to actually apply/remove bonuses (default tries Character.applySetBonus/removeSetBonus).

public final class SetManager {

    public interface SetBonusApplier {
        void apply(Character c, String sourceKey, SetBonus bonus);
        void remove(Character c, String sourceKey);
    }

    private static final SetManager INSTANCE = new SetManager();

    // per-character currently-applied aggregated bonuses (keyed by "set:<tag>")
    private final Map<Character, Map<String, SetBonus>> applied = new HashMap<>();

    // helper for logging: safely obtain set-tags from an equipment instance
    private static String safeGetTags(Object eq) {
        if (eq == null) return "";
        try {
            Object tagsObj = eq.getClass().getMethod("getSetTags").invoke(eq);
            if (tagsObj instanceof java.util.Set<?> tags && !tags.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (Object t : tags) {
                    if (t == null) continue;
                    if (!first) sb.append(", ");
                    sb.append(t.toString());
                    first = false;
                }
                return sb.toString();
            }
        } catch (Throwable ignored) { }
        return "";
    }

    // applier used to actually mutate character state; can be replaced for tests or future refactor
    private SetBonusApplier applier = createDefaultApplier();

    private SetManager() {}

    public static SetManager getInstance() { return INSTANCE; }

    public void setApplier(SetBonusApplier applier) {
        this.applier = (applier == null) ? createDefaultApplier() : applier;
    }

    // Recompute aggregated SetBonus for character and reconcile (apply/remove) differences.
    // Source keys are "set:<tag>".
    public void reconcile(Character c) {
        if (c == null) return;

        System.out.println("[SetManager] reconciling sets for " + (c.getName()));

        Map<String, Integer> tagCounts = new HashMap<>();
        try {
            Collection<EquipmentSlot> slots = c.getEquipmentSlots().values();
            for (EquipmentSlot slot : slots) {
                if (slot == null) continue;
                var eq = slot.getEquippedItem();
                if (eq == null) continue;
                System.out.println("[SetManager] slot " + slot.getName() + " -> " + (eq != null ? eq.getName() + " tags=" + safeGetTags(eq) : "empty"));
                // Prefer instance-set tags (getSetTags) so per-item tags added via Equipment.setSetTags(...) are counted.
                try {
                    java.util.Set<String> tags = eq.getSetTags();
                    if (tags != null) {
                        for (String tag : tags) {
                            if (tag == null) continue;
                            tagCounts.merge(tag.toLowerCase(), 1, Integer::sum);
                        }
                    }
                } catch (Throwable t) {
                    // fallback to the SetTagged interface if present on the item
                    if (eq instanceof items.equipment.sets.SetTagged st) {
                        for (String tag : st.getSetTags()) {
                            if (tag == null) continue;
                            tagCounts.merge(tag.toLowerCase(), 1, Integer::sum);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return; // defensive
        }

        System.out.println("[SetManager] tagCounts = " + tagCounts);

        Map<String, SetBonus> newAggregated = new HashMap<>();
        for (String tag : SetBonusRegistry.allTags()) {
            int count = tagCounts.getOrDefault(tag.toLowerCase(), 0);
            SortedMap<Integer, SetBonus> thresholds = SetBonusRegistry.getForTag(tag);
            if (thresholds.isEmpty()) continue;

            SetBonus agg = new SetBonus();
            for (Map.Entry<Integer, SetBonus> e : thresholds.entrySet()) {
                if (e.getKey() <= count) agg.combine(e.getValue());
            }
            if (!agg.isEmpty()) newAggregated.put("set:" + tag.toLowerCase(), agg);
        }

        System.out.println("[SetManager] newAggregated = " + newAggregated);

        Map<String, SetBonus> previous = applied.getOrDefault(c, Map.of());

        // remove ones present previously but not in newAggregated (or changed)
        for (String key : previous.keySet()) {
            SetBonus prev = previous.get(key);
            SetBonus now = newAggregated.get(key);
            if (now == null || !prev.equals(now)) {
                applier.remove(c, key);
            }
        }

        // apply new or changed ones
        for (Map.Entry<String, SetBonus> e : newAggregated.entrySet()) {
            String key = e.getKey();
            SetBonus now = e.getValue();
            SetBonus prev = previous.get(key);
            if (prev == null || !prev.equals(now)) {
                applier.apply(c, key, now);
            }
        }

        // store snapshot (make a shallow copy)
        applied.put(c, new HashMap<>(newAggregated));
    }

    private static SetBonusApplier createDefaultApplier() {
        // default: apply set bonuses to Attributes via the AttributeSetBonusApplier
        return new AttributeSetBonusApplier();
    }
}
