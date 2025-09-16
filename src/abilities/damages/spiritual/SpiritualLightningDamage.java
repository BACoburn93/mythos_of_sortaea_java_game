package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Confused;
import status_conditions.Paralyze;
import status_conditions.StatusCondition;

public class SpiritualLightningDamage extends Damage {
    public SpiritualLightningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.LIGHTNING,
                new StatusCondition[]{
                        new Confused(10, 1, 1),
                        new Paralyze(10, 1, 1),
                }
        );
    }

    public SpiritualLightningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.LIGHTNING,
                statusConditions
        );
    }
}