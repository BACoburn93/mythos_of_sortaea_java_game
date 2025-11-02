package abilities.enemy;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.magical.*;
import abilities.damages.physical.*;
import abilities.damages.spiritual.*;
import enemies.EnemyKey;
import status_conditions.Blind;
import status_conditions.Confused;
import status_conditions.Envenom;
import status_conditions.Poison;
import status_conditions.StatusCondition;

public class EnemyAbilities {
    public static final TargetingAbility PUNCH = new TargetingAbility.Builder(
            "Punch",
            new Damage[]{ PhysicalDamage.bludgeoning(6, 12) }
        )
        .manaCost(0)
        .description("A standard punch.")
        .build();

    public static final TargetingAbility KICK = new TargetingAbility.Builder(
            "Kick",
            new Damage[]{ PhysicalDamage.bludgeoning(5, 18) }
        )
        .manaCost(0)
        .description("A swift kick")
        .build();

    public static final TargetingAbility CLAW = new TargetingAbility.Builder(
            "Claw",
            new Damage[]{ PhysicalDamage.slashing(15, 16) }
        )
        .manaCost(2)
        .description("Claws raking across flesh, causing deep wounds.")
        .build();

    public static final TargetingAbility FLASH_BANG = new TargetingAbility.Builder(
            "Flash Bang",
            new Damage[]{
                new PhysicalLightDamage(
                    15,
                    16,
                    new StatusCondition[]{
                        new Blind(50, 100, 5),
                        new Confused(50, 100, 5)
                    }
                )
            }
        )
        .manaCost(10)
        .leftRange(1)
        .rightRange(1)
        .description("An intense burst of light that can blind and confuse targets.")
        .build();

    public static final TargetingAbility ROTTING_TENTACLE = new TargetingAbility.Builder(
            "Rotting Tentacle",
            new Damage[]{ PhysicalDamage.darkness(15, 16) }
        )
        .manaCost(30)
        .description("Lashing tentacle covered in decay that spreads disease.")
        .build();

    public static final TargetingAbility VENOM_MAW = new TargetingAbility.Builder(
            "Venom Maw",
            new Damage[]{
                new PhysicalVenomDamage(15, 16, new StatusCondition[]{
                    new Envenom(10, 75, 3)
                })
            }
        )
        .manaCost(16)
        .description("Fangs dripping with venom that can envenom the target.")
        .build();

    public static final TargetingAbility POISON_MIST = new TargetingAbility.Builder(
            "Poison Mist",
            new Damage[]{
                new PhysicalEarthDamage(15, 16, new StatusCondition[]{
                    new Poison(5, 50, 2)
                })
            }
        )
        .manaCost(25)
        .leftRange(1)
        .rightRange(1)
        .description("A cloud of toxic mist that lingers in the air.")
        .build();

        // ICE ABILITIES
    public static final TargetingAbility FROST_BOLT = new TargetingAbility.Builder(
            "Frost Bolt",
            new Damage[]{ MagicalDamage.ice(4, 6) }
        )
        .description("A small bolt of ice.")
        .build();

    public static final TargetingAbility WINTERS_GRASP = new TargetingAbility.Builder(
            "Winters Grasp",
            new Damage[]{ MagicalDamage.ice(6, 12) }
        )
        .tier(1)
        .manaCost(2)
        .description("A freezing grasp that immobilizes the target.")
        .build();

    public static final TargetingAbility IMPALING_ICE = new TargetingAbility.Builder(
            "Impaling Ice",
            new Damage[]{ PhysicalDamage.ice(15, 16) }
        )
        .tier(2)
        .manaCost(8)
        .description("A shard of ice that pierces the target.")
        .build();

    public static final TargetingAbility BLACK_FROST_ABYSS = new TargetingAbility.Builder(
            "Black Frost Abyss",
            new Damage[]{SpiritualDamage.ice(30, 40), SpiritualDamage.darkness(30, 40) }
        )
        .tier(6)
        .manaCost(8)
        .leftRange(1)
        .rightRange(1)
        .description("A swirling vortex of black ice from unknown origins.")
        .build();

    public static final TargetingAbility HYDRO_BURST = new TargetingAbility.Builder(
            "Hydro Burst",
            new Damage[]{ PhysicalDamage.water(15, 16) }
        )
        .manaCost(15)
        .leftRange(1)
        .rightRange(0)
        .description("Surging water that crashes into up to two targets.")
        .build();

    public static final TargetingAbility TAIL = new TargetingAbility.Builder(
            "Tail",
            new Damage[]{
                PhysicalDamage.bludgeoning(8, 12),
                PhysicalDamage.slashing(8, 12)
            }
        )
        .manaCost(0)
        .leftRange(1)
        .rightRange(1)
        .description("A powerful tail swipe that deals both bludgeoning and slashing damage.")
        .build();

    public static final TargetingAbility BITE = new TargetingAbility.Builder(
            "Bite",
            new Damage[]{ PhysicalDamage.piercing(15, 18) }
        )
        .manaCost(0)
        .description("An attack with razor-sharp fangs to rend flesh.")
        .build();

        // FIRE ABILITIES
    public static final TargetingAbility DRAGON_BREATH = new TargetingAbility.Builder(
            "Dragon Breath",
            new Damage[]{ SpiritualDamage.fire(25, 35) }
        )
        .tier(6)
        .manaCost(16)
        .leftRange(1)
        .rightRange(1)
        .description("A cone of a dragons breath that destroys all in its path.")
        .allowedActorTypes(EnemyKey.DRAGON.key())
        .build();

        
    public static final TargetingAbility MOTE_OF_FIRE = new TargetingAbility.Builder(
            "Mote of Fire",
            new Damage[]{ MagicalDamage.fire(3, 8) }
        )
        .tier(0)
        .manaCost(1)
        .description("A small mote of fire that deals minor damage.")
        .build();

    public static final TargetingAbility DANCING_CINDERS = new TargetingAbility.Builder(
            "Dancing Cinders",
            new Damage[]{ SpiritualDamage.fire(4, 6), MagicalDamage.earth(3, 4) }
        )
        .tier(1)
        .leftRange(1)
        .manaCost(1)
        .description("A small burst of cinders than burn a target and the target to the left of it.")
        .build();

    public static final TargetingAbility SPHERE_OF_FLAMES = new TargetingAbility.Builder(
            "Sphere of Flames",
            new Damage[]{ MagicalDamage.fire(28, 38) }
        )
        .tier(4)
        .manaCost(18)
        .leftRange(4)
        .rightRange(4)
        .description("A flaming sphere that engulfs all in its path.")
        .build();

    public static final TargetingAbility PYROCLASM = new TargetingAbility.Builder(
            "Pyroclasm",
            new Damage[]{ MagicalDamage.fire(35, 50), PhysicalDamage.bludgeoning(5, 10) }
        )
        .tier(5)
        .manaCost(30)
        .leftRange(5)
        .rightRange(5)
        .description("A devastating eruption of fire that incinerates everything in its wake.")
        .build();

}