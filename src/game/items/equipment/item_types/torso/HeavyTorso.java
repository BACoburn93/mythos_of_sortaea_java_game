package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class HeavyTorso extends Torso {
    public HeavyTorso(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.HEAVY, attributes, resistances);
    }

    public static class Builder extends TorsoBuilder {
        public Builder() {
            this.name = "Plate Armor";
            this.tier = 3;
            this.value = 1000.0;
            this.armorType = ArmorTypes.HEAVY;
        }

        @Override
        protected Builder self() { return this; }

        public HeavyTorso build() {
            return new HeavyTorso(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
