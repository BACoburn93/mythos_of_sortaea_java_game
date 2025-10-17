package items.equipment.item_types.back;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Back extends Equipment {
    public Back(String name, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, value, EquipmentTypes.BACK, itemType, attributes, resistances, new ArrayList<>());
    }
}
