package items.equipment.offhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ShieldTypes;

public class LargeShield extends Offhand {
    public LargeShield(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, ShieldTypes.LARGE, attributes, resistances);
    }

}
