package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothFeet extends Feet {
    public ClothFeet(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

    public static class Builder extends FeetBuilder {
        public Builder() {
            this.name = "Cloth Footwraps";
            this.tier = 0;
            this.value = 10.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected Builder self() { return this; }

        public ClothFeet build() {
            return new ClothFeet(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
