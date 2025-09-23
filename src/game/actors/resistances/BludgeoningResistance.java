package actors.resistances;

import abilities.damages.DamageTypes;

public class BludgeoningResistance extends Resistance {
    public BludgeoningResistance(double value) {
        super(DamageTypes.BLUDGEONING, value);
    }
}
