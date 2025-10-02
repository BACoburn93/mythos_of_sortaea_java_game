package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.physical.*;

public class BasicAbilities {
    public static final TargetingAbility PUNCH = new TargetingAbility(
        "Punch", 0, 1, new PhysicalBludgeoningDamage[]{new PhysicalBludgeoningDamage(6, 12)},
        "A basic melee attack using fists."
    );
    public static final TargetingAbility KICK = new TargetingAbility(
        "Kick", 0, 1, new PhysicalBludgeoningDamage[]{new PhysicalBludgeoningDamage(5, 18)},
        "A swift kick that can stagger the opponent."
    );
}