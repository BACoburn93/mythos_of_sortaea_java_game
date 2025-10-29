package abilities.database;

import java.util.ArrayList;
import java.util.List;

import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.enemy.EnemyAbilities;


public class AbilityDatabase {

    // Weapon Abilities
    public static final TargetingAbility SLASH = WeaponAbilities.SLASH;
    public static final TargetingAbility STAB = WeaponAbilities.STAB;
    public static final TargetingAbility BASH = WeaponAbilities.BASH;
    public static final TargetingAbility SHOOT = WeaponAbilities.SHOOT;
    public static final TargetingAbility MAGIC_DART = WeaponAbilities.MAGIC_DART;

    // Enemy Abilities
    public static final TargetingAbility PUNCH = EnemyAbilities.PUNCH;
    public static final TargetingAbility KICK = EnemyAbilities.KICK;
    public static final TargetingAbility CLAW = EnemyAbilities.CLAW;
    public static final TargetingAbility BITE = EnemyAbilities.BITE;
    public static final TargetingAbility FLASH_BANG = EnemyAbilities.FLASH_BANG;
    public static final TargetingAbility ROTTING_TENTACLE = EnemyAbilities.ROTTING_TENTACLE;
    public static final TargetingAbility VENOM_MAW = EnemyAbilities.VENOM_MAW;
    public static final TargetingAbility POISON_MIST = EnemyAbilities.POISON_MIST;
    public static final TargetingAbility FROST_BOLT = EnemyAbilities.FROST_BOLT;
    public static final TargetingAbility WINTERS_GRASP = EnemyAbilities.WINTERS_GRASP;
    public static final TargetingAbility IMPALING_ICE = EnemyAbilities.IMPALING_ICE;
    public static final TargetingAbility BLACK_FROST_ABYSS = EnemyAbilities.BLACK_FROST_ABYSS;
    public static final TargetingAbility MOTE_OF_FIRE = EnemyAbilities.MOTE_OF_FIRE;
    public static final TargetingAbility DANCING_CINDERS = EnemyAbilities.DANCING_CINDERS;
    public static final TargetingAbility FIRE_BREATH = EnemyAbilities.FIRE_BREATH;
    public static final TargetingAbility SPHERE_OF_FLAMES = EnemyAbilities.SPHERE_OF_FLAMES;
    public static final TargetingAbility PYROCLASM = EnemyAbilities.PYROCLASM;
    public static final TargetingAbility TAIL = EnemyAbilities.TAIL;

    // Character Abilities

    // Mage Abilities
    public static final TargetingAbility FIREBALL = MageAbilities.FIREBALL;
    public static final TargetingAbility ICE_SPIKE = MageAbilities.ICE_SPIKE;
    public static final TargetingAbility BLIZZARD = MageAbilities.BLIZZARD;
    public static final TargetingAbility LIGHTNING_BOLT = MageAbilities.LIGHTNING_BOLT;
    public static final TargetingAbility FIRE_STORM = MageAbilities.FIRE_STORM;
    public static final TargetingAbility METEOR_SWARM = MageAbilities.METEOR_SWARM;

    // Warrior Abilities
    public static final WeaponAbility SHIELD_BASH = WarriorAbilities.SHIELD_BASH;
    public static final TargetingAbility CHARGE = WarriorAbilities.CHARGE;
    public static final TargetingAbility FLAME_SLASH = WarriorAbilities.FLAME_SLASH;
    public static final TargetingAbility FROST_WIND_SLASH = WarriorAbilities.FROST_WIND_SLASH;

    // Rogue Abilities
    public static final TargetingAbility BACKSTAB = RogueAbilities.BACKSTAB;
    public static final TargetingAbility POISON_DART = RogueAbilities.POISON_DART;
    public static final TargetingAbility SHADOW_STEP = RogueAbilities.SHADOW_STEP;

    // Class Ability Getters
    public static List<Ability> getMageAbilities() {
        return new ArrayList<>(List.of(
            FIREBALL,
            ICE_SPIKE,
            BLIZZARD,
            LIGHTNING_BOLT,
            FIRE_STORM,
            METEOR_SWARM
        ));
    }

    public static List<Ability> getWarriorAbilities() {
        return new ArrayList<>(List.of(
            SHIELD_BASH,
            CHARGE,
            FLAME_SLASH,
            FROST_WIND_SLASH
        ));
    }

    public static List<Ability> getRogueAbilities() {
        return new ArrayList<>(List.of(
            BACKSTAB,
            POISON_DART,
            SHADOW_STEP
        ));
    }
}