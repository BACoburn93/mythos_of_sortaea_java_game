package items.equipment.item_types.head;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Head extends Equipment {
    public Head(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.HEAD, itemType, attributes, resistances, new ArrayList<>());
    }
}
