package items.equipment.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Waist extends Equipment {
    public Waist(String name, int goldValue, int quantity, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.WAIST,
                itemType,
                attributes,
                resistances
        );
    }

    public Waist(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.WAIST,
                itemType,
                attributes,
                resistances
        );
    }
}