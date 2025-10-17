package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumTorso extends Torso {
    public MediumTorso(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

}
