package items.equipment.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothWaist extends Waist {
    public ClothWaist(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.CLOTH, attributes, resistances);
    }

}
