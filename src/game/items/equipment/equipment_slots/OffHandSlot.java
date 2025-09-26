package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class OffHandSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.OFFHAND;
    }

    @Override
    public String getName() {
        return "Off Hand Slot";
    }
}
