package items.equipment.item_types.offhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ShieldTypes;

public class LargeShield extends Offhand {

    public LargeShield(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ShieldTypes.LARGE, attributes, resistances);
    }

    public LargeShield(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, tier, value, ShieldTypes.LARGE, attributes, resistances, abilities);
    }

    public LargeShield(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(name, tier, value, ShieldTypes.LARGE, attributes, resistances, abilities, damage);
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
    }

}
