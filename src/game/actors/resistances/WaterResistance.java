package actors.resistances;

import abilities.damages.DamageTypes;

public class WaterResistance extends Resistance {
    public WaterResistance(double value) {
        super(DamageTypes.WATER, value);
    }
}
