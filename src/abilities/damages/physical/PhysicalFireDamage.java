package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Burn;
import status_conditions.Dry;
import status_conditions.StatusCondition;

public class PhysicalFireDamage extends Damage {
    public PhysicalFireDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.FIRE,
                new StatusCondition[] {
                        new Burn(10, 1, 1),
                        new Dry(10, 1, 1),
                }
        );
    }

    public PhysicalFireDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.FIRE,
                statusConditions
        );
    }
}
