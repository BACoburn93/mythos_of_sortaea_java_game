package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Fear;
import abilities.status_conditions.Manipulate;
import abilities.status_conditions.Rot;
import abilities.status_conditions.StatusCondition;

public class MagicalDarknessDamage extends Damage {
    public MagicalDarknessDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.DARKNESS,
                new StatusCondition[] {
                        new Fear(10, 1, 1),
                        new Manipulate(10, 1, 1),
                        new Rot(10, 1, 1),
                }
        );
    }

    public MagicalDarknessDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.DARKNESS,
                statusConditions
        );
    }
}
