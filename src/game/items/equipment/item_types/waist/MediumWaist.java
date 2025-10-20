package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumWaist extends Waist {
    public MediumWaist(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public static class Builder extends WaistBuilder {
        public Builder() {
            this.name = "Chainmail Belt";
            this.tier = 2;
            this.value = 300.0;
            this.armorType = ArmorTypes.MEDIUM;
        }

        @Override
        protected Builder self() { return this; }

        public MediumWaist build() {
            return new MediumWaist(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
