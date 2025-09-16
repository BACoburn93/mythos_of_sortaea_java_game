package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Blind;
import status_conditions.Confused;
import status_conditions.StatusCondition;

public class SpiritualLightDamage extends Damage {
    public SpiritualLightDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.LIGHT,
                new StatusCondition[] {
                        new Blind(10, 1, 1),
                        new Confused(10, 1, 1)
                }
        );
    }

    public SpiritualLightDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.LIGHT,
                statusConditions
        );
    }
}
