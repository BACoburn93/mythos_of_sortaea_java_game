package items.equipment.item_types.feet;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumFeet extends Feet {
    public MediumFeet(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public static class Builder extends FeetBuilder {
        public Builder() {
            this.name = "Chainmail Sabatons";
            this.tier = 1;
            this.value = 100.0;
            this.armorType = ArmorTypes.MEDIUM;
        }

        @Override
        protected Builder self() { return this; }

        public MediumFeet build() {
            return new MediumFeet(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
