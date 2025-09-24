package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Head extends Equipment {
    public Head(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.HEAD, itemType, attributes, resistances);
    }
}
