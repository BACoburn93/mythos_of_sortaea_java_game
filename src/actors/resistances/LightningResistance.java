package actors.resistances;

import abilities.damages.DamageTypes;

public class LightningResistance extends Resistance {
    public LightningResistance(int value) {
        super(DamageTypes.LIGHTNING, value);
    }
}