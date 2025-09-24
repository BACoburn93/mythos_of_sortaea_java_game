package items.equipment.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Staff extends Mainhand{
    public Staff(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.STAFF, false, attributes, resistances);
    }

}
