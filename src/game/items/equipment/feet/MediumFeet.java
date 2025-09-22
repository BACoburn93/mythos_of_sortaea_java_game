package items.equipment.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class MediumFeet extends Feet {
    public MediumFeet(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public MediumFeet(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.MEDIUM, attributes, resistances);
    }
}
