package abilities;

import abilities.damages.Damage;
import abilities.damages.magical.MagicalFireDamage;
import abilities.damages.magical.MagicalIceDamage;
import abilities.damages.magical.MagicalLightningDamage;
import abilities.damages.magical.MagicalPiercingDamage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.damages.physical.PhysicalEarthDamage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.damages.physical.PhysicalSlashingDamage;
import abilities.damages.spiritual.SpiritualFireDamage;
import abilities.single_target.TargetingAbility;
import characters.jobs.JobTypes;
import items.equipment.item_types.WeaponTypes;
import status_conditions.Burn;
import status_conditions.StatusCondition;

import java.util.*;

public class AbilityInitializer {

    private static final Map<JobTypes, List<TargetingAbility>> jobAbilities = new HashMap<>();

    static {
        // Mage Abilities
        List<TargetingAbility> mageAbilities = new ArrayList<>();

        WeaponTypes[] spellCastingWeapons = new WeaponTypes[]{
                WeaponTypes.STAFF,
                WeaponTypes.ORB,
                WeaponTypes.TOME,
                WeaponTypes.WAND
        };

        mageAbilities.add(new TargetingAbility("Fireball",
                12, 1,
                new Damage[]{
                        new MagicalFireDamage(
                                10, 
                                18, 
                                new StatusCondition[]{new Burn(10, 100, 2)}
                        ),
                        new SpiritualFireDamage(3, 5)
                },
                spellCastingWeapons,
                2,
                2,
                "A sphere of flames that explodes wherever it lands."));

        mageAbilities.add(new TargetingAbility("Ice Spike",
                13, 1,
                new Damage[]{
                        new MagicalIceDamage(
                                4, 
                                8,
                                new StatusCondition[]{new Burn(10, 100, 2)}
                        ), 
                        new MagicalPiercingDamage(12, 14)
                },
                spellCastingWeapons,
                "An ice spire that guides itself to impale it's target."));

        mageAbilities.add(new TargetingAbility("Lightning Bolt",
        10, 1,
                new Damage[]{new MagicalLightningDamage(15, 22)},
                spellCastingWeapons,
        "A streak of cackling plasma going forth to electrocute a target."));

        jobAbilities.put(JobTypes.MAGE, mageAbilities);


        // Warrior Abilities
        List<TargetingAbility> warriorAbilities = new ArrayList<>();
        warriorAbilities.add(new TargetingAbility("Slash",
        3, 1,
                new Damage[]{new PhysicalSlashingDamage(15, 22)},
        "A powerful slash with a sword."));

        warriorAbilities.add(new TargetingAbility("Shield Bash",
        2, 1,
                new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
        "A bash with a shield that stuns the target."));

        warriorAbilities.add(new TargetingAbility("Charge",
        5, 2,
                new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
        "A charging attack that deals heavy damage."));

        jobAbilities.put(JobTypes.WARRIOR, warriorAbilities);


        // Rogue Abilities
        List<TargetingAbility> rogueAbilities = new ArrayList<>();
        rogueAbilities.add(new TargetingAbility("Backstab",
        1, 1,
                new Damage[]{new PhysicalPiercingDamage(15, 22)},
        "A backstab that has high potential damage."));

        rogueAbilities.add(new TargetingAbility("Poison Dart",
        2, 1,
                new Damage[]{new PhysicalPiercingDamage(3, 6), new PhysicalEarthDamage(10, 15)},
        "A dart that poisons the target."));

        rogueAbilities.add(new TargetingAbility("Shadow Step",
        0, 1,
                null,
        "A move that allows the rogue to dodge attacks."));

        jobAbilities.put(JobTypes.ROGUE, rogueAbilities);
    }

    public static List<TargetingAbility> getAbilities(JobTypes jobType, int numberOfAbilities) {
        List<TargetingAbility> abilities = new ArrayList<>(jobAbilities.getOrDefault(jobType, Collections.emptyList()));
        Collections.shuffle(abilities, new Random());
        return abilities.subList(0, Math.min(numberOfAbilities, abilities.size()));
    }
}
