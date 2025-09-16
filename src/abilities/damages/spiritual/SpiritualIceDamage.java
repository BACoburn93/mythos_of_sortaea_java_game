package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.*;

public class SpiritualIceDamage extends Damage {
    public SpiritualIceDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.ICE,
                new StatusCondition[] {
                        new Freeze(),
                        new Sick(),
                        new Slow(),
                        new Weak(),
                }
        );
    }

    public SpiritualIceDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.ICE,
                statusConditions
        );
    }
}
