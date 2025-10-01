package handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import abilities.Ability;
import characters.Character;
import characters.Party;
import items.equipment.Equipment;
import items.equipment.equipment_slots.*;
import items.equipment.item_types.ItemType;
import utils.GameScanner;
import utils.InputHandler;
import utils.SelectionUtils;
import utils.StringUtils;
import characters.jobs.Job;

public class EquipmentHandler {
    private Party party;
    private Character character;

    public EquipmentHandler(Party party) {
        this.party = party;
    }

    public EquipmentHandler(Character character) {
        this.character = character;
    }

    public void handleEquip(GameScanner scanner, Character character) {
        List<Equipment> equipmentList = party.getSharedEquipment();
        Equipment eq = SelectionUtils.selectFromList(
            equipmentList,
            scanner,
            Equipment::getName,
            Equipment::toString,
            "Type the equipment name to use, [L]ist to see party equipment, or [Q]uit: ",
            "l",
            3
        );

        if (eq != null) {
            Job charJob = character.getJobObj();
            Set<ItemType> allowedTypes = charJob.getEquippableItemTypes();

            if (allowedTypes != null) {
                ItemType itemType = eq.getItemType();

                if (itemType != null && !allowedTypes.contains(itemType)) {
                    System.out.println("Your job cannot equip this item type.");
                    return;
                }
            }

            System.out.println("Equipping " + eq.getName());
            boolean equipped = character.equipItem(eq); 
            if (equipped) {
                // Add item abilities if present
                if (eq instanceof items.equipment.item_types.mainhand.Mainhand mainhand && mainhand.getAbilities() != null) {
                    character.addItemAbilities(mainhand.getAbilities());
                }
                // Repeat for Offhand or other equipment types as needed
                equipmentList.remove(eq);
                ui.CombatUIStrings.printCombatActorStats(character);
            } else {
                System.out.println("Could not equip " + eq.getName() + ". Check slot compatibility.");
            }
        }
    }

    public void handleUnequip(GameScanner scanner, Character character) {
        Map<String, EquipmentSlot> slotMap = character.getEquipmentSlots();
        List<String> orderedSlots = new ArrayList<>(slotMap.keySet());
        List<Map.Entry<String, EquipmentSlot>> entries = new ArrayList<>(slotMap.entrySet());

        StringUtils.printOptionsGrid(
            orderedSlots,
            slot -> {
                EquipmentSlot eqSlot = slotMap.get(slot);
                Equipment eq = (eqSlot != null) ? eqSlot.getEquippedItem() : null;
                String eqName = (eq != null && eq.getName() != null) ? eq.getName() : "Empty";
                return slot + ": " + eqName;
            },
            3,
            5
        );

        System.out.println("Type the item name, slot name (e.g., HEAD), or its number to unequip:");
        String input = scanner.nextLine();

        Map.Entry<String, EquipmentSlot> selectedEntry = InputHandler.getItemByInput(
            input,
            entries,
            entry -> {
                EquipmentSlot eqSlot = entry.getValue();
                Equipment eq = (eqSlot != null) ? eqSlot.getEquippedItem() : null;
                String slotName = entry.getKey();
                String itemName = (eq != null && eq.getName() != null) ? eq.getName() : "";
                return slotName + "|" + itemName;
            }
        );

        String slot = (selectedEntry != null) ? selectedEntry.getKey() : null;
        Equipment eq = (slot != null && slotMap.get(slot) != null) ? slotMap.get(slot).getEquippedItem() : null;

        if (slot != null && eq != null) {
            character.unequipItem(slot);

            // Remove item abilities if present (generalized for any Equipment with abilities)
            List<Ability> abilitiesToRemove = null;
            
            if (eq instanceof items.equipment.item_types.mainhand.Mainhand mainhand) {
                abilitiesToRemove = mainhand.getAbilities();
            } 
            // else if (eq instanceof items.equipment.item_types.offhand.Offhand offhand) {
            //     abilitiesToRemove = offhand.getAbilities();
            // }
            // Account for each of the other equipment types as needed

            // TODO - Fix the abilities not being removes when unequipping an item
            if (abilitiesToRemove != null) {
                character.removeItemAbilities(abilitiesToRemove);
            }

            party.getSharedEquipment().add(eq);
            System.out.println("Unequipped " + eq.getName() + " from " + slot);
            ui.CombatUIStrings.printCombatActorStats(character);
        } else {
            System.out.println("No such equipped item found.");
        }
    }

    public <T extends Enum<T>> boolean meetsEquipmentRequirement(T[] requiredTypes, Class<T> typeClass) {
        if (requiredTypes == null || requiredTypes.length == 0) return true;
        return character.getEquipmentSlots().values().stream().anyMatch(e -> {
            if (e == null) return false;

            Object itemTypeObj = e.getItemType();

            if (itemTypeObj != null && typeClass.isInstance(itemTypeObj)) {
                T itemType = typeClass.cast(itemTypeObj);
                for (T req : requiredTypes) {
                    if (itemType.equals(req)) {
                        return true;
                    }
                }
            }
            
            return false;
        });
    }

}
