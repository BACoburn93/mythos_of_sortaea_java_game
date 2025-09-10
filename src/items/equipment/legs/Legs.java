package items.equipment.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Legs extends Equipment {
    public Legs(String name, int goldValue, int quantity, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.LEGS,
                itemType,
                attributes,
                resistances
        );
    }

    public Legs(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.LEGS,
                itemType,
                attributes,
                resistances
        );
    }
}
