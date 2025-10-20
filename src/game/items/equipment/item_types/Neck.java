package items.equipment.item_types;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;

public class Neck extends Equipment {
    public Neck(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.ACCESSORY, AccessoryTypes.NECK, attributes, resistances, new ArrayList<>());
    }

    public static class NeckBuilder extends Builder<NeckBuilder> {
        public NeckBuilder() {
            // defaults
            this.name = "Simple Necklace";
            this.tier = 0;
            this.value = 10.0;
        }

        @Override
        protected NeckBuilder self() { return this; }

        public Neck build() {
            return new Neck(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
