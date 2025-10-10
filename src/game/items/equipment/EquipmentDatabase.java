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
import java.util.List;

import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;

import items.equipment.modifiers.prefixes.*;
import items.equipment.modifiers.suffixes.*;

public class EquipmentDatabase {
    private static final List<Equipment> EQUIPMENT_LIST = new ArrayList<>();
    static {
        init();
    }

    public static void init() {
        // guard so init is idempotent if called multiple times
        if (!EQUIPMENT_LIST.isEmpty()) return;

        // deterministic tests: optional
        EquipmentFactory.INSTANCE.setSeed(42L);

        // Register prototypes and pools (example: Staff has pools; others use no pools for now)
        EquipmentFactory.INSTANCE.registerPrototype(
        "Staff",
        () -> new Staff(
                "Staff",
                1000,
                new Attributes(0.0, 0.0, 10.0, 0.0, 0.0, 0.0, 0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                List.of(AbilityDatabase.FIREBALL)
        ),
        List.of(
                new EquipmentFactory.Weighted<>(new Ancient(), 0.25)
        ),
        0.35,
        List.of(
                new EquipmentFactory.Weighted<>(new OfTheNorth(), 0.5)
        ),
        0.55 
        );

        // Register other prototypes (no pools shown; add pools as needed)
        EquipmentFactory.INSTANCE.registerPrototype(
        "Dagger",
        () -> new Dagger(
                "Dagger",
                100,
                new Attributes(0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                List.of(),
                25.0
        ),
        null, 0.0, null, 0.0
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "LargeShield",
        () -> new LargeShield(
                "Large Shield",
                200,
                new Attributes(0.0,0.0,0.0,5.0,2.0,0.0,0.0),
                new Resistances(2,2,2,0,0,0,0,0,0,0,0,0),
                List.of(AbilityDatabase.SHIELD_BASH),
                3.0
        ),
        // example: shields get prefixes rarely, but can get suffixes
        List.of(new EquipmentFactory.Weighted<>(new Ancient(), 1.0)),
        0.10,
        List.of(new EquipmentFactory.Weighted<>(new OfTheNorth(), 1.0)),
        0.25
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "Longsword",
        () -> new Longsword(
                "Longsword",
                500,
                new Attributes(50.0,0.0,0.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10),
                List.of(AbilityDatabase.SLASH),
                77.0
        ),
        null, 0.0, null, 0.0
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "Longbow",
        () -> new Longbow(
                "Longbow",
                800,
                new Attributes(0.0,60.0,0.0,0.0,0.0,0.0,5.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0),
                List.of(AbilityDatabase.POISON_DART)
        ),
        null, 0.0, null, 0.0
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "HeavyTorso",
        () -> new HeavyTorso(
                "Heavy Torso",
                1000,
                new Attributes(20.0,20.0,20.0,20.0,20.0,20.0,0.0),
                new Resistances(10,10,10,10,10,10,10,10,10,10,10,10)
        ),
        null, 0.0, null, 0.0
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "LightTorso",
        () -> new LightTorso(
                "Light Torso",
                200,
                new Attributes(2.0,2.0,2.0,2.0,2.0,2.0,2.0),
                new Resistances(1,1,1,1,1,1,1,1,1,1,1,1)
        ),
        null, 0.0, null, 0.0
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "Ring",
        () -> new Ring(
                "Ring",
                2000,
                new Attributes(0.0,0.0,0.0,0.0,0.0,0.0,100.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
        ),
        null, 0.0, null, 0.0
        );

        EquipmentFactory.INSTANCE.registerPrototype(
        "Neck",
        () -> new Neck(
                "Neck",
                2000,
                new Attributes(100.0,0.0,0.0,0.0,0.0,0.0,0.0),
                new Resistances(0,0,0,0,0,0,0,0,0,0,0,0)
        ),
        null, 0.0, null, 0.0
        );

        // optionally create some sample items for quick testing
        EQUIPMENT_LIST.add(EquipmentFactory.INSTANCE.createByKey("Staff", null, null));
        EQUIPMENT_LIST.add(EquipmentFactory.INSTANCE.createRandomByKey("Dagger", null, null));
    }

    public static List<Equipment> getEquipmentList() {
            return EQUIPMENT_LIST;
    }
}
