package actors.resistances;

import abilities.damages.DamageTypes;

public class VenomResistance extends Resistance {
    public VenomResistance(int value) {
        super(DamageTypes.VENOM, value);
    }
}
