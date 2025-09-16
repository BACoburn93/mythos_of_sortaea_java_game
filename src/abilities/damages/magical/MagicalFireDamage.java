package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Burn;
import status_conditions.Dry;
import status_conditions.StatusCondition;

public class MagicalFireDamage extends Damage {
    public MagicalFireDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.FIRE,
                new StatusCondition[] {
                        new Burn(),
                        new Dry(),
                }
        );
    }

    public MagicalFireDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.FIRE,
                statusConditions
        );
    }
}
