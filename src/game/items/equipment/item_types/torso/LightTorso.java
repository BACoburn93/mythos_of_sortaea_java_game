package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class LightTorso extends Torso {
    public LightTorso(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.LIGHT, attributes, resistances);
    }

    public static class Builder extends TorsoBuilder {
        public Builder() {
            this.name = "Leather Armor";
            this.tier = 1;
            this.value = 50.0;
            this.armorType = ArmorTypes.LIGHT;
        }

        @Override
        protected Builder self() { return this; }

        public LightTorso build() {
            return new LightTorso(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
