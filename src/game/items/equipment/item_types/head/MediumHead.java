package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumHead extends Head {
    public MediumHead(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public static class Builder extends HeadBuilder {
        public Builder() {
            this.name = "Chainmail Coif";
            this.tier = 1;
            this.value = 150.0;
            this.armorType = ArmorTypes.MEDIUM;
        }

        @Override
        protected Builder self() { return this; }

        public MediumHead build() {
            return new MediumHead(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
