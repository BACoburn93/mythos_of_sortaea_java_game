package items.equipment.item_types.mainhand;

import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.enums.WeaponTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.database.AbilityDatabase; 

public class Staff extends Mainhand {
    private static final double DEFAULT_DAMAGE = 5.0;

    // central constructor
    public Staff(String name, double value, boolean isTwoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(
            name, 
            value, 
            WeaponTypes.STAFF, 
            isTwoHanded, 
            attributes, 
            resistances, 
            Equipment.combineWithAbilities(AbilityDatabase.MAGIC_DART, abilities),
            damage
        );
    }

    // convenience overloads delegate to the central constructor
    public Staff(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        this(name, value, false, attributes, resistances, abilities, damage);
    }

    public Staff(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        this(name, value, false, attributes, resistances, abilities, DEFAULT_DAMAGE);
    }

    public Staff(String name, double value, Attributes attributes, Resistances resistances) {
        this(name, value, false, attributes, resistances, new ArrayList<>(List.of(AbilityDatabase.MAGIC_DART)), DEFAULT_DAMAGE);
    }

    public Staff(String name, double value) {
        this(name, value, null, null, new ArrayList<>(List.of(AbilityDatabase.MAGIC_DART)), DEFAULT_DAMAGE);
    }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.KNOWLEDGE; 
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
    }
}
