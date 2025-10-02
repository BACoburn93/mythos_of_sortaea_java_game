package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.magical.MagicalFireDamage;
import abilities.damages.magical.MagicalIceDamage;
import abilities.damages.magical.MagicalLightningDamage;
import abilities.damages.magical.MagicalPiercingDamage;
import abilities.damages.spiritual.SpiritualFireDamage;
import items.equipment.AbilityPrerequisites;
import status_conditions.Burn;
import status_conditions.StatusCondition;

public class MageAbilities {
    public static final TargetingAbility FIREBALL = new TargetingAbility(
        "Fireball",
        12, 1,
        new Damage[]{
                new MagicalFireDamage(
                        10,
                        18,
                        new StatusCondition[]{new Burn(10, 100, 2)}
                ),
                new SpiritualFireDamage(3, 5)
        },
        AbilityPrerequisites.SPELL_CASTING_WEAPONS,
        2,
        2,
        "A sphere of flames that explodes wherever it lands."
    );

    public static final TargetingAbility ICE_SPIKE = new TargetingAbility(
        "Ice Spike",
        13, 1,
        new Damage[]{
                new MagicalIceDamage(
                        4,
                        8,
                        new StatusCondition[]{new Burn(10, 100, 2)}
                ),
                new MagicalPiercingDamage(12, 14)
        },
        AbilityPrerequisites.SPELL_CASTING_WEAPONS,
        "An ice spire that guides itself to impale it's target."
    );

    public static final TargetingAbility LIGHTNING_BOLT = new TargetingAbility(
        "Lightning Bolt",
        10, 1,
        new Damage[]{new MagicalLightningDamage(15, 22)},
        AbilityPrerequisites.SPELL_CASTING_WEAPONS,
        "A streak of cackling plasma going forth to electrocute a target."
    );

    public static final TargetingAbility METEOR_SWARM = new TargetingAbility(
        "Meteor Swarm",
        3, 10, 1,
        new Damage[]{new MagicalFireDamage(20, 120), new MagicalFireDamage(20, 120)},
        AbilityPrerequisites.SPELL_CASTING_WEAPONS,
        8, 8,
        "Summon a swarm of meteors to pummel a large area."
    );
}
