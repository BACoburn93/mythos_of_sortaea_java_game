package items.equipment.item_types.mainhand;

import java.util.List;

import abilities.Ability;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Dagger extends Mainhand{
    public Dagger(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, goldValue, WeaponTypes.DAGGER, false, attributes, resistances, abilities);
    }

}
