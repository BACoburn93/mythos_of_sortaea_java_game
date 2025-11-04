package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.magical.MagicalDamage;
import abilities.damages.physical.PhysicalDamage;
import abilities.damages.spiritual.SpiritualDamage;
import items.equipment.AbilityPrerequisites;
import status_conditions.Burn;
import status_conditions.StatusCondition;

public class MageAbilities {
    public static final TargetingAbility FIREBALL = new TargetingAbility.Builder(
            "Fireball",
            new Damage[]{
                MagicalDamage.fire(10, 18, new StatusCondition[]{ new Burn(10, 100, 2) }),
                SpiritualDamage.fire(5, 10)
            })
        .manaCost(1)
        .actionCost(10)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .leftRange(2)
        .rightRange(2)
        .tier(3)
        .description("A sphere of flames that explodes wherever it lands.")
        .build();

    public static final TargetingAbility ICE_SPIKE = new TargetingAbility.Builder(
            "Ice Spike",
            new Damage[]{
                MagicalDamage.ice(4, 8),
                PhysicalDamage.piercing(8, 12)
            })
        .manaCost(1)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .tier(3)
        .description("An ice spire that guides itself to impale it's target.")
        .build();

    public static final TargetingAbility BLIZZARD = new TargetingAbility.Builder(
            "Blizzard",
            new Damage[]{
                MagicalDamage.ice(10, 40),
                PhysicalDamage.ice(10, 40),
                MagicalDamage.wind(10, 30)
            })
        .manaCost(18)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .leftRange(12)
        .rightRange(12)
        .tier(7)
        .description("A blizzard that engulfs a massive area in freezing winds.")
        .build();

    public static final TargetingAbility LIGHTNING_BOLT = new TargetingAbility.Builder(
            "Lightning Bolt",
            new Damage[]{ MagicalDamage.lightning(15, 22) })
        .manaCost(1)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .tier(3)
        .description("A streak of cackling plasma going forth to electrocute a target.")
        .build();

    public static final TargetingAbility FIRE_STORM = new TargetingAbility.Builder(
            "Fire Storm",
            new Damage[]{ MagicalDamage.fire(15, 30), SpiritualDamage.fire(5, 10) })
        .manaCost(30)
        .actionCost(0)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .leftRange(3)
        .rightRange(3)
        .tier(5)
        .description("Summon a storm of fire to engulf a moderate area.")
        .build();

    public static final TargetingAbility METEOR_SWARM = new TargetingAbility.Builder(
            "Meteor Swarm",
            new Damage[]{ MagicalDamage.fire(20, 120), SpiritualDamage.fire(20, 120) })
        .manaCost(3)
        .actionCost(10)
        .weaponTypes(AbilityPrerequisites.SPELL_CASTING_WEAPONS)
        .leftRange(8)
        .rightRange(8)
        .tier(9)
        .description("Summon a swarm of meteors to pummel a large area.")
        .build();
}
