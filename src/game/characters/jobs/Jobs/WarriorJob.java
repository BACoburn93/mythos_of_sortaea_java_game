package characters.jobs.Jobs;

import abilities.AbilityInitializer;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import characters.jobs.Job;
import characters.jobs.JobTypes;
import status_conditions.StatusConditions;
import actors.attributes.Attributes;
import actors.resistances.Resistances;

import java.util.ArrayList;

public class WarriorJob extends Job {
    public WarriorJob() {
        super(
                JobTypes.WARRIOR.toString(),
                new HealthValues(150, 5),
                new ManaValues(50, 1),
                new Attributes(15, 13, 8, 14, 12, 12, 10),
                new Resistances(5, 5, 5, 2, 3, 3, 3,
                        4, 1, 1, 1, 1),
                new StatusConditions(2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.WARRIOR, 2))
        );
    }
}
