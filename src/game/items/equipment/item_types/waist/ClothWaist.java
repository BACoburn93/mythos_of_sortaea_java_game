package items.equipment.item_types.waist;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothWaist extends Waist {
    public ClothWaist(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

    public static class Builder extends WaistBuilder {
        public Builder() {
            this.name = "Cloth Belt";
            this.tier = 0;
            this.value = 5.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected Builder self() { return this; }

        public ClothWaist build() {
            return new ClothWaist(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
