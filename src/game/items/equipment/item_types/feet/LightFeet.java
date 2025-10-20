package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightFeet extends Feet {
    public LightFeet(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }

    public static class Builder extends FeetBuilder {
        public Builder() {
            this.name = "Leather Boots";
            this.tier = 0;
            this.value = 20.0;
            this.armorType = ArmorTypes.LIGHT;
        }

        @Override
        protected Builder self() { return this; }

        public LightFeet build() {
            return new LightFeet(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
