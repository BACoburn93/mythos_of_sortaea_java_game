package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyHead extends Head {
    public HeavyHead(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

}
