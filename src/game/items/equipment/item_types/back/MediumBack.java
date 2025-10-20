package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumBack extends Back {
    public MediumBack(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public static class Builder extends BackBuilder {
        public Builder() {
            this.name = "Medium Cloak";
            this.tier = 1;
            this.value = 4.0;
            this.armorType = ArmorTypes.MEDIUM;
        }

        @Override
        protected Builder self() { return this; }

        public MediumBack build() {
            return new MediumBack(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
