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
    private static final double DEFAULT_DAMAGE = 8.0;

    // central constructor
    public Longsword(String name, double value, boolean isTwoHanded,
                     Attributes attributes, Resistances resistances,
                     List<Ability> abilities, double damage) {
        super(
            name,
            value,
            WeaponTypes.LONGSWORD,
            isTwoHanded,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            damage
        );
    }

    // convenience overloads delegate to the central constructor
    public Longsword(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        this(name, value, false, attributes, resistances, abilities, damage);
    }

    public Longsword(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        this(name, value, false, attributes, resistances, abilities, DEFAULT_DAMAGE);
    }

    public Longsword(String name, double value, Attributes attributes, Resistances resistances) {
        this(name, value, false, attributes, resistances, List.of(AbilityDatabase.STAB), DEFAULT_DAMAGE);
    }

    public Longsword(String name, double value) {
        this(name, value, false, null, null, List.of(AbilityDatabase.STAB), DEFAULT_DAMAGE);
    }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.STRENGTH;
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalSlashingDamage(min, max);
    }
}
