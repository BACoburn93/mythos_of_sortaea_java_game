package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothHead extends Head {
    public ClothHead(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.CLOTH, attributes, resistances);
    }
}
