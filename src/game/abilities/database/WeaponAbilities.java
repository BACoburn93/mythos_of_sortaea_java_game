package abilities.database;

import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.damages.magical.MagicalDamage;
import abilities.damages.physical.PhysicalDamage;
import abilities.damages.physical.PhysicalPiercingDamage;
import items.equipment.AbilityPrerequisites;
import status_conditions.Bleed;
import status_conditions.StatusCondition;

public class WeaponAbilities {
    public static final WeaponAbility SLASH = new WeaponAbility.Builder(
            "Slash",
            new Damage[]{ PhysicalDamage.slashing(11, 19) }
        )
        .multiplier(1.8)
        .weaponTypes(AbilityPrerequisites.SLASHING_WEAPONS)
        .description("A powerful slash.")
        .build();

    public static final WeaponAbility STAB = new WeaponAbility.Builder(
            "Stab",
            new Damage[]{ PhysicalDamage.piercing(10, 20) }
        )
        .multiplier(1.5)
        .weaponTypes(AbilityPrerequisites.PIERCING_WEAPONS)
        .description("A precise piercing attack.")
        .build();

    public static final WeaponAbility BASH = new WeaponAbility.Builder(
            "Bash",
            new Damage[]{ PhysicalDamage.bludgeoning(12, 18) }
        )
        .multiplier(1.6)
        .weaponTypes(AbilityPrerequisites.BLUDGEONING_WEAPONS)
        .description("A powerful bash.")
        .build();

    public static final WeaponAbility SHOOT = new WeaponAbility.Builder(
            "Shoot",
            new Damage[]{
                new PhysicalPiercingDamage(
                    6,
                    15,
                    new StatusCondition[]{ new Bleed(5, 50, 2) }
                )
            }
        )
        .multiplier(1.4)
        .weaponTypes(AbilityPrerequisites.RANGED_WEAPONS)
        .description("A ranged attack using a bow or crossbow.")
        .build();

    public static final WeaponAbility MAGIC_DART = new WeaponAbility.Builder(
            "Magic Dart",
            new Damage[]{ MagicalDamage.piercing(11, 19) }
        )
        .multiplier(0)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .description("A basic magical attack.")
        .build();
}
