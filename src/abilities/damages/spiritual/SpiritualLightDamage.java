package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Blind;
import abilities.status_conditions.Confused;
import abilities.status_conditions.StatusCondition;

public class SpiritualLightDamage extends Damage {
    public SpiritualLightDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.LIGHT,
                new StatusCondition[] {
                        new Blind(10, 5, 1),
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
