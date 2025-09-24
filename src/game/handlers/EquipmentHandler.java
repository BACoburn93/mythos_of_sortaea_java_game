package handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import characters.Character;
import characters.Party;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import utils.GameScanner;
import utils.InputHandler;
import utils.SelectionUtils;

public class EquipmentHandler {
    private Party party;

    public EquipmentHandler(Party party) {
        this.party = party;
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
            System.out.println("Equipping " + eq.getName());
            character.equipItem(eq);
            equipmentList.remove(eq);
            ui.CombatUIStrings.printCombatActorStats(character);
        }
    }

    public void handleUnequip(GameScanner scanner, Character character) {
        Map<EquipmentTypes, Equipment> slots = character.getEquipmentSlots();
        List<EquipmentTypes> orderedSlots = new ArrayList<>(slots.keySet());
        List<Map.Entry<EquipmentTypes, Equipment>> entries = new ArrayList<>(slots.entrySet());

        orderedSlots.remove(EquipmentTypes.HEAD);
        orderedSlots.add(0, EquipmentTypes.HEAD);

        for (int i = 0; i < orderedSlots.size(); i++) {
            EquipmentTypes slot = orderedSlots.get(i);
            Equipment eq = slots.get(slot);
            String eqName = (eq != null) ? eq.getName() : "Empty";
            System.out.printf("%d. %-8s: %s%n", i + 1, slot, eqName);
        }
        System.out.println("Type the item name, slot name (e.g., HEAD), or its number to unequip:");
        String input = scanner.nextLine();

        Map.Entry<EquipmentTypes, Equipment> selectedEntry = InputHandler.getItemByInput(
            input,
            entries,
            entry -> {
                Equipment eq = entry.getValue();
                String slotName = entry.getKey().toString();
                String itemName = (eq != null && eq.getName() != null) ? eq.getName() : "";
                return slotName + "|" + itemName;
            }
        );

        EquipmentTypes slot = (selectedEntry != null) ? selectedEntry.getKey() : null;
        Equipment eq = (slot != null) ? slots.get(slot) : null;

        if (slot != null && eq != null) {
            character.unequipItem(slot);
            party.getSharedEquipment().add(eq);
            System.out.println("Unequipped " + eq.getName() + " from " + slot);
            ui.CombatUIStrings.printCombatActorStats(character);
        } else {
            System.out.println("No such equipped item found.");
        }
    }

}
