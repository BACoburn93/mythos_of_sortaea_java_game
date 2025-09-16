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
import abilities.single_target.SingleTargetAbility;
import characters.jobs.JobTypes;
import items.equipment.item_types.WeaponTypes;
import status_conditions.Burn;
import status_conditions.StatusCondition;

import java.util.*;

public class AbilityInitializer {

    private static final Map<JobTypes, List<SingleTargetAbility>> jobAbilities = new HashMap<>();

    static {
        // Mage Abilities
        List<SingleTargetAbility> mageAbilities = new ArrayList<>();

        WeaponTypes[] spellCastingWeapons = new WeaponTypes[]{
                WeaponTypes.STAFF,
                WeaponTypes.ORB,
                WeaponTypes.TOME,
                WeaponTypes.WAND
        };

        mageAbilities.add(new SingleTargetAbility("Fireball",
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
                "A sphere of flames that explodes wherever it lands."));

        mageAbilities.add(new SingleTargetAbility("Ice Spike",
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

        mageAbilities.add(new SingleTargetAbility("Lightning Bolt",
        10, 1,
                new Damage[]{new MagicalLightningDamage(15, 22)},
                spellCastingWeapons,
        "A streak of cackling plasma going forth to electrocute a target."));

        jobAbilities.put(JobTypes.MAGE, mageAbilities);


        // Warrior Abilities
        List<SingleTargetAbility> warriorAbilities = new ArrayList<>();
        warriorAbilities.add(new SingleTargetAbility("Slash",
        3, 1,
                new Damage[]{new PhysicalSlashingDamage(15, 22)},
        "A powerful slash with a sword."));

        warriorAbilities.add(new SingleTargetAbility("Shield Bash",
        2, 1,
                new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
        "A bash with a shield that stuns the target."));

        warriorAbilities.add(new SingleTargetAbility("Charge",
        5, 2,
                new Damage[]{new PhysicalBludgeoningDamage(15, 22)},
        "A charging attack that deals heavy damage."));

        jobAbilities.put(JobTypes.WARRIOR, warriorAbilities);


        // Rogue Abilities
        List<SingleTargetAbility> rogueAbilities = new ArrayList<>();
        rogueAbilities.add(new SingleTargetAbility("Backstab",
        1, 1,
                new Damage[]{new PhysicalPiercingDamage(15, 22)},
        "A backstab that has high potential damage."));

        rogueAbilities.add(new SingleTargetAbility("Poison Dart",
        2, 1,
                new Damage[]{new PhysicalPiercingDamage(3, 6), new PhysicalEarthDamage(10, 15)},
        "A dart that poisons the target."));

        rogueAbilities.add(new SingleTargetAbility("Shadow Step",
        0, 1,
                null,
        "A move that allows the rogue to dodge attacks."));

        jobAbilities.put(JobTypes.ROGUE, rogueAbilities);
    }

    public static List<SingleTargetAbility> getAbilities(JobTypes jobType, int numberOfAbilities) {
        List<SingleTargetAbility> abilities = new ArrayList<>(jobAbilities.getOrDefault(jobType, Collections.emptyList()));
        Collections.shuffle(abilities, new Random());
        return abilities.subList(0, Math.min(numberOfAbilities, abilities.size()));
    }
}
