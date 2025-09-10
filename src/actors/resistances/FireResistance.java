package actors.resistances;

import abilities.damages.DamageTypes;

public class FireResistance extends Resistance {
    public FireResistance(int value) {
        super(DamageTypes.FIRE, value);
    }
}
