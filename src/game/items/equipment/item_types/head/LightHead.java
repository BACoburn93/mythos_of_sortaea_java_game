package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightHead extends Head {
    public LightHead(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }

    public static class Builder extends HeadBuilder {
        public Builder() {
            this.name = "Leather Cap";
            this.tier = 0;
            this.value = 10.0;
            this.armorType = ArmorTypes.LIGHT;
        }

        @Override
        protected Builder self() { return this; }

        public LightHead build() {
            return new LightHead(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
