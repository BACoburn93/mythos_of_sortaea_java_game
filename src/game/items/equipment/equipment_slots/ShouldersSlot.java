package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class ShouldersSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.SHOULDERS;
    }

    @Override
    public String getName() {
        return "Shoulders Slot";
    }
}
