package items.equipment.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Torso extends Equipment {
    public Torso(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.TORSO, itemType, attributes, resistances);
    }

}
