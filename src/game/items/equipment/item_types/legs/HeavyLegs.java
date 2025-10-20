package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyLegs extends Legs {
    public HeavyLegs(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

    public static class Builder extends LegsBuilder {
        public Builder() {
            this.name = "Plate Leggings";
            this.tier = 3;
            this.value = 900.0;
            this.armorType = ArmorTypes.HEAVY;
        }

        @Override
        protected Builder self() { return this; }

        public HeavyLegs build() {
            return new HeavyLegs(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
