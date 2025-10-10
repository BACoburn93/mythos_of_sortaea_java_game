package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Legs extends Equipment {
    public Legs(String name, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, value, EquipmentTypes.LEGS, itemType, attributes, resistances, null);
    }

}
