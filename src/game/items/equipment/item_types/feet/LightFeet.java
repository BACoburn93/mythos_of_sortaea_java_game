package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightFeet extends Feet {
    public LightFeet(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.LIGHT, attributes, resistances);
    }
}
