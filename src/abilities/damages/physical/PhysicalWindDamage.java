package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Bleed;
import abilities.status_conditions.Dry;
import abilities.status_conditions.StatusCondition;

public class PhysicalWindDamage extends Damage {
    public PhysicalWindDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.WIND,
                new StatusCondition[] {
                        new Bleed(10, 100, 1),
                        new Dry(10, 100, 1),
                }
        );
    }

    public PhysicalWindDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.WIND,
                statusConditions
        );
    }
}
