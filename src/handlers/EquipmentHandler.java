package handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import characters.Character;
import characters.Party;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import utils.GameScanner;
import ui.FormattedStrings;

public class EquipmentHandler {
    private Party party;

    public EquipmentHandler(Party party) {
        this.party = party;
    }

    public void handleEquip(GameScanner combatLoop, Character character) {
        List<Equipment> equipmentList = party.getSharedEquipment();

        System.out.print(FormattedStrings.formatQNumberedList(equipmentList));
        System.out.println("Type the equipment name or its number to equip:");
        String chosenEquipment = combatLoop.nextLine();

        Equipment eq = getEquipmentByInput(chosenEquipment, equipmentList);
        if (eq != null) {
            System.out.println("Equipping " + eq.getName());
            character.equipItem(eq);
            System.out.println("Character's Attributes after equipping: " + character.getAttributes());
            System.out.println("Character's Resistances after equipping: " + character.getResistances());
        } else {
            System.out.println("No such equipment found.");
        }
    }

    public void handleUnequip(GameScanner combatLoop, Character character) {
        Map<EquipmentTypes, Equipment> slots = character.getEquipmentSlots();
        List<EquipmentTypes> orderedSlots = new ArrayList<>(slots.keySet());
        orderedSlots.remove(EquipmentTypes.HEAD);
        orderedSlots.add(0, EquipmentTypes.HEAD);

        for (int i = 0; i < orderedSlots.size(); i++) {
            EquipmentTypes slot = orderedSlots.get(i);
            Equipment eq = slots.get(slot);
            String eqName = (eq != null) ? eq.getName() : "Empty";
            System.out.printf("%d. %-8s: %s%n", i + 1, slot, eqName);
        }
        System.out.println("Type the item name, slot name (e.g., HEAD), or its number to unequip:");
        String input = combatLoop.nextLine();

        EquipmentTypes slot = getEquippedSlotByInput(input, slots);
        Equipment eq = (slot != null) ? slots.get(slot) : null;
        if (slot != null && eq != null) {
            character.unequipItem(slot);
            System.out.println("Character's Attributes after unequipping: " + character.getAttributes());
            System.out.println("Character's Resistances after unequipping: " + character.getResistances());
        } else {
            System.out.println("No such equipped item found.");
        }
    }

    private Equipment getEquipmentByInput(String input, List<Equipment> equipmentList) {
        input = input.trim().toLowerCase();

        // Try to parse as number
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < equipmentList.size()) {
                return equipmentList.get(index);
            }
        } catch (NumberFormatException e) {
            // Not a number
        }

        // Try to find by equipment name
        for (Equipment eq : equipmentList) {
            if (eq.getName().equalsIgnoreCase(input)) {
                return eq;
            }
        }

        return null;
    }

    private EquipmentTypes getEquippedSlotByInput(String input, Map<EquipmentTypes, Equipment> slots) {
        input = input.trim().toUpperCase();
        List<EquipmentTypes> slotList = new ArrayList<>(slots.keySet());

        // Try number input
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < slotList.size()) {
                return slotList.get(index);
            }
        } catch (NumberFormatException e) {
            // Not a number
        }

        // Try enum name
        try {
            EquipmentTypes slot = EquipmentTypes.valueOf(input);
            if (slots.containsKey(slot)) {
                return slot;
            }
        } catch (IllegalArgumentException e) {
            // Not a valid enum
        }

        // Try matching equipped item names
        for (Map.Entry<EquipmentTypes, Equipment> entry : slots.entrySet()) {
            Equipment eq = entry.getValue();
            if (eq != null && eq.getName().equalsIgnoreCase(input)) {
                return entry.getKey();
            }
        }

        return null;
    }
}
