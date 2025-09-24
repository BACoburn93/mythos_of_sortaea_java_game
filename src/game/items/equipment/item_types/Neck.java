package items.equipment.item_types;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class Neck extends Equipment {
    public Neck(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.NECK, attributes, resistances);
    }

}
