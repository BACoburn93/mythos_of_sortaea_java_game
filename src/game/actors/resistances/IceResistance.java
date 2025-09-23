package actors.resistances;

import abilities.damages.DamageTypes;

public class IceResistance extends Resistance {
    public IceResistance(double value) {
        super(DamageTypes.ICE, value);
    }
}
