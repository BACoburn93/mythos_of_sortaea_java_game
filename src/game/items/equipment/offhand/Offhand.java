package items.equipment.offhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;

public class Offhand extends Equipment {
    public Offhand(String name, int goldValue, int quantity, ItemType itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.OFFHAND,
                itemType,
                attributes,
                resistances
        );
    }

    public Offhand(String name, int goldValue, ItemType itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.OFFHAND,
                itemType,
                attributes,
                resistances
        );
    }
}