package items.equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.FactoryRegistry;
import items.equipment.modifiers.Prefix;
import items.equipment.modifiers.Suffix;
import items.equipment.modifiers.prefixes.*;
import items.equipment.modifiers.suffixes.*;

/**
 * Bootstraps prototype registrations and prefix/suffix pools.
 * Call EquipmentDatabase.init() early in startup (Main or GameManager.start).
 */
public class EquipmentDatabase {
    private static final List<Equipment> EQUIPMENT_LIST = new ArrayList<>();
    private static boolean initialized = false;

    // reusable test pools
    private static final List<EquipmentFactory.Weighted<Prefix>> STAFF_PREFIX_POOL = Arrays.asList(
            new EquipmentFactory.Weighted<Prefix>(new Ancient(), 0.25),
            new EquipmentFactory.Weighted<Prefix>(new Enflamed(), 0.75)
    );

    private static final List<EquipmentFactory.Weighted<Suffix>> STAFF_SUFFIX_POOL = Arrays.asList(
            new EquipmentFactory.Weighted<Suffix>(new OfTheNorthWind(), 0.5),
            new EquipmentFactory.Weighted<Suffix>(new OfFortitude(), 0.5)
    );

    private static final List<EquipmentFactory.Weighted<Prefix>> SHIELD_PREFIX_POOL = Arrays.asList(
            new EquipmentFactory.Weighted<Prefix>(new Ancient(), 1.0)
    );

    private static final List<EquipmentFactory.Weighted<Suffix>> SHIELD_SUFFIX_POOL = Arrays.asList(
            new EquipmentFactory.Weighted<Suffix>(new OfTheNorthWind(), 1.0)
    );

    public static void init() {
        if (initialized) return;
        initialized = true;

        // deterministic seed for tests (optional)
        FactoryRegistry.getEquipmentFactory().setSeed(42L);

        // Register prototypes by retrieving suppliers from the Registry
        EquipmentFactory factory = FactoryRegistry.getEquipmentFactory();

        factory.registerPrototype(
                "Staff",
                EquipmentRegistry.get("staff"),
                STAFF_PREFIX_POOL,
                0.35,
                STAFF_SUFFIX_POOL,
                0.55
        );

        factory.registerPrototype(
                "Dagger",
                EquipmentRegistry.get("dagger"),
                null, 0.0,
                null, 0.0
        );

        factory.registerPrototype(
                "LargeShield",
                EquipmentRegistry.get("largeshield"),
                SHIELD_PREFIX_POOL,
                0.10,
                SHIELD_SUFFIX_POOL,
                0.25
        );

        factory.registerPrototype(
                "Longsword",
                EquipmentRegistry.get("longsword"),
                null, 0.0,
                null, 0.0
        );

        factory.registerPrototype(
                "Longbow",
                EquipmentRegistry.get("longbow"),
                null, 0.0,
                null, 0.0
        );

        factory.registerPrototype(
                "HeavyTorso",
                EquipmentRegistry.get("heavytorso"),
                null, 0.0,
                null, 0.0
        );

        factory.registerPrototype(
                "LightTorso",
                EquipmentRegistry.get("lighttorso"),
                null, 0.0,
                null, 0.0
        );

        factory.registerPrototype(
                "Ring",
                EquipmentRegistry.get("ring"),
                null, 0.0,
                null, 0.0
        );

        factory.registerPrototype(
                "Neck",
                EquipmentRegistry.get("neck"),
                null, 0.0,
                null, 0.0
        );

        // sample items for quick debugging
        EQUIPMENT_LIST.add(factory.createByKey("Staff", null, null));
        EQUIPMENT_LIST.add(factory.createRandomByKey("Dagger", null, null));
    }

    public static List<Equipment> getEquipmentList() {
        return EQUIPMENT_LIST;
    }
}
