package items.equipment.item_types.offhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ShieldTypes;

public class SmallShield extends Offhand {

    public SmallShield(
        String name, 
        int tier, 
        double value, 
        Attributes attributes, 
        Resistances resistances, 
        List<Ability> abilities, 
        double damage) 
    {
        super(name, tier, value, ShieldTypes.SMALL, attributes, resistances, abilities, damage);
    }

    // public SmallShield(String name, int tier, double value, Attributes attributes, Resistances resistances) {
    //     super(name, tier, value, ShieldTypes.SMALL, attributes, resistances);
    // }

    // public SmallShield(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
    //     super(name, tier, value, ShieldTypes.SMALL, attributes, resistances, abilities);
    // }

    // public SmallShield(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
    //     super(name, tier, value, ShieldTypes.SMALL, attributes, resistances, abilities, damage);
    // }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
    }

    public static class Builder extends Offhand.Builder<Builder> {
        private double damage;

        public Builder() {
            this.itemType = ShieldTypes.SMALL;
            this.equipmentType = EquipmentTypes.OFFHAND;
            this.value = 5.0;
            this.damage = 0.0;
        }

        @Override protected Builder self() { return this; }

        @Override
        public SmallShield build() {
            return new SmallShield( name, tier, value, attributes, resistances, abilities, damage );
        }
    }
}