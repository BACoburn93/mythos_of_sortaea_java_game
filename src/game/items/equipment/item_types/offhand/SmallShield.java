package items.equipment.item_types.offhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ShieldTypes;

public class SmallShield extends Offhand {

    public SmallShield(String name, double value, Attributes attributes, Resistances resistances) {
        super(name, value, ShieldTypes.SMALL, attributes, resistances);
    }

    public SmallShield(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, value, ShieldTypes.SMALL, attributes, resistances, abilities);
    }

    public SmallShield(String name, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(name, value, ShieldTypes.SMALL, attributes, resistances, abilities, damage);
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
    }
}