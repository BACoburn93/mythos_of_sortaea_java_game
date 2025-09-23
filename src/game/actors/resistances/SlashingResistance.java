package actors.resistances;

import abilities.damages.DamageTypes;

public class SlashingResistance extends Resistance {
    public SlashingResistance(double value) {
        super(DamageTypes.SLASHING, value);
    }
}