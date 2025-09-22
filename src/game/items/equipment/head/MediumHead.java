package items.equipment.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class MediumHead extends Head {
    public MediumHead(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public MediumHead(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.MEDIUM, attributes, resistances);
    }
}
