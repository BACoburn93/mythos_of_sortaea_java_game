package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Confused;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Stun;


public class SpiritualBludgeoningDamage extends Damage {
    public SpiritualBludgeoningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.BLUDGEONING,
                new StatusCondition[] {
                        new Confused(10, 1, 1),
                        new Stun(10, 1, 1),
                }
        );
    }

    public SpiritualBludgeoningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.BLUDGEONING,
                statusConditions
        );
    }
}

