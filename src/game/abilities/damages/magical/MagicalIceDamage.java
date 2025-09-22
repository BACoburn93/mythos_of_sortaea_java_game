package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.*;

public class MagicalIceDamage extends Damage {
    public MagicalIceDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.ICE,
                new StatusCondition[] {
                        new Freeze(),
                        new Sick(),
                        new Slow(),
                        new Weak(),
                }
        );
    }

    public MagicalIceDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.ICE,
                statusConditions
        );
    }
}
