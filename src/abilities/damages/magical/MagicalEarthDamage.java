package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Poison;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Weak;

public class MagicalEarthDamage extends Damage {
    public MagicalEarthDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.EARTH,
                new StatusCondition[] {
                        new Poison(10, 1, 1),
                        new Weak(10, 1, 1),
                }
        );
    }

    public MagicalEarthDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.EARTH,
                statusConditions
        );
    }
}