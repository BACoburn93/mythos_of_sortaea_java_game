package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothWaist extends Waist {
    public ClothWaist(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

}
