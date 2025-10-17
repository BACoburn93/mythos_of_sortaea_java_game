package items.equipment.item_types;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;

public class Neck extends Equipment {
    public Neck(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.NECK, AccessoryTypes.NECK, attributes, resistances, new ArrayList<>());
    }

}
