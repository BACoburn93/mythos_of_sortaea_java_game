package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightFeet extends Feet {
    public LightFeet(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }
}
