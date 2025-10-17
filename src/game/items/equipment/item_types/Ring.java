package items.equipment.item_types;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;

public class Ring extends Equipment {
    public Ring(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.RING, AccessoryTypes.RING, attributes, resistances, new ArrayList<>());
    }

}
