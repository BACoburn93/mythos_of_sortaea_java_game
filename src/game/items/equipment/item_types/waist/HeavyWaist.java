package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyWaist extends Waist {
    public HeavyWaist(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.HEAVY, attributes, resistances);
    }

}
