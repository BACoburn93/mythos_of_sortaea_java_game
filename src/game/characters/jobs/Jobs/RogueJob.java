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
                        new Skills(0, 0, 0, 0, 0, 0, 0),
                new ArrayList<>(AbilityInitializer.getAbilities(JobTypes.ROGUE, 2)),
                EquipmentProficiencies.rogueAllowed,
                6.0,
                "dexterity"
        );

        LevelScaler.LevelDelta base = new LevelScaler.LevelDelta(
            new Attributes(0,3,0,1,1,0,0),
            new Resistances(0,2,1,0,0,0,0,0,0,0,0,0),
            new HealthValues(8, 0.5),
            new ManaValues(8, 0.25)
        );

        LevelScaler.LevelProgression prog = new LevelScaler.LevelProgression(base)
            .addNthModifier(new LevelScaler.NthModifier(3,
                new LevelScaler.LevelDelta(
                    new Attributes(0,0,3,0,1,0,2), 
                    null, 
                    new HealthValues(5, 1), 
                    null
                )))
            .overrideLevel(20, new LevelScaler.LevelDelta(
                new Attributes(0,6,0,1,1,0,0),
                new Resistances(0,10,5,0,0,0,0,0,0,0,0,0),
                new HealthValues(50,2.0),
                new ManaValues(30,1.0)
            ));

        LevelScaler.register(getName(), prog);
    }

    @Override
    protected JobTypes getJobType() {
        return JobTypes.ROGUE;
    }

}
