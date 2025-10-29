package characters.managers;

import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.AccessoryTypes;
import items.equipment.equipment_slots.EquipmentSlot;
import characters.Character;

import java.util.Map;


//   Responsible for equipping/unequipping items for a Character.
//   - Uses the Character's equipmentSlots map.
//   - Applies/removes item attribute/resistance changes and item abilities via Character API.
 
//   Notes:
//   - This implementation prefers left ring when auto-assigning a ring.
//   - It intentionally avoids user prompting; callers that need interactive choice should
//     perform that and call equipToSlot(item, "Left Ring"/"Right Ring").

public class EquipmentManager {
    private final Character owner;
    private final Map<String, EquipmentSlot> slots;

    // Display-name mapping similar to Character.equipmentTypeToDisplayName
    private final Map<Enum<?>, String> equipmentTypeToDisplayName = Map.of(
        EquipmentTypes.HEAD, "Head",
        EquipmentTypes.MAINHAND, "Mainhand",
        EquipmentTypes.OFFHAND, "Offhand",
        EquipmentTypes.TORSO, "Torso",
        EquipmentTypes.BACK, "Back",
        EquipmentTypes.LEGS, "Legs",
        EquipmentTypes.WAIST, "Waist",
        EquipmentTypes.FEET, "Feet",
        AccessoryTypes.NECK, "Neck",
        AccessoryTypes.RING, "Ring"
    );

    public EquipmentManager(Character owner, Map<String, EquipmentSlot> slots) {
        if (owner == null) throw new IllegalArgumentException("owner is null");
        if (slots == null) throw new IllegalArgumentException("slots is null");
        this.owner = owner;
        this.slots = slots;
    }

    // Equip the given item into the appropriate slot based on its type.
    // For rings, prefers left then right then replaces left.
    public boolean equip(Equipment item) {
        if (item == null) return false;

        String slotKey;
        if (item.getEquipmentType() == EquipmentTypes.ACCESSORY) {
            // item.getItemType() is an enum like AccessoryTypes
            slotKey = equipmentTypeToDisplayName.get(item.getItemType());
        } else {
            slotKey = equipmentTypeToDisplayName.get(item.getEquipmentType());
        }

        if (slotKey == null) return false;

        // special-case Ring auto-choice
        if ("Ring".equals(slotKey)) {
            // prefer left then right then replace left
            String left = "Left Ring";
            String right = "Right Ring";
            EquipmentSlot leftSlot = slots.get(left);
            EquipmentSlot rightSlot = slots.get(right);
            if (leftSlot != null && leftSlot.canEquip(item) && leftSlot.getEquippedItem() == null) {
                return equipToSlot(item, left);
            } else if (rightSlot != null && rightSlot.canEquip(item) && rightSlot.getEquippedItem() == null) {
                return equipToSlot(item, right);
            } else {
                // both occupied or not equippable: try to replace left if possible
                if (leftSlot != null && leftSlot.canEquip(item)) {
                    unequip(left);
                    return equipToSlot(item, left);
                } else if (rightSlot != null && rightSlot.canEquip(item)) {
                    unequip(right);
                    return equipToSlot(item, right);
                } else {
                    return false;
                }
            }
        }

        // normal slot
        EquipmentSlot slot = slots.get(slotKey);
        if (slot != null && slot.canEquip(item)) {
            Equipment currentlyEquipped = slot.getEquippedItem();
            if (currentlyEquipped != null) {
                // remove existing
                unequip(slotKey);
            }
            return equipToSlot(item, slotKey);
        }

        return false;
    }

    // Equip the given item into the specified slot (by display name).
    public boolean equipToSlot(Equipment item, String slotKey) {
        if (item == null || slotKey == null) return false;
        EquipmentSlot slot = slots.get(slotKey);
        if (slot == null || !slot.canEquip(item)) return false;

        slot.setEquippedItem(item);

        // apply item attributes/resistances directly to owner
        if (item.getAttributes() != null) owner.getAttributes().add(item.getAttributes());
        if (item.getResistances() != null) owner.getResistances().add(item.getResistances());

        // apply item abilities if present
        if (item.getAbilities() != null && !item.getAbilities().isEmpty()) {
            owner.addItemAbilities(item.getAbilities());
        }

        // Ensure sets are recomputed and log
        System.out.println("[EquipmentManager] calling SetManager.reconcile for owner=" + owner.getName());
        SetManager.getInstance().reconcile(owner);

        return true;
    }


    // Unequip item from specified slot (by display name).
    public void unequip(String slotKey) {
        if (slotKey == null) return;
        EquipmentSlot slot = slots.get(slotKey);
        if (slot == null) return;
        Equipment item = slot.getEquippedItem();
        if (item == null) return;

        // remove attributes/resistances and abilities
        if (item.getAttributes() != null) owner.getAttributes().subtract(item.getAttributes());
        if (item.getResistances() != null) owner.getResistances().subtract(item.getResistances());
        if (item.getAbilities() != null && !item.getAbilities().isEmpty()) {
            owner.removeItemAbilities(item.getAbilities());
        }

        slot.unequip();
    }

    // Unequip item by name (case-insensitive); returns true if found and unequipped.
    public boolean unequipByName(String itemName) {
        if (itemName == null) return false;
        for (Map.Entry<String, EquipmentSlot> e : slots.entrySet()) {
            Equipment eq = e.getValue().getEquippedItem();
            if (eq != null && itemName.equalsIgnoreCase(eq.getName())) {
                unequip(e.getKey());
                return true;
            }
        }
        return false;
    }

    // Get the equipment slots map.
    public Map<String, EquipmentSlot> getSlots() {
        return slots;
    }

    // Check if a weapon is equipped in the Mainhand slot.
    public boolean hasWeaponEquipped() {
        EquipmentSlot mainhand = slots.get("Mainhand");
        return mainhand != null && mainhand.getEquippedItem() != null;
    }
}