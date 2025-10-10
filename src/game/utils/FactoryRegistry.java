package utils;

import items.equipment.EquipmentFactory;

public final class FactoryRegistry {
    private static EquipmentFactory equipmentFactory = new EquipmentFactory();
    private FactoryRegistry() {}

    public static EquipmentFactory getEquipmentFactory() {
        return equipmentFactory;
    }

    public static void setEquipmentFactory(EquipmentFactory factory) {
        equipmentFactory = (factory == null) ? new EquipmentFactory() : factory;
    }
}
