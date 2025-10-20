package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;

public class NeckSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.ACCESSORY && equipment.getItemType() == AccessoryTypes.NECK;
    }

    @Override
    public String getName() {
        return "Neck Slot";
    }
}
