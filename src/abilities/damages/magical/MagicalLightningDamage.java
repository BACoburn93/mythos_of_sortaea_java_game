package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Confused;
import status_conditions.Paralyze;
import status_conditions.StatusCondition;

public class MagicalLightningDamage extends Damage {
    public MagicalLightningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.LIGHTNING,
                new StatusCondition[] {
                        new Confused(10, 1, 1),
                        new Paralyze(10, 1, 1),
                }
        );
    }

    public MagicalLightningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.LIGHTNING,
                statusConditions
        );
    }
}
