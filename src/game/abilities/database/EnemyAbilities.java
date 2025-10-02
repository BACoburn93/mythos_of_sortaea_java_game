package abilities.database;

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
        public static final TargetingAbility CLAW = new TargetingAbility(
        "Claw", 
        2, 
        1, 
        new Damage[]{new PhysicalSlashingDamage(15, 16)}, 
        "Claws raking across flesh, causing deep wounds."
    );
    public static final TargetingAbility FLASH_BANG = new TargetingAbility(
        "Flash Bang", 
        10, 
        2, 
        new Damage[]{
                new PhysicalLightDamage(
                        15, 
                        16, 
                        new StatusCondition[]{
                                new Blind(50, 100, 5), 
                                new Confused(50, 100, 5)
                        }
                )}, 
        1,
        1, "An intense burst of light that can blind and confuse targets."
    );
    public static final TargetingAbility ROTTING_TENTACLE = new TargetingAbility(
        "Rotting Tentacle", 
        30, 
        1, 
        new Damage[]{new PhysicalDarknessDamage(15, 16)}, 
        "Lashing tentacle covered in decay that spreads disease."
    );
    public static final TargetingAbility VENOM_MAW = new TargetingAbility(
        "Venom Maw", 
        16, 
        2, 
        new Damage[]{
                new PhysicalVenomDamage(15, 16, new StatusCondition[]{
                        new Envenom(10, 75, 3)
                })
        }, 
        "Fangs dripping with venom that can envenom the target."
    );
    public static final TargetingAbility POISON_MIST = new TargetingAbility(
        "Poison Mist", 
        25, 
        2, 
        new Damage[]{
                new PhysicalEarthDamage(15, 16, new StatusCondition[]{
                        new Poison(5, 50, 2)
                })
        }, 
        2,
        2,
        "A cloud of toxic mist that lingers in the air."
    );
    public static final TargetingAbility IMPALING_ICE = new TargetingAbility(
        "Impaling Ice", 
        8, 
        1, 
        new Damage[]{new PhysicalIceDamage(15, 16)}, 
        "A shard of ice that pierces the target."
    );
    public static final TargetingAbility HYDRO_BURST = new TargetingAbility(
        "Hydro Burst", 
        15, 
        2, 
        new Damage[]{new PhysicalWaterDamage(15, 16)}, 
        1,
        0,
        "Surging water that crashes into up to two targets."
    );
    public static final TargetingAbility TAIL = new TargetingAbility(
        "Tail", 
        5, 
        1, 
        new Damage[]{
        new PhysicalBludgeoningDamage(8, 12),
        new PhysicalSlashingDamage(8, 12)
        },
        1,
        1,
        "A powerful tail swipe that deals both bludgeoning and slashing damage."
    );

    public static final TargetingAbility BITE = new TargetingAbility(
        "Bite", 
        5, 
        30, 
        new Damage[]{new PhysicalPiercingDamage(15, 18)}, 
        "An attack with razor-sharp fangs to rend flesh."
    );

    public static final TargetingAbility FIRE_BREATH = new TargetingAbility(
        "Fire Breath",
        30, 
        30, 
        new Damage[]{new SpiritualFireDamage(25, 35)}, 
        2, 
        2, 
        "A cone of fire that scorches all in its path."
    );
}
