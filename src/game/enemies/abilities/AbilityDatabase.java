package enemies.abilities;

import abilities.damages.Damage;
import abilities.damages.physical.*;
import abilities.damages.spiritual.SpiritualFireDamage;
import abilities.single_target.TargetingAbility;

public class AbilityDatabase {
    public static final TargetingAbility PUNCH = new TargetingAbility(
            "Punch", 5, 100, new Damage[]{new PhysicalBludgeoningDamage(6, 12)}, ""
    );
    public static final TargetingAbility KICK = new TargetingAbility(
            "Kick", 5, 20, new Damage[]{new PhysicalBludgeoningDamage(5, 18)}, ""
    );
    public static final TargetingAbility CLAW = new TargetingAbility(
            "Claw", 5, 30, new Damage[]{new PhysicalSlashingDamage(15, 16)}, ""
    );
    public static final TargetingAbility FLASH_BANG = new TargetingAbility(
            "Flash Bang", 5, 30, new Damage[]{new PhysicalLightDamage(15, 16)}, ""
    );
    public static final TargetingAbility ROTTING_TENTACLE = new TargetingAbility(
            "Rotting Tentacle", 1, 30, new Damage[]{new PhysicalDarknessDamage(15, 16)}, ""
    );
    public static final TargetingAbility VENOM_MAW = new TargetingAbility(
            "Viper Fangs", 1, 30, new Damage[]{new PhysicalVenomDamage(15, 16)}, ""
    );
    public static final TargetingAbility POISON_MIST = new TargetingAbility(
            "Poison Mist", 1, 30, new Damage[]{new PhysicalEarthDamage(15, 16)}, ""
    );
    public static final TargetingAbility IMPALING_ICE = new TargetingAbility(
            "Impaling Ice", 1, 30, new Damage[]{new PhysicalIceDamage(15, 16)}, ""
    );
    public static final TargetingAbility HYDRO_BURST = new TargetingAbility(
            "Hydro Burst", 1, 30, new Damage[]{new PhysicalWaterDamage(15, 16)}, ""
    );
    public static final TargetingAbility TAIL = new TargetingAbility(
            "Tail", 5, 30, new Damage[]{
                new PhysicalBludgeoningDamage(8, 12),
                new PhysicalSlashingDamage(8, 12)
            }, ""
    );
    public static final TargetingAbility BITE = new TargetingAbility(
            "Bite", 5, 30, new Damage[]{new PhysicalPiercingDamage(15, 18)}, ""
    );
    public static final TargetingAbility FIRE_BREATH = new TargetingAbility(
            "Fire Breath", 30, 30, new Damage[]{new SpiritualFireDamage(25, 35)}, ""
    );
}