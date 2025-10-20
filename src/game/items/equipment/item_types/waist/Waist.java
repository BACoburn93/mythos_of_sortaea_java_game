package items.equipment.item_types.waist;

import java.util.ArrayList;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.ArmorTypes;

public class Waist extends Equipment {
    public Waist(String name, int tier, double value, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(name, tier, value, EquipmentTypes.WAIST, itemType, attributes, resistances, new ArrayList<>());
    }

    public static abstract class Builder<T extends Builder<T>> extends Equipment.Builder<T> {
        protected ArmorTypes armorType;

        public T armorType(ArmorTypes it) { this.armorType = it; return self(); }
    }

    public static abstract class WaistBuilder extends Builder<WaistBuilder> {
        public WaistBuilder() {
            // defaults
            this.name = "Belt";
            this.tier = 0;
            this.value = 5.0;
            this.armorType = ArmorTypes.CLOTH;
        }

        @Override
        protected WaistBuilder self() { return this; }

        public Waist build() {
            return new Waist(name, tier, value, armorType, attributes, resistances);
        }
    }
}