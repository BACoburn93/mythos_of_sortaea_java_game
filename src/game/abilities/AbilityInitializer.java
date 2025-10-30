package abilities;

import abilities.ability_types.TargetingAbility;
import characters.jobs.JobTypes;
import abilities.database.AbilityDatabase;

import java.util.*;

public class AbilityInitializer {

    private static final Map<JobTypes, List<TargetingAbility>> jobAbilities = new HashMap<>();

    static {
        // Mage Abilities
        List<TargetingAbility> mageAbilities = new ArrayList<>();
        mageAbilities.add(AbilityDatabase.FIREBALL);
        mageAbilities.add(AbilityDatabase.ICE_SPIKE);
        mageAbilities.add(AbilityDatabase.LIGHTNING_BOLT);
        jobAbilities.put(JobTypes.MAGE, mageAbilities);


        List<TargetingAbility> warriorAbilities = new ArrayList<>();
        warriorAbilities.add(AbilityDatabase.DRAGONBANE_STRIKE);
        warriorAbilities.add(AbilityDatabase.CHARGE);
        jobAbilities.put(JobTypes.WARRIOR, warriorAbilities);

        // Rogue Abilities
        List<TargetingAbility> rogueAbilities = new ArrayList<>();
        rogueAbilities.add(AbilityDatabase.BACKSTAB);
        rogueAbilities.add(AbilityDatabase.POISON_DART);
        rogueAbilities.add(AbilityDatabase.SHADOW_STEP);
        jobAbilities.put(JobTypes.ROGUE, rogueAbilities);
    }

    public static List<TargetingAbility> getAbilities(JobTypes jobType, int numberOfAbilities) {
        List<TargetingAbility> abilities = new ArrayList<>(jobAbilities.getOrDefault(jobType, Collections.emptyList()));
        Collections.shuffle(abilities, new Random());
        return abilities.subList(0, Math.min(numberOfAbilities, abilities.size()));
    }
}
