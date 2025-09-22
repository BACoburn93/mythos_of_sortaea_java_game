package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Bleed;
import status_conditions.Dry;
import status_conditions.StatusCondition;

public class PhysicalWindDamage extends Damage {
    public PhysicalWindDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.WIND,
                new StatusCondition[] {
                        new Bleed(),
                        new Dry(),
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
