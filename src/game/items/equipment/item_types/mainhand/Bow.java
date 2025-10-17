package items.equipment.item_types.mainhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.enums.WeaponTypes;

public class Bow extends Mainhand {
    private static final double DEFAULT_DAMAGE = 10.0;

    // central constructor 
    public Bow(String name,
                   double value,
                   boolean isTwoHanded,
                   Attributes attributes,
                   Resistances resistances,
                   List<Ability> abilities,
                   double damage) {
        super(
            name,
            value,
            WeaponTypes.BOW,
            true,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.SHOOT, abilities),
            damage
        );
    }

    // convenience overloads delegate to the central constructor
    public Bow(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        this(name, value, true, attributes, resistances, abilities, damage);
    }

    public Bow(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        this(name, value, attributes, resistances, abilities, DEFAULT_DAMAGE);
    }

    public Bow(String name, double value, Attributes attributes, Resistances resistances) {
        this(name, value, attributes, resistances, List.of(AbilityDatabase.SHOOT), DEFAULT_DAMAGE);
    }

    public Bow(String name, double value) {
        this(name, value, null, null, List.of(AbilityDatabase.SHOOT), DEFAULT_DAMAGE);
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalPiercingDamage(min, max);
    }
}
