package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumWaist extends Waist {
    public MediumWaist(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

}
