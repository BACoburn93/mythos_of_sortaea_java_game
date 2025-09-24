package items.equipment.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class ClothTorso extends Torso {
    public ClothTorso(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.CLOTH, attributes, resistances);
    }

}
