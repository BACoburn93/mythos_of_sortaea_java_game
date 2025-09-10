package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Burn;
import abilities.status_conditions.Dry;
import abilities.status_conditions.StatusCondition;

public class MagicalFireDamage extends Damage {
    public MagicalFireDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.FIRE,
                new StatusCondition[] {
                        new Burn(10, 5, 1),
                        new Dry(10, 1, 1),
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
