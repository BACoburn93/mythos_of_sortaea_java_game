package items.equipment;

import java.util.ArrayList;
import java.util.List;

import utils.FactoryRegistry;
import modifiers.EquipmentModifiers.*;


public class EquipmentDatabase {
    private static final List<Equipment> EQUIPMENT_LIST = new ArrayList<>();
    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        initialized = true;

        // deterministic seed for tests (optional)
        FactoryRegistry.getEquipmentFactory().setSeed(42L);

        // Register prototypes by retrieving suppliers from the Registry
        EquipmentFactory factory = FactoryRegistry.getEquipmentFactory();

        factory.registerPrototype(
                EquipmentKey.LESSER_STAFF.key(),
                EquipmentRegistry.get(EquipmentKey.LESSER_STAFF.key()),
                StaffPools.PREFIX_POOL, 1,
                StaffPools.SUFFIX_POOL, 0.25
        );

        factory.registerPrototype(
                EquipmentKey.STAFF.key(),
                EquipmentRegistry.get(EquipmentKey.STAFF.key()),
                StaffPools.PREFIX_POOL, 0.4,
                StaffPools.SUFFIX_POOL, 0.4
        );

        factory.registerPrototype(
                EquipmentKey.GREAT_STAFF.key(),
                EquipmentRegistry.get(EquipmentKey.GREAT_STAFF.key()),
                StaffPools.PREFIX_POOL, 0.65,
                StaffPools.SUFFIX_POOL, 0.65
        );

        factory.registerPrototype(
                EquipmentKey.DAGGER.key(),
                EquipmentRegistry.get(EquipmentKey.DAGGER.key()),
                DaggerPools.PREFIX_POOL, 0.5,
                DaggerPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.TOWER_SHIELD.key(),
                EquipmentRegistry.get(EquipmentKey.TOWER_SHIELD.key()),
                TowerShieldPools.PREFIX_POOL, 0.5,
                TowerShieldPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.SWORD.key(),
                EquipmentRegistry.get(EquipmentKey.SWORD.key()),
                SwordPools.PREFIX_POOL, 0.5,
                SwordPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.BOW.key(),
                EquipmentRegistry.get(EquipmentKey.BOW.key()),
                BowPools.PREFIX_POOL, 0.5,
                BowPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.PLATE_ARMOR.key(),
                EquipmentRegistry.get(EquipmentKey.PLATE_ARMOR.key()),
                PlateArmorPools.PREFIX_POOL, 0.5,
                PlateArmorPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.LEATHER_ARMOR.key(),
                EquipmentRegistry.get(EquipmentKey.LEATHER_ARMOR.key()),
                LeatherArmorPools.PREFIX_POOL, 0.5,
                LeatherArmorPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.RING.key(),
                EquipmentRegistry.get(EquipmentKey.RING.key()),
                RingPools.PREFIX_POOL, 0.5,
                RingPools.SUFFIX_POOL, 0.5
        );

        factory.registerPrototype(
                EquipmentKey.AMULET.key(),
                EquipmentRegistry.get(EquipmentKey.AMULET.key()),
                AmuletPools.PREFIX_POOL, 0.5,
                AmuletPools.SUFFIX_POOL, 0.5
        );

        // sample items for quick debugging
        EQUIPMENT_LIST.add(factory.createByKey(EquipmentKey.LESSER_STAFF.key(), null, null));
        EQUIPMENT_LIST.add(factory.createRandomByKey(EquipmentKey.DAGGER.key(), null, null));
    }

    public static List<Equipment> getEquipmentList() {
        return EQUIPMENT_LIST;
    }
}
