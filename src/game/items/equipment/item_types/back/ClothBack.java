package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothBack extends Back {
    public ClothBack(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

    public static class Builder extends BackBuilder {
        public Builder() {
            this.name = "Cloth Cape";
            this.tier = 0;
            this.value = 2.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected Builder self() { return this; }

        public ClothBack build() {
            return new ClothBack(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
