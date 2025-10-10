package items.equipment;

import java.util.function.Supplier;

public enum EquipmentKey {
    LESSERSTAFF("lesserstaff"),
    DAGGER("dagger"),
    TOWERSHIELD("towershield"),
    LONGSWORD("longsword"),
    LONGBOW("longbow"),
    PLATEARMOR("platearmor"),
    LEATHERARMOR("leatherarmor"),
    RING("ring"),
    AMULET("amulet");

    private final String key;
    EquipmentKey(String key) { this.key = key; }
    public String key() { return key; }

    /** helper that fetches the supplier from the registry (convenience only) */
    public Supplier<Equipment> supplier() { return EquipmentRegistry.get(this); }

    @Override public String toString() { return key; }
}
