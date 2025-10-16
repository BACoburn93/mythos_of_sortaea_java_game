package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;

public class WarriorAbilities {
    public static final WeaponAbility SHIELD_BASH = new WeaponAbility.Builder(
        "Shield Bash",
        new Damage[]{new PhysicalBludgeoningDamage(15, 22)}
    )
    .levelRequirement(2)
    .manaCost(1)
    .description("A bash with a shield that stuns the target.")
    .withOffhand()
    .build();

    public static final TargetingAbility CHARGE = new TargetingAbility.Builder(
        "Charge",
        new Damage[]{new PhysicalBludgeoningDamage(15, 22)}
    )
    .levelRequirement(5)
    .manaCost(2)
    .description("A charging attack that deals heavy damage.")
    .build();
}
