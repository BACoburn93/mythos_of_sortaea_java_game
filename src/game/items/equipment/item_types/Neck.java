package items.equipment.item_types;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class Neck extends Equipment {
    public Neck(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, EquipmentTypes.NECK, attributes, resistances);
    }

}
