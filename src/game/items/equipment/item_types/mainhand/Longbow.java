package items.equipment.item_types.mainhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.WeaponTypes;

public class Longbow extends Mainhand {
    public Longbow(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(
            name, 
            value, 
            WeaponTypes.LONGBOW, 
            true, 
            attributes, 
            resistances, 
            combineWithAbilities(AbilityDatabase.SHOOT, abilities),
            10.0
        );
    }

    public Longbow(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, WeaponTypes.LONGBOW, true, attributes, resistances, List.of(AbilityDatabase.SHOOT), 10.0);
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalPiercingDamage(min, max);
    }
}
