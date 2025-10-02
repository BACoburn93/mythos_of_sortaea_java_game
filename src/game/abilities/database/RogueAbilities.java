package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalEarthDamage;
import abilities.damages.physical.PhysicalPiercingDamage;

public class RogueAbilities {
    public static final TargetingAbility BACKSTAB = new TargetingAbility(
        "Backstab",
        1, 1,
        new Damage[]{new PhysicalPiercingDamage(15, 22)},
        "A backstab that has high potential damage."
    );

    public static final TargetingAbility POISON_DART = new TargetingAbility(
        "Poison Dart",
        2, 1,
        new Damage[]{new PhysicalPiercingDamage(3, 6), new PhysicalEarthDamage(10, 15)},
        "A dart that poisons the target."
    );

    public static final TargetingAbility SHADOW_STEP = new TargetingAbility(
        "Shadow Step",
        0, 1,
        null,
        "A move that allows the rogue to dodge attacks."
    );
}
