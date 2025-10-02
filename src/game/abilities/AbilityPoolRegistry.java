package abilities;

import abilities.database.*;

import characters.jobs.JobTypes;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class AbilityPoolRegistry {
    private static final Map<JobTypes, AbilityPool> pools = new HashMap<>();

    static {
        pools.put(JobTypes.MAGE, new AbilityPool(AbilityDatabase.getMageAbilities()));
        pools.put(JobTypes.WARRIOR, new AbilityPool(AbilityDatabase.getWarriorAbilities()));
        pools.put(JobTypes.ROGUE, new AbilityPool(AbilityDatabase.getRogueAbilities()));
    }

    public static AbilityPool getPool(JobTypes jobType) {
        return pools.get(jobType);
    }
}