package items.equipment.item_types.mainhand;

import java.util.List;

import abilities.Ability;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Longbow extends Mainhand{
    public Longbow(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, goldValue, WeaponTypes.LONGBOW, true, attributes, resistances, abilities);
    }

}
