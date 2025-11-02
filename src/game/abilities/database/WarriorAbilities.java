package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalDamage;
import items.equipment.AbilityPrerequisites;

public class WarriorAbilities {
    public static final WeaponAbility SHIELD_BASH = new WeaponAbility.Builder(
        "Shield Bash",
        new Damage[]{PhysicalDamage.bludgeoning(15, 22)}
    )
    .tier(1)
    .manaCost(1)
    .description("A bash with a shield that stuns the target.")
    .withOffhand()
    .build();

    public static final TargetingAbility CHARGE = new TargetingAbility.Builder(
        "Charge",
        new Damage[]{PhysicalDamage.bludgeoning(15, 22)}
    )
    .tier(2)
    .manaCost(2)
    .description("A charging attack that deals heavy damage.")
    .build();

    public static final TargetingAbility FLAME_SLASH = new TargetingAbility.Builder(
        "Flame Slash",
        new Damage[]{
            PhysicalDamage.slashing(8, 13),
            PhysicalDamage.fire(6, 10)
        }
    )
    .tier(2)
    .manaCost(3)
    .weaponTypes(AbilityPrerequisites.SLASHING_WEAPONS)
    .description("A fiery slash that deals burns and cuts.")
    .build();

    public static final TargetingAbility FROST_WIND_SLASH = new TargetingAbility.Builder(
        "Frost Wind Slash",
        new Damage[]{
            PhysicalDamage.wind(3, 5),
            PhysicalDamage.slashing(5, 8),
            PhysicalDamage.ice(6, 10),
        }
    )
    .tier(2)
    .manaCost(3)
    .weaponTypes(AbilityPrerequisites.SLASHING_WEAPONS)
    .description("A chilling slash wounds deeply with numbing frost.")
    .build();

    public static final TargetingAbility DRAGONBANE_STRIKE = new TargetingAbility.Builder(
        "Dragonbane Strike",
        new Damage[]{
            PhysicalDamage.slashing(5, 8),
        }
    )
    .tier(0)
    .manaCost(3)
    .speciesDamageModifier("DRAGON", 2.50) // +250% vs any DRAGON
    .description("A powerful strike that deals extra damage to dragons.")
    .build();
}
