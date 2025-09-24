package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Blind;
import status_conditions.Confused;
import status_conditions.StatusCondition;

public class PhysicalLightDamage extends Damage {
    public PhysicalLightDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHT,
                new StatusCondition[] {
                        new Blind(),
                        new Confused()
                }
        );
    }

    public PhysicalLightDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHT,
                statusConditions
        );
    }
}
