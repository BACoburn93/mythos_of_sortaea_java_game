package items.equipment.item_types.legs;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Legs extends Equipment {
    public Legs(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.LEGS, itemType, attributes, resistances, new ArrayList<>());
    }

}
