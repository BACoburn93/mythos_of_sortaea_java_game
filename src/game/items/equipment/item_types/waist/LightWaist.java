package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightWaist extends Waist {
    public LightWaist(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.LIGHT, attributes, resistances);
    }

}
