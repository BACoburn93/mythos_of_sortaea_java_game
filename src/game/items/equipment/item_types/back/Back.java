package items.equipment.item_types.back;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.feet.Feet;

public class Back extends Equipment {
    public Back(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.BACK, itemType, attributes, resistances, new ArrayList<>());
    }

    public static abstract class BackBuilder extends Builder<BackBuilder> {
        protected ArmorTypes armorType;
        
        public BackBuilder() {
            // defaults
            this.name = "Cape";
            this.tier = 0;
            this.value = 1.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected BackBuilder self() { return this; }

        public Back build() {
            return new Back(
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
