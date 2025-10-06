package items.equipment.item_types.mainhand;

import java.util.List;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.WeaponTypes;

public class Dagger extends Mainhand {
    private static final String ATTRIBUTE_TO_ATTACK_WITH = "agility";

    public Dagger(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name,
            goldValue,
            WeaponTypes.DAGGER,
            false,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            6.0
        );
    }

    public Dagger(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.DAGGER, false, attributes, resistances, List.of(AbilityDatabase.STAB), 6.0);
    }

    public String getWeaponDamageAttr() {
        return ATTRIBUTE_TO_ATTACK_WITH;
    }
}
