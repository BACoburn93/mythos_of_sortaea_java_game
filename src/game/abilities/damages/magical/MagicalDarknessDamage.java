package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Fear;
import status_conditions.Manipulate;
import status_conditions.Rot;
import status_conditions.StatusCondition;

public class MagicalDarknessDamage extends Damage {
    public MagicalDarknessDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.DARKNESS,
                new StatusCondition[] {
                        new Fear(),
                        new Manipulate(),
                        new Rot(),
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
