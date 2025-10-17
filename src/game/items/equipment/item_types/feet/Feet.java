package items.equipment.item_types.feet;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Feet extends Equipment {
    public Feet(String name, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, value, EquipmentTypes.FEET, itemType, attributes, resistances, new ArrayList<>());
    }

}
