package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class HeavyBack extends Back {
    public HeavyBack(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.HEAVY, attributes, resistances);
    }

}
