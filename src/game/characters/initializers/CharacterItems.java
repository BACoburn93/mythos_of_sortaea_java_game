package characters.initializers;

import items.consumables.Consumable;
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
}
