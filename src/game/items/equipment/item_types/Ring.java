package items.equipment.item_types;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class Ring extends Equipment {
    public Ring(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.RING, attributes, resistances);
    }

}
