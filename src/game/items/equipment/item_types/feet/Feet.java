package items.equipment.item_types.feet;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Feet extends Equipment {
    public Feet(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.FEET, itemType, attributes, resistances, new ArrayList<>());
    }

    public static abstract class FeetBuilder extends Builder<FeetBuilder> {
        protected ArmorTypes armorType;
        
        public FeetBuilder() {
            // defaults
            this.name = "Footwraps";
            this.tier = 0;
            this.value = 1.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected FeetBuilder self() { return this; }

        public Feet build() {
            return new Feet(
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
