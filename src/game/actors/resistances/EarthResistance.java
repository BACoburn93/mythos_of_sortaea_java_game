package actors.resistances;

import abilities.damages.DamageTypes;

public class EarthResistance extends Resistance {
    public EarthResistance(double value) {
        super(DamageTypes.EARTH, value);
    }
}
