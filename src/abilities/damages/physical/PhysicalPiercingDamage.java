package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Bleed;
import status_conditions.Slow;
import status_conditions.StatusCondition;
import status_conditions.Weak;

public class PhysicalPiercingDamage extends Damage {
    public PhysicalPiercingDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.PIERCING,
                new StatusCondition[] {
                        new Bleed(10, 1, 2),
                        new Slow(10, 1, 2),
                        new Weak(10, 1, 2),
                }
        );
    }

    public PhysicalPiercingDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.PIERCING,
                statusConditions
        );
    }
}
