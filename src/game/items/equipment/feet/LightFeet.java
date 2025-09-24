package items.equipment.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightFeet extends Feet {
    public LightFeet(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.LIGHT, attributes, resistances);
    }
}
