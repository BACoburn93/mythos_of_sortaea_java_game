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
        public static final WeaponAbility SLASH = new WeaponAbility(
        "Slash", 
        new Damage[]{new PhysicalSlashingDamage(11, 19)}, 
        1.8,
        AbilityPrerequisites.SLASHING_WEAPONS,
        "A powerful slash."
    );

    public static final WeaponAbility STAB = new WeaponAbility(
        "Stab", 
        new Damage[]{new PhysicalPiercingDamage(10, 20)}, 
        1.5,
        AbilityPrerequisites.PIERCING_WEAPONS,
        "A precise piercing attack."
    );

    public static final WeaponAbility BASH = new WeaponAbility(
        "Bash", 
        new Damage[]{new PhysicalBludgeoningDamage(12, 18)}, 
        1.6,
        AbilityPrerequisites.BLUDGEONING_WEAPONS,
        "A powerful bash."
    );

    public static final WeaponAbility SHOOT = new WeaponAbility(
        "Shoot", 
        new Damage[]{
            new PhysicalPiercingDamage(
                6, 
                15, 
                new StatusCondition[]{new Bleed(5, 50, 2)}
            ),
        }, 
        1.4,
        AbilityPrerequisites.RANGED_WEAPONS,
        "A ranged attack using a bow or crossbow."
    );

    public static final WeaponAbility MAGIC_DART = new WeaponAbility(
        "Magic Dart", 
        new Damage[]{new MagicalPiercingDamage(11, 19)}, 
        0,
        AbilityPrerequisites.SPELL_CASTING_WEAPONS,
        "A basic magical attack."
    );
}
