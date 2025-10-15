package items.equipment;

import java.util.function.Supplier;

public enum EquipmentKey {
    LESSER_STAFF("lesserstaff"),
    DAGGER("dagger"),
    TOWER_SHIELD("towershield"),
    SWORD("sword"),
    BOW("bow"),
    PLATE_ARMOR("platearmor"),
    LEATHER_ARMOR("leatherarmor"),
    RING("ring"),
    AMULET("amulet");

    private final String key;
    EquipmentKey(String key) { this.key = key; }
    public String key() { return key; }

    /** helper that fetches the supplier from the registry (convenience only) */
    public Supplier<Equipment> supplier() { return EquipmentRegistry.get(this); }

    @Override public String toString() { return key; }
}
