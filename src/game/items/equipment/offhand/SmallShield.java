package items.equipment.offhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ShieldTypes;

public class SmallShield extends Offhand {
    public SmallShield(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ShieldTypes.SMALL, attributes, resistances);
    }

}
