package actors.resistances;

import abilities.damages.DamageTypes;

public class IceResistance extends Resistance {
    public IceResistance(int value) {
        super(DamageTypes.ICE, value);
    }
}
