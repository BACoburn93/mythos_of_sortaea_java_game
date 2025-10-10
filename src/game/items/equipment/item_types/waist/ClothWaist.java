package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothWaist extends Waist {
    public ClothWaist(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.CLOTH, attributes, resistances);
    }

}
