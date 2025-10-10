package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ArmorTypes;

public class LightTorso extends Torso {
    public LightTorso(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ArmorTypes.LIGHT, attributes, resistances);
    }

}
