package items.equipment.item_types.mainhand;

import java.util.List;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Longbow extends Mainhand {
    public Longbow(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name, 
            goldValue, 
            WeaponTypes.LONGBOW, 
            true, 
            attributes, 
            resistances, 
            combineWithAbilities(AbilityDatabase.SHOOT, abilities),
            10.0
        );
    }

    public Longbow(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.LONGBOW, true, attributes, resistances, List.of(AbilityDatabase.SHOOT), 10.0);
    }

}
