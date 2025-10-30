package characters.managers;

import characters.Character;
import items.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public final class InventoryManager {
    private static final InventoryManager INSTANCE = new InventoryManager();
    private InventoryManager() {}
    public static InventoryManager getInstance() { return INSTANCE; }

    public List<Consumable> listConsumables(Character c) {
        List<Consumable> out = new ArrayList<>();
        if (c == null) return out;
        Consumable[] arr = c.getItems();
        if (arr == null) return out;
        for (Consumable it : arr) if (it != null && it.getQuantity() > 0) out.add(it);
        return out;
    }

    public int getQuantity(Character c, String name) {
        if (c == null || name == null) return 0;
        for (Consumable it : c.getItems()) {
            if (it != null && name.equalsIgnoreCase(it.getName())) return it.getQuantity();
        }
        return 0;
    }

    public boolean useConsumable(Character c, String name) {
        if (c == null || name == null) return false;
        for (Consumable it : c.getItems()) {
            if (it != null && name.equalsIgnoreCase(it.getName()) && it.getQuantity() > 0) {
                if (c.getActionPoints() < 1) return false;
                c.setActionPoints(c.getActionPoints() - 1);
                it.useConsumable(c, it);
                return true;
            }
        }
        return false;
    }

    public boolean addConsumable(Character c, Consumable template, int qty) {
        if (c == null || template == null || qty <= 0) return false;
        Consumable[] arr = c.getItems();
        // Try to stack
        for (int i = 0; i < arr.length; i++) {
            Consumable it = arr[i];
            if (it != null && it.getName().equalsIgnoreCase(template.getName())) {
                it.setQuantity(it.getQuantity() + qty);
                return true;
            }
        }
        // Try to place in empty slot - need to make a copy method in Consumable
        // for (int i = 0; i < arr.length; i++) {
        //     if (arr[i] == null || arr[i].getQuantity() == 0) {
        //         Consumable copy = template.copy(); 
        //         copy.setQuantity(qty);
        //         arr[i] = copy;
        //         return true;
        //     }
        // }
        return false; // no space
    }

    public Optional<Consumable> findConsumable(Character c, String name) {
        if (c == null || name == null) return Optional.empty();
        for (Consumable it : c.getItems()) {
            if (it != null && name.equalsIgnoreCase(it.getName())) return Optional.of(it);
        }
        return Optional.empty();
    }
}
