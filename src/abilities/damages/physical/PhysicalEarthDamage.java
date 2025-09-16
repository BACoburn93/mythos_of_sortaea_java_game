package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Poison;
import status_conditions.StatusCondition;
import status_conditions.Weak;

public class PhysicalEarthDamage extends Damage {
    public PhysicalEarthDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.EARTH,
                new StatusCondition[] {
                        new Poison(10, 1, 1),
                        new Weak(10, 1, 1),
                }
        );
    }

    public PhysicalEarthDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.EARTH,
                statusConditions
        );
    }
}
