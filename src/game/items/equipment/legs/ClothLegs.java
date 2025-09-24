package items.equipment.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothLegs extends Legs {
    public ClothLegs(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.CLOTH, attributes, resistances);
    }

}
