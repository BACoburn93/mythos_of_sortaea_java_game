package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;

public class Ring extends Equipment {
    public Ring(String name, int goldValue, int quantity, Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                quantity,
                EquipmentTypes.RING,
                attributes,
                resistances
        );
    }

    public Ring(String name, int goldValue,  Attributes attributes, Resistances resistances) {
        super(
                name,
                goldValue,
                1,
                EquipmentTypes.RING,
                attributes,
                resistances
        );
    }
}
