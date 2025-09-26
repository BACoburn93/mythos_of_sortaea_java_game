package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class RightRingSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        
        return equipment.getEquipmentType() == EquipmentTypes.RING;
    }

    @Override
    public String getName() {
        return "Right Ring";
    }
}

