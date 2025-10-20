package items.equipment.item_types.torso;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Torso extends Equipment {
    public Torso(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.TORSO, itemType, attributes, resistances, new ArrayList<>());
    }

    public static abstract class TorsoBuilder extends Builder<TorsoBuilder> {
        protected ArmorTypes armorType;

        public TorsoBuilder() {
            // defaults
            this.name = "Shirt";
            this.tier = 0;
            this.value = 3.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected TorsoBuilder self() { return this; }

        public Torso build() {
            return new Torso(name, tier, value, armorType, attributes, resistances);
        }
    }
}
