package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightLegs extends Legs {
    public LightLegs(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.LIGHT, attributes, resistances);
    }

}
