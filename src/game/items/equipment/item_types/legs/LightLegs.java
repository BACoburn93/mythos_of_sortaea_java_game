package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightLegs extends Legs {
    public LightLegs(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.LIGHT, attributes, resistances);
    }

}
