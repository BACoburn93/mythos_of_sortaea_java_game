package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightHead extends Head {
    public LightHead(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.LIGHT, attributes, resistances);
    }

}
