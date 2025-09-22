package items.consumables;

import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.resources.ResourceTypes;
import characters.Character;
import items.Item;

import java.util.Objects;

public class Consumable extends Item {
    public ResourceTypes effectType; // healthRestore, manaRestore, attrBoost, etc.
    public int effectValue; // How much the effectType affects the type (if applicable)
    public Consumable(String name, int goldValue, int quantity, ResourceTypes effectType, int effectValue) {
        super(name, goldValue, quantity);
        this.effectType = effectType;
        this.effectValue = effectValue;
    }

    public void useConsumable(Character character, Consumable consumable) {
        HealthValues characterHealthValues = character.getHealthValues();
        ManaValues characterManaValues = character.getManaValues();

        character.setActionPoints(consumable.getActionCost());

        if(Objects.equals(consumable.effectType, ResourceTypes.HEALTH)
        && character.getHealth() < characterHealthValues.getMaxValue()) {
            consumable.setQuantity(getQuantity() - 1);
            characterHealthValues.setValue(characterHealthValues.getValue() + consumable.effectValue);
            character.preventOverloadingResourceValues();
            System.out.println(character.getHealthValues());
        }

        if(Objects.equals(consumable.effectType, ResourceTypes.MANA)
        && character.getHealth() < character.getManaValues().getMaxValue()) {
            consumable.setQuantity(getQuantity() - 1);
            character.getManaValues().setValue(characterManaValues.getValue() + consumable.effectValue);
            character.preventOverloadingResourceValues();
            System.out.println(character.getManaValues());
        }
    }

    public ResourceTypes getEffectType() {
        return effectType;
    }

    public String toString() {
        return  getName() + " | " +
                "COST: " + getGoldValue() + " GP" + " | " +
                "QTY: " + getQuantity() + " | " +
                "TYPE: " + effectType + " | " +
                "VALUE: " + (effectValue < 10000 ? effectValue : "ALL");
    }
}
