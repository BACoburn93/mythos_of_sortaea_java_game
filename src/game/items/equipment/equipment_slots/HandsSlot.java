package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class HandsSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.HANDS;
    }

    @Override
    public String getName() {
        return "Hands Slot";
    }
}
