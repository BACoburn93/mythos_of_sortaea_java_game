package items.equipment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import abilities.database.AbilityDatabase;
import items.equipment.item_types.mainhand.*;
import items.equipment.item_types.offhand.*;
import items.equipment.item_types.back.*;
import items.equipment.item_types.feet.*;
import items.equipment.item_types.head.*;
import items.equipment.item_types.legs.*;
import items.equipment.item_types.torso.*;
import items.equipment.item_types.waist.*;
import items.equipment.item_types.Neck;
import items.equipment.item_types.Ring;


/**
 * Catalog of Suppliers for concrete equipment prototypes.
 * Database uses these suppliers when wiring the Factory.
 */
public final class EquipmentRegistry {

    private static final Map<String, Supplier<Equipment>> SUPPLIERS = new HashMap<>();

    public static Map<String, Supplier<Equipment>> getAllSuppliers() {
        return Collections.unmodifiableMap(SUPPLIERS);
    }

    static {
        // register placeholders for every enum entry (preserves declaration order)
        for (EquipmentKey key : EquipmentKey.values()) {
            SUPPLIERS.put(key.key(), () -> {
                throw new UnsupportedOperationException("No supplier registered for: " + key);
            });
        }

        // Replace placeholders with real suppliers for implemented items.
        // Each lambda creates a fresh instance (builder.build()) to avoid shared mutable prototypes.

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
                .attributes(new actors.attributes.Attributes(0.0,0.0,5.0,0.0,0.0,0.0,0.0))
                .damage(8.0)
                .build()
        );

        SUPPLIERS.put(EquipmentKey.GREAT_STAFF.key(), () ->
            new Staff.Builder()
                .name("Great Staff")
                .tier(5)
                .value(3000)
                .damage(12.0)
                .attributes(new actors.attributes.Attributes(0.0,0.0,30.0,0.0,0.0,0.0,0.0))
                .abilities(new java.util.ArrayList<>(java.util.List.of(AbilityDatabase.MAGIC_DART, AbilityDatabase.LIGHTNING_BOLT)))
                .build()
        );

        // Daggers / Knives
        SUPPLIERS.put(EquipmentKey.DAGGER.key(), () ->
            new Dagger.Builder()
                .name("Dagger")
                .tier(0)
                .value(100)
                .attributes(new actors.attributes.Attributes(0.0,10.0,0.0,0.0,0.0,0.0,0.0))
                .build()
        );

        SUPPLIERS.put(EquipmentKey.KNIFE.key(), () ->
            new Dagger.Builder()
                .name("Knife")
                .tier(0)
                .value(50)
                .build()
        );

        // Bows
        SUPPLIERS.put(EquipmentKey.BOW.key(), () ->
            new Bow.Builder()
                .name("Bow")
                .tier(1)
                .value(300)
                .damage(10.0)
                .build()
        );

        SUPPLIERS.put(EquipmentKey.LONGBOW.key(), () ->
            new Bow.Builder()
                .name("Longbow")
                .tier(1)
                .value(600)
                .damage(12.0)
                .attributes(new actors.attributes.Attributes(0.0,3.0,0.0,0.0,0.0,0.0,2.0))
                .build()
        );

        // Swords
        SUPPLIERS.put(EquipmentKey.SWORD.key(), () ->
            new Sword.Builder()
                .name("Sword")
                .tier(1)
                .value(500)
                .attributes(new actors.attributes.Attributes(10.0,0.0,0.0,30.0,0.0,0.0,0.0))
                .build()
        );

        SUPPLIERS.put(EquipmentKey.SWORD.key(), () ->
            new Sword.Builder()
                .name("Excalibur")
                .tier(7)
                .value(5000)
                .attributes(new actors.attributes.Attributes(77.0,77.0,77.0,77.0,77.0, 77.0, 77.0))
                .build()
        );

        // Shields / Offhand
        SUPPLIERS.put(EquipmentKey.BUCKLER.key(), () ->
            new SmallShield.Builder()
                .name("Buckler")
                .tier(0)
                .value(40)
                .build()
        );

        SUPPLIERS.put(EquipmentKey.ROUND_SHIELD.key(), () ->
            new MediumShield.Builder()
                .name("Round Shield")
                .tier(1)
                .value(120)
                .build()
        );

        SUPPLIERS.put(EquipmentKey.TOWER_SHIELD.key(), () ->
            new LargeShield.Builder()
                .name("Tower Shield")
                .tier(2)
                .value(200)
                .build()
        );

        // Head
        SUPPLIERS.put(EquipmentKey.JESTER_HAT.key(), () ->
            new ClothHead.Builder().name("Jester Hat").tier(0).value(5).build()
        );
        SUPPLIERS.put(EquipmentKey.LEATHER_CAP.key(), () ->
            new ClothHead.Builder().name("Leather Cap").tier(0).value(10).build()
        );

        // Back
        SUPPLIERS.put(EquipmentKey.CLOAK.key(), () ->
            new ClothBack.Builder().name("Cloak").tier(0).value(15).build()
        );
        SUPPLIERS.put(EquipmentKey.CHAINLINK_MANTLE.key(), () ->
            new MediumBack.Builder().name("Chainlink Mantle").tier(1).value(80).build()
        );

        // Torso
        SUPPLIERS.put(EquipmentKey.PLATE_ARMOR.key(), () ->
            new HeavyTorso.Builder().name("Plate Armor").tier(3).value(2000).build()
        );
        SUPPLIERS.put(EquipmentKey.LEATHER_ARMOR.key(), () ->
            new HeavyTorso.Builder().name("Leather Armor").tier(0).value(100).build()
        );

        // Waist
        SUPPLIERS.put(EquipmentKey.LEATHER_BELT.key(), () ->
            new LightWaist.Builder().name("Leather Belt").tier(0).value(5).build()
        );
        SUPPLIERS.put(EquipmentKey.SASH.key(), () ->
            new ClothWaist.Builder().name("Sash").tier(0).value(3).build()
        );

        // Legs
        SUPPLIERS.put(EquipmentKey.CHAIN_LEGGINGS.key(), () ->
            new MediumLegs.Builder().name("Chain Leggings").tier(1).value(150).build()
        );
        SUPPLIERS.put(EquipmentKey.PLATE_LEGGINGS.key(), () ->
            new HeavyLegs.Builder().name("Plate Leggings").tier(3).value(900).build()
        );

        // Feet
        SUPPLIERS.put(EquipmentKey.LEATHER_BOOTS.key(), () ->
            new LightFeet.Builder().name("Leather Boots").tier(0).value(20).build()
        );
        SUPPLIERS.put(EquipmentKey.IRON_GREAVES.key(), () ->
            new HeavyFeet.Builder().name("Iron Greaves").tier(2).value(250).build()
        );

        // Accessories - Might create Accessory Abstract Class to negate need for NeckBuilder and RingBuilder
        SUPPLIERS.put(EquipmentKey.RING.key(), () ->
            new Ring.RingBuilder().name("Ring").tier(0).value(50).build()
        );
        SUPPLIERS.put(EquipmentKey.AMULET.key(), () ->
            new Neck.NeckBuilder().name("Amulet").tier(0).value(80).build()
        );
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
