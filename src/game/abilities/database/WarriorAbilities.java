package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.damages.DamageTypes;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.damages.physical.PhysicalFireDamage;

public class WarriorAbilities {
    public static final WeaponAbility SHIELD_BASH = new WeaponAbility.Builder(
        "Shield Bash",
        new Damage[]{new PhysicalBludgeoningDamage(15, 22)}
    )
    .tier(1)
    .manaCost(1)
    .description("A bash with a shield that stuns the target.")
    .withOffhand()
    .build();

    public static final TargetingAbility CHARGE = new TargetingAbility.Builder(
        "Charge",
        new Damage[]{new PhysicalBludgeoningDamage(15, 22)}
    )
    .tier(2)
    .manaCost(2)
    .description("A charging attack that deals heavy damage.")
    .build();

    public static final TargetingAbility FLAME_SLASH = new TargetingAbility.Builder(
        "Flame Slash",
        new Damage[]{
            new PhysicalBludgeoningDamage(8, 13),
            new PhysicalFireDamage(6, 10)
        }
    )
    .tier(2)
    .manaCost(3)
    .description("A fiery slash that deals burns and cuts.")
    .allowedDamageTypes(DamageTypes.SLASHING)
    .build();
}
