package items.equipment.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothHead extends Head {
    public ClothHead(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.CLOTH, attributes, resistances);
    }
}
