package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.magical.MagicalPiercingDamage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.damages.physical.PhysicalSlashingDamage;
import items.equipment.AbilityPrerequisites;
import status_conditions.Bleed;
import status_conditions.StatusCondition;

public class WeaponAbilities {
        public static final TargetingAbility SLASH = new TargetingAbility(
        "Slash", 
        new Damage[]{new PhysicalSlashingDamage(11, 19)}, 
        AbilityPrerequisites.SLASHING_WEAPONS,
        "A powerful slash."
    );

    public static final TargetingAbility STAB = new TargetingAbility(
        "Stab", 
        new Damage[]{new PhysicalPiercingDamage(10, 20)}, 
        AbilityPrerequisites.PIERCING_WEAPONS,
        "A precise piercing attack."
    );

    public static final TargetingAbility BASH = new TargetingAbility(
        "Bash", 
        new Damage[]{new PhysicalBludgeoningDamage(12, 18)}, 
        AbilityPrerequisites.BLUDGEONING_WEAPONS,
        "A powerful bash."
    );

    public static final TargetingAbility SHOOT = new TargetingAbility(
        "Shoot", 
        new Damage[]{
            new PhysicalPiercingDamage(
                6, 
                15, 
                new StatusCondition[]{new Bleed(5, 50, 2)}
            ),
        }, 
        AbilityPrerequisites.RANGED_WEAPONS,
        "A ranged attack using a bow or crossbow."
    );

    public static final TargetingAbility MAGIC_DART = new TargetingAbility(
        "Magic Dart", 
        new Damage[]{new MagicalPiercingDamage(11, 19)}, 
        AbilityPrerequisites.SPELL_CASTING_WEAPONS,
        "A basic magical attack."
    );
}
