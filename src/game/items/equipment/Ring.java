package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;

public class Ring extends Equipment {
    public Ring(String name, int goldValue, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.RING, attributes, resistances);
    }

}
