package items.equipment.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class MediumTorso extends Torso {
    public MediumTorso(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public MediumTorso(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.MEDIUM, attributes, resistances);
    }
}
