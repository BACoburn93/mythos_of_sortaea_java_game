package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothFeet extends Feet {
    public ClothFeet(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.CLOTH, attributes, resistances);
    }

}
