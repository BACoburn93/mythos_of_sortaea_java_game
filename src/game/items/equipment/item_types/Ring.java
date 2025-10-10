package items.equipment.item_types;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class Ring extends Equipment {
    public Ring(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, EquipmentTypes.RING, attributes, resistances);
    }

}
