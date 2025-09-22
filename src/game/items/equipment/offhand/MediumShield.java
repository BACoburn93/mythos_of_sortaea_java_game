package items.equipment.offhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ShieldTypes;

public class MediumShield extends Offhand {
    public MediumShield(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(name, goldValue, quantity, ShieldTypes.MEDIUM, attributes, resistances);
    }

    public MediumShield(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ShieldTypes.MEDIUM, attributes, resistances);
    }
}
