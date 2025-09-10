package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Wet;

public class PhysicalWaterDamage extends Damage {
    public PhysicalWaterDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.WATER,
                new StatusCondition[] {
                        new Wet(10, 100, 1),
                }
        );
    }

    public PhysicalWaterDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.WATER,
                statusConditions
        );
    }
}
