package actors.resistances;

import abilities.damages.DamageTypes;

public class WaterResistance extends Resistance {
    public WaterResistance(int value) {
        super(DamageTypes.WATER, value);
    }
}
