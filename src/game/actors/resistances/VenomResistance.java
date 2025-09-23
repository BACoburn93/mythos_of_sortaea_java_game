package actors.resistances;

import abilities.damages.DamageTypes;

public class VenomResistance extends Resistance {
    public VenomResistance(double value) {
        super(DamageTypes.VENOM, value);
    }
}
