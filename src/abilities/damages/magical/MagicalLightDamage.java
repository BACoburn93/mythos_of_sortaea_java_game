package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Blind;
import status_conditions.Confused;
import status_conditions.StatusCondition;

public class MagicalLightDamage extends Damage {
    public MagicalLightDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.LIGHT,
                new StatusCondition[] {
                        new Blind(),
                        new Confused()
                }
        );
    }

    public MagicalLightDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.LIGHT,
                statusConditions
        );
    }
}
