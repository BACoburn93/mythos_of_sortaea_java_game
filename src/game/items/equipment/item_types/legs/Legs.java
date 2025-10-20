package items.equipment.item_types.legs;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Legs extends Equipment {
    public Legs(String name, int tier, double value, ArmorTypes armorType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.LEGS, armorType, attributes, resistances, new ArrayList<>());
    }

    public static abstract class LegsBuilder extends Builder<LegsBuilder> {
        protected ArmorTypes armorType;

        public LegsBuilder() {
            // defaults
            this.name = "Pants";
            this.tier = 0;
            this.value = 3.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected LegsBuilder self() { return this; }

        public Legs build() {
            return new Legs(
                name, 
                tier, 
                value, 
                armorType, 
                attributes, 
                resistances
            );
        }
    }

}
