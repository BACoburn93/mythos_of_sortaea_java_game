package items.equipment.item_types.back;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;

public class Back extends Equipment {
    public Back(String name, int goldValue, ArmorTypes itemType, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                EquipmentTypes.BACK,
                itemType,
                attributes,
                resistances
        );
    }
}
