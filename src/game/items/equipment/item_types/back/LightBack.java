package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightBack extends Back {
    public LightBack(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }

    public static class Builder extends BackBuilder {
        public Builder() {
            this.name = "Light Cloak";
            this.tier = 0;
            this.value = 3.0;
            this.armorType = ArmorTypes.LIGHT;
        }

        @Override
        protected Builder self() { return this; }

        public LightBack build() {
            return new LightBack(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
