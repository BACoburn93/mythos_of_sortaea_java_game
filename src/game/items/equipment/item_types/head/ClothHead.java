package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothHead extends Head {
    public ClothHead(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

    public static class Builder extends HeadBuilder {
        public Builder() {
            this.name = "Jester Hat";
            this.tier = 0;
            this.value = 5.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected Builder self() { return this; }

        public ClothHead build() {
            return new ClothHead(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
