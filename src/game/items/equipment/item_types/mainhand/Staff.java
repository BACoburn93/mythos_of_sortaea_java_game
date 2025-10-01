package items.equipment.item_types.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;
import java.util.List;
import abilities.Ability; 

public class Staff extends Mainhand{
    public Staff(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, goldValue, WeaponTypes.STAFF, false, attributes, resistances, abilities);
    }

}
