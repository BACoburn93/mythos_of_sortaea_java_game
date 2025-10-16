package abilities.enemy;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.damages.physical.PhysicalDarknessDamage;
import abilities.damages.physical.PhysicalEarthDamage;
import abilities.damages.physical.PhysicalIceDamage;
import abilities.damages.physical.PhysicalLightDamage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.damages.physical.PhysicalSlashingDamage;
import abilities.damages.physical.PhysicalVenomDamage;
import abilities.damages.physical.PhysicalWaterDamage;
import abilities.damages.spiritual.SpiritualFireDamage;
import status_conditions.Blind;
import status_conditions.Confused;
import status_conditions.Envenom;
import status_conditions.Poison;
import status_conditions.StatusCondition;

public class EnemyAbilities {
    public static final TargetingAbility PUNCH = new TargetingAbility.Builder(
            "Punch",
            new Damage[]{ new PhysicalBludgeoningDamage(6, 12) }
        )
        .manaCost(0)
        .description("")
        .build();

    public static final TargetingAbility KICK = new TargetingAbility.Builder(
            "Kick",
            new Damage[]{ new PhysicalBludgeoningDamage(5, 18) }
        )
        .manaCost(0)
        .description("")
        .build();

    public static final TargetingAbility CLAW = new TargetingAbility.Builder(
            "Claw",
            new Damage[]{ new PhysicalSlashingDamage(15, 16) }
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
            new Damage[]{ new PhysicalDarknessDamage(15, 16) }
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

    public static final TargetingAbility IMPALING_ICE = new TargetingAbility.Builder(
            "Impaling Ice",
            new Damage[]{ new PhysicalIceDamage(15, 16) }
        )
        .manaCost(8)
        .description("A shard of ice that pierces the target.")
        .build();

    public static final TargetingAbility HYDRO_BURST = new TargetingAbility.Builder(
            "Hydro Burst",
            new Damage[]{ new PhysicalWaterDamage(15, 16) }
        )
        .manaCost(15)
        .leftRange(1)
        .rightRange(0)
        .description("Surging water that crashes into up to two targets.")
        .build();

    public static final TargetingAbility TAIL = new TargetingAbility.Builder(
            "Tail",
            new Damage[]{
                new PhysicalBludgeoningDamage(8, 12),
                new PhysicalSlashingDamage(8, 12)
            }
        )
        .manaCost(0)
        .leftRange(1)
        .rightRange(1)
        .description("A powerful tail swipe that deals both bludgeoning and slashing damage.")
        .build();

    public static final TargetingAbility BITE = new TargetingAbility.Builder(
            "Bite",
            new Damage[]{ new PhysicalPiercingDamage(15, 18) }
        )
        .manaCost(0)
        .description("An attack with razor-sharp fangs to rend flesh.")
        .build();

    public static final TargetingAbility FIRE_BREATH = new TargetingAbility.Builder(
            "Fire Breath",
            new Damage[]{ new SpiritualFireDamage(25, 35) }
        )
        .manaCost(30)
        .leftRange(2)
        .rightRange(2)
        .description("A cone of fire that scorches all in its path.")
        .build();
}