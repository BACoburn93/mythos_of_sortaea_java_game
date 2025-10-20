package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyWaist extends Waist {
    public HeavyWaist(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

    public static class Builder extends WaistBuilder {
        public Builder() {
            this.name = "Plate Belt";
            this.tier = 3;
            this.value = 800.0;
            this.armorType = ArmorTypes.HEAVY;
        }

        @Override
        protected Builder self() { return this; }

        public HeavyWaist build() {
            return new HeavyWaist(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
