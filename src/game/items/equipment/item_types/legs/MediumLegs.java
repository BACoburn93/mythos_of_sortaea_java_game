package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumLegs extends Legs {
    public MediumLegs(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public static class Builder extends LegsBuilder {
        public Builder() {
            this.name = "Chain Leggings";
            this.tier = 1;
            this.value = 150.0;
            this.armorType = ArmorTypes.MEDIUM;
        }

        @Override
        protected Builder self() { return this; }

        public MediumLegs build() {
            return new MediumLegs(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
