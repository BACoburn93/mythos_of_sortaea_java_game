package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;
import status_conditions.Wet;

public class SpiritualWaterDamage extends Damage {
    public SpiritualWaterDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.WATER,
                new StatusCondition[] {
                        new Wet(),
                }
        );
    }

    public SpiritualWaterDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.WATER,
                statusConditions
        );
    }
}
