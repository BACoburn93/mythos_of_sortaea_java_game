package items.equipment.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Torso extends Equipment {
    public Torso(String name, int goldValue, int quantity, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.TORSO,
                itemType,
                attributes,
                resistances
        );
    }

    public Torso(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.TORSO,
                itemType,
                attributes,
                resistances
        );
    }
}
