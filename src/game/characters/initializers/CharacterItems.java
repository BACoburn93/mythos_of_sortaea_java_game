package characters.initializers;

import items.consumables.Consumable;
import items.equipment.equipment_slots.EquipmentSlot;
import items.equipment.equipment_slots.FeetSlot;
import items.equipment.equipment_slots.HeadSlot;
import items.equipment.equipment_slots.LeftRingSlot;
import items.equipment.equipment_slots.LegsSlot;
import items.equipment.equipment_slots.MainHandSlot;
import items.equipment.equipment_slots.NeckSlot;
import items.equipment.equipment_slots.OffHandSlot;
import items.equipment.equipment_slots.RightRingSlot;
import items.equipment.equipment_slots.TorsoSlot;

import java.util.LinkedHashMap;

import actors.resources.ResourceTypes;

public class CharacterItems {
    public static Consumable[] getItems() {
        return new Consumable[] {
            new Consumable("Minor Health Potion", 10, 10, ResourceTypes.HEALTH, 20),
            new Consumable("Moderate Health Potion", 30, 10, ResourceTypes.HEALTH, 50),
            new Consumable("Supreme Health Potion", 100, 10, ResourceTypes.HEALTH, 100),
            new Consumable("Full Health Potion", 300, 10, ResourceTypes.HEALTH, 99999),
            new Consumable("Minor Mana Potion", 8, 10, ResourceTypes.MANA, 20),
            new Consumable("Moderate Mana Potion", 25, 10, ResourceTypes.MANA, 50),
            new Consumable("Supreme Mana Potion", 80, 10, ResourceTypes.MANA, 100),
            new Consumable("Full Mana Potion", 250, 10, ResourceTypes.MANA, 99999),
        };
    }

    public static LinkedHashMap<String, EquipmentSlot> getEquipmentSlots() {
        LinkedHashMap<String, EquipmentSlot> equipmentSlots = new LinkedHashMap<>();
                // Initialize equipment slots
            equipmentSlots.put("Head", new HeadSlot());
            equipmentSlots.put("Mainhand", new MainHandSlot());
            equipmentSlots.put("Offhand", new OffHandSlot());
            equipmentSlots.put("Legs", new LegsSlot());
            equipmentSlots.put("Torso", new TorsoSlot());
            equipmentSlots.put("Feet", new FeetSlot());
            equipmentSlots.put("Neck", new NeckSlot());
            equipmentSlots.put("Left Ring", new LeftRingSlot());
            equipmentSlots.put("Right Ring", new RightRingSlot());

        return equipmentSlots;
    }
}
