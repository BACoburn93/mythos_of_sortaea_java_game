package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Bleed;
import status_conditions.Dry;
import status_conditions.StatusCondition;

public class MagicalWindDamage extends Damage {
    public MagicalWindDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.WIND,
                new StatusCondition[] {
                        new Bleed(),
                        new Dry(),
                }
        );
    }

    public MagicalWindDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.WIND,
                statusConditions
        );
    }
}
