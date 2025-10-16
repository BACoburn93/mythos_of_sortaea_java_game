package abilities.database;

import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.damages.magical.MagicalPiercingDamage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.damages.physical.PhysicalSlashingDamage;
import items.equipment.AbilityPrerequisites;
import status_conditions.Bleed;
import status_conditions.StatusCondition;

public class WeaponAbilities {
    public static final WeaponAbility SLASH = new WeaponAbility.Builder(
            "Slash",
            new Damage[]{ new PhysicalSlashingDamage(11, 19) }
        )
        .multiplier(1.8)
        .weaponTypes(AbilityPrerequisites.SLASHING_WEAPONS)
        .description("A powerful slash.")
        .build();

    public static final WeaponAbility STAB = new WeaponAbility.Builder(
            "Stab",
            new Damage[]{ new PhysicalPiercingDamage(10, 20) }
        )
        .multiplier(1.5)
        .weaponTypes(AbilityPrerequisites.PIERCING_WEAPONS)
        .description("A precise piercing attack.")
        .build();

    public static final WeaponAbility BASH = new WeaponAbility.Builder(
            "Bash",
            new Damage[]{ new PhysicalBludgeoningDamage(12, 18) }
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
            new Damage[]{ new MagicalPiercingDamage(11, 19) }
        )
        .multiplier(0)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .description("A basic magical attack.")
        .build();
}
