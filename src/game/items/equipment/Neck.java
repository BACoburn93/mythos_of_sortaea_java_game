package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;

public class Neck extends Equipment {
    public Neck(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.NECK,
                attributes,
                resistances
        );
    }

    public Neck(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.NECK,
                attributes,
                resistances
        );
    }
}
