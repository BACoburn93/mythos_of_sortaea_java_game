package actors.resistances;

import abilities.damages.DamageTypes;

public class LightningResistance extends Resistance {
    public LightningResistance(double value) {
        super(DamageTypes.LIGHTNING, value);
    }
}