package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class HeavyWaist extends Waist {
    public HeavyWaist(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.HEAVY, attributes, resistances);
    }

}
