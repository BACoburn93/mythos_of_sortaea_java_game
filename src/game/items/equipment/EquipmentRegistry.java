package items.equipment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.Neck;
import items.equipment.item_types.Ring;
import items.equipment.item_types.mainhand.Dagger;
import items.equipment.item_types.mainhand.Longbow;
import items.equipment.item_types.mainhand.Longsword;
import items.equipment.item_types.mainhand.Staff;
import items.equipment.item_types.offhand.LargeShield;
import items.equipment.item_types.torso.HeavyTorso;
import items.equipment.item_types.torso.LightTorso;

/**
 * Catalog of Suppliers for concrete equipment prototypes.
 * Database uses these suppliers when wiring the Factory.
 */
public final class EquipmentRegistry {
    private static final Map<String, Supplier<Equipment>> SUPPLIERS = new HashMap<>();

    private EquipmentRegistry() {}

    static {
        // Register suppliers with reasonably complete default prototype constructors.
        // These suppliers should produce a base prototype item (Database may flavor it).
        SUPPLIERS.put("staff", () -> new Staff(
                "Staff",
                1000,
                new Attributes(0.0, 0.0, 10.0, 0.0, 0.0, 0.0, 0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                java.util.List.of(AbilityDatabase.FIREBALL)
        ));

        SUPPLIERS.put("dagger", () -> new Dagger(
                "Dagger",
                100,
                new Attributes(0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                java.util.List.of(),
                25.0
        ));

        SUPPLIERS.put("largeshield", () -> new LargeShield(
                "Large Shield",
                200,
                new Attributes(0.0,0.0,0.0,5.0,2.0,0.0,0.0),
                new Resistances(2,2,2,0,0,0,0,0,0,0,0,0),
                java.util.List.of(AbilityDatabase.SHIELD_BASH),
                3.0
        ));

        SUPPLIERS.put("longsword", () -> new Longsword(
                "Longsword",
                500,
                new Attributes(50.0,0.0,0.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10),
                java.util.List.of(AbilityDatabase.SLASH),
                77.0
        ));

        SUPPLIERS.put("longbow", () -> new Longbow(
                "Longbow",
                800,
                new Attributes(0.0,60.0,0.0,0.0,0.0,0.0,5.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                java.util.List.of(AbilityDatabase.POISON_DART)
        ));

        SUPPLIERS.put("heavytorso", () -> new HeavyTorso(
                "Heavy Torso",
                1000,
                new Attributes(20.0,20.0,20.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10)
        ));

        SUPPLIERS.put("lighttorso", () -> new LightTorso(
                "Light Torso",
                200,
                new Attributes(2.0,2.0,2.0,2.0,2.0,2.0,2.0),
                new Resistances(1,1,1,1,1,1,1,1,1,1,1,1)
        ));

        SUPPLIERS.put("ring", () -> new Ring(
                "Ring",
                2000,
                new Attributes(0.0,0.0,0.0,0.0,0.0,0.0,100.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
        ));

        SUPPLIERS.put("neck", () -> new Neck(
                "Neck",
                2000,
                new Attributes(100.0,0.0,0.0,0.0,0.0,0.0,0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
        ));
    }

    public static Supplier<Equipment> get(String key) {
        if (key == null) return null;
        return SUPPLIERS.get(key.trim().toLowerCase());
    }

    public static java.util.Set<String> allKeys() {
        return java.util.Collections.unmodifiableSet(SUPPLIERS.keySet());
    }

    public static void register(String key, Supplier<Equipment> supplier) {
        if (key == null || supplier == null) return;
        SUPPLIERS.put(key.trim().toLowerCase(), supplier);
    }
}
