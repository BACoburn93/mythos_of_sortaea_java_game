package items.equipment.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Dagger extends Mainhand{
    public Dagger(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.DAGGER, false, attributes, resistances);
    }

}
