package items.equipment.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;

public class Mainhand extends Equipment {
    private boolean twoHanded;
    public Mainhand(String name, int goldValue, int quantity, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.MAINHAND,
                itemType,
                attributes,
                resistances
        );
        this.twoHanded = twoHanded;
    }

    public Mainhand(String name, int goldValue, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.MAINHAND,
                itemType,
                attributes,
                resistances
        );
        this.twoHanded = twoHanded;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }
}
