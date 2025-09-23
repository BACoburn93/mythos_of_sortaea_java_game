package actors.resistances;

import abilities.damages.DamageTypes;

public class FireResistance extends Resistance {
    public FireResistance(double value) {
        super(DamageTypes.FIRE, value);
    }
}
