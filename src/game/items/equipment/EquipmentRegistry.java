package items.equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.Neck;
import items.equipment.item_types.Ring;
import items.equipment.item_types.mainhand.Dagger;
import items.equipment.item_types.mainhand.Bow;
import items.equipment.item_types.mainhand.Sword;
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
        
        // SUPPLIERS.put(EquipmentKey.LESSER_STAFF.key(), () -> new Staff(
        //         "Lesser Staff",
        //         0,
        //         20
        // ));

        // SUPPLIERS.put(EquipmentKey.STAFF.key(), () -> new Staff(
        //         "Staff",
        //         1,
        //         400,
        //         new Attributes(0.0,0.0,5.0,0.0,0.0, 0.0, 0.0),
        //         new Resistances(3,3,3,3,3,3,3,3,3,3,3,3),
        //         new ArrayList<>(),
        //         8.0
        // ));

        // SUPPLIERS.put(EquipmentKey.GREAT_STAFF.key(), () -> new Staff(
        //         "Great Staff",
        //         3,
        //         3000,
        //         true,
        //         new Attributes(0.0,0.0,30.0,0.0,0.0, 0.0, 0.0),
        //         new Resistances(10,10,10,10,10,10,10,10,10,10,10,10),
        //         new ArrayList<>(List.of(AbilityDatabase.FIREBALL)),
        //         12.0
        // ));

        // SUPPLIERS.put(EquipmentKey.DAGGER.key(), () -> new Dagger(
        //         "Dagger",
        //         0,
        //         100,
        //         new Attributes(0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0),
        //         new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
        //         new ArrayList<>(List.of())
        // ));

        // Testing the Builder option

        // If I go with the Template Approach
        // Dagger knife = Mainhand.template().name("Knife").tier(2).damage(7).buildInto(Dagger.class);

        // Staves
        SUPPLIERS.put(EquipmentKey.LESSER_STAFF.key(), () -> 
            new Staff.Builder()
                .name("Lesser Staff")
                .tier(0)
                .value(20)
                .build()
        );

        SUPPLIERS.put(EquipmentKey.STAFF.key(), () -> 
            new Staff.Builder()
                .name("Staff")
                .tier(1)
                .value(400)
                .attributes(new Attributes(0.0,0.0,5.0,0.0,0.0, 0.0, 0.0))
                .damage(8.0)
                .build()
        );

        SUPPLIERS.put(EquipmentKey.GREAT_STAFF.key(), () -> 
            new Staff.Builder()
                .name("Great Staff")
                .tier(3)
                .value(3000)
                .damage(12.0)
                .attributes(new Attributes(0.0,0.0,30.0,0.0,0.0, 0.0, 0.0))
                .abilities(new ArrayList<>(List.of(AbilityDatabase.MAGIC_DART, AbilityDatabase.FIREBALL)))
                .build()
        );


        // Daggers
        SUPPLIERS.put(EquipmentKey.DAGGER.key(), () ->
            new Dagger.Builder()
                .name("Dagger")
                .tier(0)
                .value(100)
                .attributes(new Attributes(0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0))
                .build()
        );

        SUPPLIERS.put(EquipmentKey.KNIFE.key(), () ->
            new Dagger.Builder()
                .name("Knife")
                .build()
        );

        // Bows
        SUPPLIERS.put(EquipmentKey.LONGBOW.key(), () ->
            new Bow.Builder()
                .name("Longbow")
                .tier(1)
                .value(600)
                .attributes(new Attributes(0.0,3.0,0.0,0.0,0.0,0.0,2.0))
                .damage(12.0)
                .build()
        );

        // Swords
        SUPPLIERS.put(EquipmentKey.SWORD.key(), () ->
            new Sword.Builder()
                .name("Sword")
                .tier(1)
                .value(500)
                .attributes(new Attributes(10.0, 0.0, 0.0, 30.0, 0.0, 0.0, 0.0))
                .build()
        );

        // Large shields
        SUPPLIERS.put(EquipmentKey.TOWER_SHIELD.key(), () ->
            new LargeShield.Builder()
                .name("Tower Shield")
                .tier(2)
                .value(200)
                .attributes(new Attributes(0.0,0.0,0.0,5.0,2.0,0.0,0.0))
                .build()
        );

        // SUPPLIERS.put(EquipmentKey.TOWER_SHIELD.key(), () -> new LargeShield(
        //         "Tower Shield",
        //         2,
        //         200,
        //         new Attributes(0.0,0.0,0.0,5.0,2.0,0.0,0.0),
        //         new Resistances(2,2,2,0,0,0,0,0,0,0,0,0),
        //         new ArrayList<>(List.of(AbilityDatabase.SHIELD_BASH))
        // ));

        // SUPPLIERS.put(EquipmentKey.SWORD.key(), () -> new Sword(
        //         "Sword",
        //         1,
        //         500,
        //         new Attributes(50.0,0.0,0.0,20.0,20.0,20.0,0.0),
        //         new Resistances(10,10,10,10,10,10,10,10,10,10,10,10),
        //         new ArrayList<>(List.of(AbilityDatabase.SLASH))
        // ));

        // SUPPLIERS.put(EquipmentKey.BOW.key(), () -> new Bow(
        //         "Bow",
        //         1,
        //         800,
        //         new Attributes(0.0,60.0,0.0,0.0,0.0,0.0,5.0),
        //         new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
        //         new ArrayList<>(List.of(AbilityDatabase.POISON_DART))
        // ));

        SUPPLIERS.put(EquipmentKey.PLATE_ARMOR.key(), () -> new HeavyTorso(
                "Plate Armor",
                3,
                1000,
                new Attributes(20.0,20.0,20.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10)
        ));

        SUPPLIERS.put(EquipmentKey.LEATHER_ARMOR.key(), () -> new LightTorso(
                "Leather Armor",
                1,
                200,
                new Attributes(2.0,2.0,2.0,2.0,2.0,2.0,2.0),
                new Resistances(1,1,1,1,1,1,1,1,1,1,1,1)
        ));

        SUPPLIERS.put(EquipmentKey.RING.key(), () -> new Ring(
                "Ring",
                0,
                2000,
                new Attributes(0.0,0.0,0.0,0.0,0.0,0.0,10.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
        ));

        SUPPLIERS.put(EquipmentKey.AMULET.key(), () -> new Neck(
                "Amulet",
                0,
                2000,
                new Attributes(10.0,0.0,0.0,0.0,0.0,0.0,0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
        ));
    }

    public static Supplier<Equipment> get(EquipmentKey key) {
        return (key == null) ? null : get(key.key());
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
