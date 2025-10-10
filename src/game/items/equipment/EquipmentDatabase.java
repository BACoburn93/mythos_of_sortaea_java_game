package items.equipment;

import items.equipment.item_types.*;
import items.equipment.item_types.mainhand.Dagger;
import items.equipment.item_types.mainhand.Longbow;
import items.equipment.item_types.mainhand.Longsword;
import items.equipment.item_types.mainhand.Staff;
import items.equipment.item_types.offhand.LargeShield;
import items.equipment.item_types.torso.HeavyTorso;
import items.equipment.item_types.torso.LightTorso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;

import items.equipment.modifiers.prefixes.*;
import items.equipment.modifiers.suffixes.*;
import items.equipment.modifiers.Prefix;
import items.equipment.modifiers.Suffix;
import utils.FactoryRegistry;

public class EquipmentDatabase {
    private static final List<Equipment> EQUIPMENT_LIST = new ArrayList<>();

    // Reusable pools you can reference when registering prototypes (easy to reuse/change for tests)
    private static final List<EquipmentFactory.Weighted<Prefix>> PREFIX_POOL = Arrays.asList(
        new EquipmentFactory.Weighted<Prefix>(new Ancient(), 0.25),
        new EquipmentFactory.Weighted<Prefix>(new Enflamed(), 0.25)
    );

    private static final List<EquipmentFactory.Weighted<Suffix>> SUFFIX_POOL = Arrays.asList(
        new EquipmentFactory.Weighted<Suffix>(new OfTheNorthWind(), 0.25),
        new EquipmentFactory.Weighted<Suffix>(new OfFortitude(), 0.25)
    );


    static {
        init();
    }

    public static void init() {
        // guard so init is idempotent if called multiple times
        if (!EQUIPMENT_LIST.isEmpty()) return;

        // deterministic tests: optional
        FactoryRegistry.getEquipmentFactory().setSeed(42L);

        // Register prototypes and pools (example: Staff has pools; others use no pools for now)
        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "Staff",
            () -> new Staff(
                "Staff",
                1000,
                new Attributes(0.0, 0.0, 10.0, 0.0, 0.0, 0.0, 0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                List.of(AbilityDatabase.FIREBALL)
            ),
            PREFIX_POOL,
            0.35,
            SUFFIX_POOL,
            0.55
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "Dagger",
            () -> new Dagger(
                "Dagger",
                100,
                new Attributes(0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                List.of(),
                25.0
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "TowerShield",
            () -> new LargeShield(
                "Tower Shield",
                200,
                new Attributes(0.0,0.0,0.0,5.0,2.0,0.0,0.0),
                new Resistances(2,2,2,0,0,0,0,0,0,0,0,0),
                List.of(AbilityDatabase.SHIELD_BASH),
                3.0
            ),
            PREFIX_POOL,
            0.10,
            SUFFIX_POOL,
            0.25
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "Longsword",
            () -> new Longsword(
                "Longsword",
                500,
                new Attributes(50.0,0.0,0.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10),
                List.of(AbilityDatabase.SLASH),
                77.0
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "Longbow",
            () -> new Longbow(
                "Longbow",
                800,
                new Attributes(0.0,60.0,0.0,0.0,0.0,0.0,5.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                List.of(AbilityDatabase.POISON_DART)
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "PlateArmor",
            () -> new HeavyTorso(
                "Plate Armor",
                1000,
                new Attributes(20.0,20.0,20.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10)
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "LeatherArmor",
            () -> new LightTorso(
                "Leather Armor",
                200,
                new Attributes(2.0,2.0,2.0,2.0,2.0,2.0,2.0),
                new Resistances(1,1,1,1,1,1,1,1,1,1,1,1)
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "Ring",
            () -> new Ring(
                "Ring",
                2000,
                new Attributes(0.0,0.0,0.0,0.0,0.0,0.0,100.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        FactoryRegistry.getEquipmentFactory().registerPrototype(
            "Amulet",
            () -> new Neck(
                "Amulet",
                2000,
                new Attributes(100.0,0.0,0.0,0.0,0.0,0.0,0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
            ),
            PREFIX_POOL, 30.0, SUFFIX_POOL, 30.0
        );

        // optionally create some sample items for quick testing
        EQUIPMENT_LIST.add(FactoryRegistry.getEquipmentFactory().createByKey("Staff", null, null));
        EQUIPMENT_LIST.add(FactoryRegistry.getEquipmentFactory().createRandomByKey("Dagger", null, null));
    }

    public static List<Equipment> getEquipmentList() {
        return EQUIPMENT_LIST;
    }
}
