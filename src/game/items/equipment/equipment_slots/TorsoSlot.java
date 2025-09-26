package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;

public class TorsoSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {
        return equipment.getEquipmentType() == EquipmentTypes.TORSO;
    }

    @Override
    public String getName() {
        return "Torso Slot";
    }
}
