package actors.resistances;

import abilities.damages.DamageTypes;

public class BludgeoningResistance extends Resistance {
    public BludgeoningResistance(int value) {
        super(DamageTypes.BLUDGEONING, value);
    }
}
