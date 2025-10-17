package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothLegs extends Legs {
    public ClothLegs(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.CLOTH, attributes, resistances);
    }

}
