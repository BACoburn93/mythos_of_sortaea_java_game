package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalDamage;
import items.equipment.AbilityPrerequisites;

public class RogueAbilities {
    public static final TargetingAbility BACKSTAB = new TargetingAbility.Builder(
        "Backstab",
        new Damage[]{PhysicalDamage.piercing(15, 22)}
    )
    .levelRequirement(1)
    .manaCost(1)
    .weaponTypes(AbilityPrerequisites.PIERCING_WEAPONS)
    .description("A precise strike that deals extra damage when attacking from behind.")
    .build();

    public static final TargetingAbility POISON_DART = new TargetingAbility.Builder(
        "Poison Dart",
        new Damage[]{PhysicalDamage.piercing(3, 6), PhysicalDamage.earth(10, 15)}
    )
    .levelRequirement(2)
    .manaCost(1)
    .weaponTypes(AbilityPrerequisites.PIERCING_WEAPONS)
    .description("A dart that poisons the target.")
    .build();

    public static final TargetingAbility SHADOW_STEP = new TargetingAbility.Builder(
        "Shadow Step",
        new Damage[]{}
    )
    .levelRequirement(3)
    .manaCost(1)
    .description("A move that allows the rogue to dodge attacks.")
    .build();
}

