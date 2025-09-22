package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Confused;
import status_conditions.StatusCondition;
import status_conditions.Stun;


public class SpiritualBludgeoningDamage extends Damage {
    public SpiritualBludgeoningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.BLUDGEONING,
                new StatusCondition[] {
                        new Confused(),
                        new Stun(),
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

