package items.equipment.equipment_slots;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;

public class LeftRingSlot extends EquipmentSlot {
    @Override
    public boolean canEquip(Equipment equipment) {

        return equipment.getEquipmentType() == EquipmentTypes.ACCESSORY && equipment.getItemType() == AccessoryTypes.RING;
    }

    @Override
    public String getName() {
        return "Left Ring";
    }
}
