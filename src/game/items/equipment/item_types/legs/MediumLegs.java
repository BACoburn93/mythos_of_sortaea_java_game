package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumLegs extends Legs {
    public MediumLegs(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

}
