package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Confused;
import status_conditions.StatusCondition;
import status_conditions.Stun;

public class MagicalBludgeoningDamage extends Damage {
    public MagicalBludgeoningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.BLUDGEONING,
                new StatusCondition[] {
                        new Confused(),
                        new Stun(),
                }
        );
    }

    public MagicalBludgeoningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.BLUDGEONING,
                statusConditions
        );
    }
}
