package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class NeckSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.NECK;
    }

    @Override
    public String getName() {
        return "Neck Slot";
    }
}
