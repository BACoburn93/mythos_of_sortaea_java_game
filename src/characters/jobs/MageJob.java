package characters.jobs;

import abilities.AbilityInitializer;
import abilities.status_conditions.StatusConditions;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;

import java.util.ArrayList;

public class MageJob extends Job {

    public MageJob() {
        super(
                JobTypes.MAGE.toString(),
                new HealthValues(9999, 3),
                new ManaValues(120, 3),
                new Attributes(8, 10, 16, 10, 14, 13, 13),
                new Resistances(2, 2, 2, 2, 5, 5, 4,
                        2, 3, 3, 2, 2),
                new StatusConditions(-100, -100, -100, -100, -100, -100, -100, -100,
                        -100, -100, -100, -100, -100, -100, -100, -100, -100),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.MAGE, 2))
        );
    }

}
