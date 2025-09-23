package actors.resistances;

import abilities.damages.DamageTypes;

public class LightResistance extends Resistance {
    public LightResistance(double value) {
        super(DamageTypes.LIGHT, value);
    }
}
