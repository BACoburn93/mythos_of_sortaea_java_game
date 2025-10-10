package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Waist extends Equipment {
    public Waist(String name, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, value, EquipmentTypes.WAIST, itemType, attributes, resistances, null);
    }

}