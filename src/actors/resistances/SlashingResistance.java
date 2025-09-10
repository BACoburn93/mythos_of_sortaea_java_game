package actors.resistances;

import abilities.damages.DamageTypes;

public class SlashingResistance extends Resistance {
    public SlashingResistance(int value) {
        super(DamageTypes.SLASHING, value);
    }
}