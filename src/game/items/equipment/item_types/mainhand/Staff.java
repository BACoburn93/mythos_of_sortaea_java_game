package items.equipment.item_types.mainhand;

import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;
import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.database.AbilityDatabase; 

public class Staff extends Mainhand {

    public Staff(String name, int goldValue, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name, 
            goldValue, 
            WeaponTypes.STAFF, 
            false, 
            attributes, 
            resistances, 
            combineWithAbilities(AbilityDatabase.MAGIC_DART, abilities),
            6.0
        );
    }

    public Staff(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, WeaponTypes.STAFF, false, attributes, resistances, List.of(AbilityDatabase.MAGIC_DART), 6.0);
    }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.KNOWLEDGE; 
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
    }
}
