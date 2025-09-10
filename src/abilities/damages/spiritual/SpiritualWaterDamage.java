package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Wet;

public class SpiritualWaterDamage extends Damage {
    public SpiritualWaterDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.WATER,
                new StatusCondition[] {
                        new Wet(10, 1, 1),
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
