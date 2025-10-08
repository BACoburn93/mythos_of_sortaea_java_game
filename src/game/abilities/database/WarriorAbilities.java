package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;

public class WarriorAbilities {
    public static final WeaponAbility SHIELD_BASH = new WeaponAbility(
        "Shield Bash",
        2, 1,
        new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
        1.25,
        "A bash with a shield that stuns the target."
    ).withOffhand();

    public static final TargetingAbility CHARGE = new TargetingAbility(
        "Charge",
        5, 2,
        new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
        "A charging attack that deals heavy damage."
    );
}
