package abilities.database;

import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.magical.MagicalFireDamage;
import abilities.damages.magical.MagicalIceDamage;
import abilities.damages.magical.MagicalLightningDamage;
import abilities.damages.magical.MagicalPiercingDamage;
import abilities.damages.physical.*;
import abilities.damages.spiritual.SpiritualFireDamage;
import items.equipment.AbilityPrerequisites;
import items.equipment.item_types.ArmorTypes;
import status_conditions.*;

public class AbilityDatabase {
    public static final TargetingAbility PUNCH = new TargetingAbility(
        "Punch", 
        0, 
        1, 
        new Damage[]{new PhysicalBludgeoningDamage(6, 12)}, 
        "A basic melee attack using fists."
    );
    public static final TargetingAbility KICK = new TargetingAbility(
        "Kick", 
        0, 
        1, 
        new Damage[]{new PhysicalBludgeoningDamage(5, 18)}, 
        "A swift kick that can stagger the opponent."
    );
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

    // Character Abilities

    // Mage Abilities
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

    // Warrior Abilities
    public static final TargetingAbility SLASH = new TargetingAbility(
            "Slash",
            3, 1,
            new Damage[]{new PhysicalSlashingDamage(15, 22)},
            AbilityPrerequisites.SLASHING_WEAPONS,
            "A powerful slash with a sword."
    );

    public static final TargetingAbility SHIELD_BASH = new TargetingAbility(
            "Shield Bash",
            2, 1,
            new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
            "A bash with a shield that stuns the target."
    );

    public static final TargetingAbility CHARGE = new TargetingAbility(
            "Charge",
            5, 2,
            new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
            "A charging attack that deals heavy damage."
    );

    // Rogue Abilities
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
