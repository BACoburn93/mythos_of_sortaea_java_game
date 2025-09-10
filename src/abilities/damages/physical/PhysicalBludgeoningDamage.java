package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Confused;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Stun;


public class PhysicalBludgeoningDamage extends Damage {
    public PhysicalBludgeoningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.BLUDGEONING,
                new StatusCondition[] {
                        new Confused(10, 100, 1),
                        new Stun(10, 100, 1),
                }
        );
    }

    public PhysicalBludgeoningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.BLUDGEONING,
                statusConditions
        );
    }
}

