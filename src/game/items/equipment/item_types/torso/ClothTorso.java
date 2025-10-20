package items.equipment.item_types.torso;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.enums.ArmorTypes;

public class ClothTorso extends Torso {
    public ClothTorso(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, ArmorTypes.CLOTH, attributes, resistances);
    }

    public static class Builder extends TorsoBuilder {
        public Builder() {
            this.name = "Cloth Robe";
            this.tier = 0;
            this.value = 8.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected Builder self() { return this; }

        public ClothTorso build() {
            return new ClothTorso(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
