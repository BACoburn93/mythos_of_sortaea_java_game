package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Confused;
import status_conditions.Paralyze;
import status_conditions.StatusCondition;

public class PhysicalLightningDamage extends Damage {
    public PhysicalLightningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHTNING,
                new StatusCondition[] {
                        new Confused(),
                        new Paralyze(),
                }
        );
    }

    public PhysicalLightningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHTNING,
                statusConditions
        );
    }
}
