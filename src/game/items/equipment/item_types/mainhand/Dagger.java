package items.equipment.item_types.mainhand;

import java.util.List;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.WeaponTypes;

public class Dagger extends Mainhand {
    public Dagger(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name,
            goldValue,
            WeaponTypes.DAGGER,
            false,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities)
        );
    }

    public Dagger(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.DAGGER, false, attributes, resistances, List.of(AbilityDatabase.STAB));
    }
}
