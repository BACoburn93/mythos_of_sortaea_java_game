package items.equipment.item_types.head;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyHead extends Head {
    public HeavyHead(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

    public static class Builder extends HeadBuilder {
        public Builder() {
            this.name = "Steel Helm";
            this.tier = 2;
            this.value = 300.0;
            this.armorType = ArmorTypes.HEAVY;
        }

        @Override
        protected Builder self() { return this; }

        public HeavyHead build() {
            return new HeavyHead(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
