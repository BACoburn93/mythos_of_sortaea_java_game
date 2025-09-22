package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Bleed;
import status_conditions.Slow;
import status_conditions.StatusCondition;
import status_conditions.Weak;

public class MagicalPiercingDamage extends Damage {
    public MagicalPiercingDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.PIERCING,
                new StatusCondition[] {
                        new Bleed(),
                        new Slow(),
                        new Weak(),
                }
        );
    }

    public MagicalPiercingDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.PIERCING,
                statusConditions
        );
    }
}
