package actors.resistances;

import abilities.damages.DamageTypes;

public class DarknessResistance extends Resistance {
    public DarknessResistance(int value) {
        super(DamageTypes.DARKNESS, value);
    }
}
