package enemies;

import abilities.damages.Damage;
import abilities.damages.physical.*;
import abilities.damages.spiritual.SpiritualFireDamage;
import abilities.single_target.SingleTargetAbility;

public class AbilityDatabase {
    public static final SingleTargetAbility PUNCH = new SingleTargetAbility(
            "Punch", 5, 100, new Damage[]{new PhysicalBludgeoningDamage(6, 12)}, ""
    );
    public static final SingleTargetAbility KICK = new SingleTargetAbility(
            "Kick", 5, 20, new Damage[]{new PhysicalBludgeoningDamage(5, 18)}, ""
    );
    public static final SingleTargetAbility CLAW = new SingleTargetAbility(
            "Claw", 5, 30, new Damage[]{new PhysicalSlashingDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility FLASH_BANG = new SingleTargetAbility(
            "Flash Bang", 5, 30, new Damage[]{new PhysicalLightDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility ROTTING_TENTACLE = new SingleTargetAbility(
            "Rotting Tentacle", 1, 30, new Damage[]{new PhysicalDarknessDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility VIPER_FANGS = new SingleTargetAbility(
            "Viper Fangs", 1, 30, new Damage[]{new PhysicalVenomDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility POISON_MIST = new SingleTargetAbility(
            "Poison Mist", 1, 30, new Damage[]{new PhysicalEarthDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility IMPALING_ICE = new SingleTargetAbility(
            "Impaling Ice", 1, 30, new Damage[]{new PhysicalIceDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility HYDRO_BURST = new SingleTargetAbility(
            "Hydro Burst", 1, 30, new Damage[]{new PhysicalWaterDamage(15, 16)}, ""
    );
    public static final SingleTargetAbility TAIL = new SingleTargetAbility(
            "Tail", 5, 30, new Damage[]{
                new PhysicalBludgeoningDamage(8, 12),
                new PhysicalSlashingDamage(8, 12)
            }, ""
    );
    public static final SingleTargetAbility BITE = new SingleTargetAbility(
            "Bite", 5, 30, new Damage[]{new PhysicalPiercingDamage(15, 18)}, ""
    );
    public static final SingleTargetAbility FIRE_BREATH = new SingleTargetAbility(
            "Fire Breath", 30, 30, new Damage[]{new SpiritualFireDamage(25, 35)}, ""
    );
}