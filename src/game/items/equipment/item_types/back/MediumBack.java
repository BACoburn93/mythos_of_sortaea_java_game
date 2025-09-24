package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class MediumBack extends Back {
    public MediumBack(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.MEDIUM, attributes, resistances);
    }

}
