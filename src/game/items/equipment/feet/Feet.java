package items.equipment.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Feet extends Equipment {
    public Feet(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.FEET, itemType, attributes, resistances);
    }

}
