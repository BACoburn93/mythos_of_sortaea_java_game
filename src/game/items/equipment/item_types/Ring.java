package items.equipment.item_types;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;

public class Ring extends Equipment {
    public Ring(String name, int tier, double value, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.ACCESSORY, AccessoryTypes.RING, attributes, resistances, new ArrayList<>());
    }

    public static class RingBuilder extends Builder<RingBuilder> {
        public RingBuilder() {
            // defaults
            this.name = "Copper Ring";
            this.tier = 0;
            this.value = 7.0;
        }

        @Override
        protected RingBuilder self() { return this; }

        public Ring build() {
            return new Ring(
                name, 
                tier, 
                value, 
                attributes, 
                resistances
            );
        }
    }
}
