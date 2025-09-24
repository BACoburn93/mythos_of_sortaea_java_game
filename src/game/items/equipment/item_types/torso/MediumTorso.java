package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class MediumTorso extends Torso {
    public MediumTorso(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.MEDIUM, attributes, resistances);
    }

}
