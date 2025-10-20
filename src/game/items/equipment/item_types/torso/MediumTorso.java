package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class MediumTorso extends Torso {
    public MediumTorso(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.MEDIUM, attributes, resistances);
    }

    public static class Builder extends TorsoBuilder {
        public Builder() {
            this.name = "Chainmail Armor";
            this.tier = 2;
            this.value = 300.0;
            this.armorType = ArmorTypes.MEDIUM;
        }

        @Override
        protected Builder self() { return this; }

        public MediumTorso build() {
            return new MediumTorso(
                name,
                tier,
                value,
                attributes,
                resistances
            );
        }
    }
}
