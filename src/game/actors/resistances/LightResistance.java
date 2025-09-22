package actors.resistances;

import abilities.damages.DamageTypes;

public class LightResistance extends Resistance {
    public LightResistance(int value) {
        super(DamageTypes.LIGHT, value);
    }
}
