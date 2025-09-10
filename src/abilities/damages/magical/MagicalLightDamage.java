package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Blind;
import abilities.status_conditions.Confused;
import abilities.status_conditions.StatusCondition;

public class MagicalLightDamage extends Damage {
    public MagicalLightDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.LIGHT,
                new StatusCondition[] {
                        new Blind(10, 5, 1),
                        new Confused(10, 1, 1)
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
