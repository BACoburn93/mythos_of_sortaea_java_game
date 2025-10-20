package items.equipment.item_types.legs;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothLegs extends Legs {
    public ClothLegs(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

    public static class Builder extends LegsBuilder {
        public Builder() {
            this.name = "Cloth Pants";
            this.tier = 0;
            this.value = 8.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected Builder self() { return this; }

        public ClothLegs build() {
            return new ClothLegs(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
