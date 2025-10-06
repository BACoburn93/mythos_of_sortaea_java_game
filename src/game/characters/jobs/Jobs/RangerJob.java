package characters.jobs.Jobs;

import abilities.AbilityInitializer;
import abilities.damages.Damage;
import abilities.damages.DamageTypeProvider;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import characters.jobs.EquipmentProficiencies;
import characters.jobs.Job;
import characters.jobs.JobTypes;
import status_conditions.StatusConditions;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class RangerJob extends Job {
    public RangerJob() {
        super(
                JobTypes.RANGER.toString(),
                new HealthValues(100, 4),
                new ManaValues(80, 2),
                new Attributes(10, 15, 10, 12, 13, 10, 14),
                new Resistances(3, 3, 3, 5, 3, 3, 4,
                        4, 3, 3, 0, 0),
                new StatusConditions(2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.RANGER, 2)),
                EquipmentProficiencies.rangerAllowed,
                8.0,
                "dexterity"
        );
    }

    @Override
    protected JobTypes getJobType() {
        return JobTypes.RANGER;
    }

}
