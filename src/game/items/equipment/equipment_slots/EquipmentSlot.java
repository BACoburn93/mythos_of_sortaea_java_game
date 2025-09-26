package items.equipment.equipment_slots;

import items.equipment.Equipment;

import characters.Party;

public abstract class EquipmentSlot {
    private Equipment equippedItem;

    public abstract boolean canEquip(Equipment equipment);
    public abstract String getName();

    public Equipment getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Equipment equipment) {
        this.equippedItem = equipment;
    }

    public boolean isEmpty() {
        return equippedItem == null;
    }

    public void equip(Equipment equipment, Party party) {
        if (canEquip(equipment)) {
            if (equippedItem != null) {
                unequip();
            }
            this.equippedItem = equipment;
        } else {
            throw new IllegalArgumentException("Cannot equip item of type " + equipment.getEquipmentType() + " in " + getName());
        }
    }

    public void unequip() {
        if (equippedItem != null) {

            // Clear the slot
            this.equippedItem = null;
        }
    }
    
    public Object getItemType() {
        return (equippedItem != null) ? equippedItem.getItemType() : null;
    }
}
