package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;

public class Neck extends Equipment {
    public Neck(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.NECK, attributes, resistances);
    }

}
