package items.equipment.offhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;

public class Offhand extends Equipment {
    public Offhand(String name, int goldValue, ItemType itemType, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.OFFHAND, itemType, attributes, resistances);
    }

}