package items.equipment;

import java.util.function.Supplier;

public enum EquipmentKey {
    // Staff
    LESSER_STAFF("lesserstaff"),
    STAFF("staff"),
    GREAT_STAFF("greatstaff"),
    // Swords
    SWORD("sword"),
    // Bows
    BOW("bow"),
    LONGBOW("longbow"),
    // Dagger
    KNIFE("knife"),
    DAGGER("dagger"),
    // Offhand
    BUCKLER("buckler"),
    ROUND_SHIELD("roundshield"),
    TOWER_SHIELD("towershield"),
    // Head
    JESTER_HAT("jesterhat"),
    LEATHER_CAP("leathercap"),
    // Back
    CLOAK("cloak"),
    CHAINLINK_MANTLE("chainlinkmantle"),
    // Torso
    PLATE_ARMOR("platearmor"),
    LEATHER_ARMOR("leatherarmor"),
    // Waist
    LEATHER_BELT("leatherbelt"),
    SASH("sash"),
    // Legs
    CHAIN_LEGGINGS("chainleggings"),
    PLATE_LEGGINGS("plateleggings"),
    // Feet
    LEATHER_BOOTS("leatherboots"),
    IRON_GREAVES("irongreaves"),
    // Accessories
    RING("ring"),
    AMULET("amulet");

    private final String key;
    EquipmentKey(String key) { this.key = key; }
    public String key() { return key; }

    // Helper that fetches the supplier from the registry (convenience only)
    public Supplier<Equipment> supplier() { return EquipmentRegistry.get(this); }

    @Override public String toString() { return key; }
}
