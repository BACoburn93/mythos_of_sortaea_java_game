package actors.resistances;

import abilities.damages.DamageTypes;

public class WindResistance extends Resistance {
    public WindResistance(double value) {
        super(DamageTypes.WIND, value);
    }
}