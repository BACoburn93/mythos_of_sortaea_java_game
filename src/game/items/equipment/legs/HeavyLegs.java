package items.equipment.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class HeavyLegs extends Legs {
    public HeavyLegs(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ArmorTypes.HEAVY, attributes, resistances);
    }

    public HeavyLegs(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.HEAVY, attributes, resistances);
    }
}
