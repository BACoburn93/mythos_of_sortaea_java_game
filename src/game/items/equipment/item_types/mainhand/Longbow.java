package items.equipment.item_types.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Longbow extends Mainhand{
    public Longbow(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.LONGBOW, true, attributes, resistances);
    }

}
