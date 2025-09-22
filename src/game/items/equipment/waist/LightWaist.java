package items.equipment.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightWaist extends Waist {
    public LightWaist(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ArmorTypes.LIGHT, attributes, resistances);
    }

    public LightWaist(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.LIGHT, attributes, resistances);
    }
}
