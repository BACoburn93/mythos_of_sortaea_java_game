package characters.jobs;

import abilities.AbilityInitializer;
import abilities.status_conditions.StatusConditions;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.attributes.Attributes;
import actors.resistances.Resistances;

import java.util.ArrayList;

public class RogueJob extends Job {
    public RogueJob() {
        super(
                JobTypes.ROGUE.toString(),
                new HealthValues(10, 3),
                new ManaValues(80, 3),
                new Attributes(8, 16, 10, 12, 10, 12, 16),
                new Resistances(1, 1, 1, 5, 4, 4, 4,
                        5, 1, 1, 4, 0),
                new StatusConditions(2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.ROGUE, 2))
        );
    }
}
