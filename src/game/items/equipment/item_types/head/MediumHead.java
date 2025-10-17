package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumHead extends Head {
    public MediumHead(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

}
