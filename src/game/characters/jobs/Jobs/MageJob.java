package characters.jobs.Jobs;

import abilities.AbilityInitializer;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.skills.Skills;
import characters.jobs.Job;
import characters.jobs.JobTypes;
import status_conditions.StatusConditions;
import characters.jobs.EquipmentProficiencies;

import java.util.ArrayList;

public class MageJob extends Job {

    public MageJob() {
        super(
                JobTypes.MAGE.toString(),
                new HealthValues(9999, 3),
                new ManaValues(120, 3),
                new Attributes(8000, 10, 16, 10, 14, 13, 13),
                new Resistances(2, 2, 2, 2, 5, 5, 4,
                        2, 3, 3, 2, 2),
                new StatusConditions(),
                new Skills(0, 0, 0,0, 0, 5, 0),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.MAGE, 2)),
                EquipmentProficiencies.mageAllowed,
                1.0,
                "intelligence"
        );
    }

    @Override
    protected JobTypes getJobType() {
        return JobTypes.MAGE;
    }

}
