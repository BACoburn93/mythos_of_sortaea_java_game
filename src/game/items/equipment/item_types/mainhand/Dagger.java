package items.equipment.item_types.mainhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.WeaponTypes;

public class Dagger extends Mainhand {

    public Dagger(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(
            name,
            value,
            WeaponTypes.DAGGER,
            false,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            damage
        );
    }

    public Dagger(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name,
            value,
            WeaponTypes.DAGGER,
            false,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            6.0
        );
    }

    public Dagger(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, WeaponTypes.DAGGER, false, attributes, resistances, List.of(AbilityDatabase.STAB), 6.0);
    }

    public Dagger(String name, double value) {
        super(name, value, WeaponTypes.DAGGER, false, null, null, List.of(AbilityDatabase.STAB), 6.0);
    }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.AGILITY;
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalPiercingDamage(min, max);
    }

}
