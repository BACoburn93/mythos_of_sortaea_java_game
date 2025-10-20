package items.equipment.item_types.head;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;

public class Head extends Equipment {
    public Head(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.HEAD, itemType, attributes, resistances, new ArrayList<>());
    }

    public static abstract class HeadBuilder extends Builder<HeadBuilder> {
        protected ArmorTypes armorType;
        
        public HeadBuilder() {
            // defaults
            this.name = "Turban";
            this.tier = 0;
            this.value = 2.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected HeadBuilder self() { return this; }

        public Head build() {
            return new Head(
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
