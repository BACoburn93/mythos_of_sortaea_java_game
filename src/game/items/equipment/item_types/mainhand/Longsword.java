package items.equipment.item_types.mainhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalSlashingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.WeaponTypes;

public class Longsword extends Mainhand {
    
    public Longsword(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(
            name,
            goldValue,
            WeaponTypes.LONGSWORD,
            false,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            damage
        );
    }

    public Longsword(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name,
            goldValue,
            WeaponTypes.LONGSWORD,
            false,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            6.0
        );
    }

    public Longsword(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.LONGSWORD, false, attributes, resistances, List.of(AbilityDatabase.STAB), 6.0);
    }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.STRENGTH;
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalSlashingDamage(min, max);
    }
}
