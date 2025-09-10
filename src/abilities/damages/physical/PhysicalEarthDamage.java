package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Poison;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Weak;

public class PhysicalEarthDamage extends Damage {
    public PhysicalEarthDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.EARTH,
                new StatusCondition[] {
                        new Poison(10, 100, 2),
                        new Weak(10, 100, 2),
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
