package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightWaist extends Waist {
    public LightWaist(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }

    public static class Builder extends WaistBuilder {
        public Builder() {
            this.name = "Leather Belt";
            this.tier = 1;
            this.value = 50.0;
            this.armorType = ArmorTypes.LIGHT;
        }

        @Override
        protected Builder self() { return this; }

        public LightWaist build() {
            return new LightWaist(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
