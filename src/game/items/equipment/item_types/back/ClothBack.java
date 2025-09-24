package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothBack extends Back {
    public ClothBack(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue,ArmorTypes.CLOTH, attributes, resistances);
    }
    
}
