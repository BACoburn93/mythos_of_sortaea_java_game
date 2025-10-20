package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyFeet extends Feet {
    public HeavyFeet(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

    public static class Builder extends FeetBuilder {
        public Builder() {
            this.name = "Iron Greaves";
            this.tier = 2;
            this.value = 250.0;
            this.armorType = ArmorTypes.HEAVY;
        }

        @Override
        protected Builder self() { return this; }

        public HeavyFeet build() {
            return new HeavyFeet(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
