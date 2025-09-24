package items.equipment.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class HeavyTorso extends Torso {
    public HeavyTorso(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ArmorTypes.HEAVY, attributes, resistances);
    }

}
