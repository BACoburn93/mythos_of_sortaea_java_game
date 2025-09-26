package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class LegsSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.LEGS;
    }

    @Override
    public String getName() {
        return "Legs Slot";
    }
}
