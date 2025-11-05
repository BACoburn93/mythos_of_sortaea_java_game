package characters.jobs.Jobs;

import abilities.AbilityInitializer;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.skills.Skills;
import characters.jobs.EquipmentProficiencies;
import characters.jobs.Job;
import characters.jobs.JobTypes;
import characters.leveling.LevelScaler;
import status_conditions.StatusConditions;

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
                        new Skills(0, 0, 0, 0, 0, 0, 0),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.WARRIOR, 2)),
                EquipmentProficiencies.warriorAllowed,
                10.0
        );

        LevelScaler.LevelDelta base = new LevelScaler.LevelDelta(
            new Attributes(3,0,0,1,1,0,0),
            new Resistances(1,1,1,0,0,0,0,0,0,0,0,0),
            new HealthValues(10, 0.75),
            new ManaValues(6, 0.25)
        );

        LevelScaler.LevelProgression prog = new LevelScaler.LevelProgression(base)
            .addNthModifier(new LevelScaler.NthModifier(3,
                new LevelScaler.LevelDelta(
                    new Attributes(3,0,0,1,1,0,0), 
                    null, 
                    new HealthValues(10, 1), 
                    null
                )))
            .overrideLevel(20, new LevelScaler.LevelDelta(
                new Attributes(0,5,0,2,1,0,0),
                new Resistances(3,3,3,0,0,0,0,0,0,0,0,0),
                new HealthValues(60,2.5),
                new ManaValues(30,1.0)
            ));

        LevelScaler.register(getName(), prog);
    }

    @Override
    protected JobTypes getJobType() {
        return JobTypes.WARRIOR;
    }

}
