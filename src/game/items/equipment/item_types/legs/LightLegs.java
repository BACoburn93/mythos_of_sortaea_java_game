package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightLegs extends Legs {
    public LightLegs(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }

    public static class Builder extends LegsBuilder {
        public Builder() {
            this.name = "Leather Pants";
            this.tier = 0;
            this.value = 15.0;
            this.armorType = ArmorTypes.LIGHT;
        }

        @Override
        protected Builder self() { return this; }

        public LightLegs build() {
            return new LightLegs(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
