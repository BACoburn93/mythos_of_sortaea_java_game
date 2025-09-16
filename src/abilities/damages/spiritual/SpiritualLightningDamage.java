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
                        new Confused(),
                        new Paralyze(),
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