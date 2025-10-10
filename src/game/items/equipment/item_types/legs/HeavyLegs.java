package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class HeavyLegs extends Legs {
    public HeavyLegs(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.HEAVY, attributes, resistances);
    }

}
