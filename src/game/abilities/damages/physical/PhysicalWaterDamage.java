package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;
import status_conditions.Wet;

public class PhysicalWaterDamage extends Damage {
    public PhysicalWaterDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.WATER,
                new StatusCondition[] {
                        new Wet(),
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
