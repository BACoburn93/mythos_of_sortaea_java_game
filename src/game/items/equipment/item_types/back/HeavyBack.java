package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyBack extends Back {
    public HeavyBack(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

    public static class Builder extends BackBuilder {
        public Builder() {
            this.name = "Heavy Cloak";
            this.tier = 0;
            this.value = 4.0;
            this.armorType = ArmorTypes.HEAVY;
        }

        @Override
        protected Builder self() { return this; }

        public HeavyBack build() {
            return new HeavyBack(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
