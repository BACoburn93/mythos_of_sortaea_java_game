package actors.resistances;

import abilities.damages.DamageTypes;

public class PiercingResistance extends Resistance {
    public PiercingResistance(double value) {
        super(DamageTypes.PIERCING, value);
    }
}
