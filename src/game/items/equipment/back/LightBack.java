package items.equipment.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightBack extends Back {
    public LightBack(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ArmorTypes.LIGHT, attributes, resistances);
    }

    public LightBack(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.LIGHT, attributes, resistances);
    }
}
