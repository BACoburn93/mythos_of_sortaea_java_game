package characters.jobs.Jobs;

import abilities.AbilityInitializer;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.skills.Skills;
import characters.jobs.Job;
import characters.jobs.JobTypes;
import characters.leveling.LevelScaler;
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


        // Mage Scaling for Attributes, Resistances, Health, and Mana
        LevelScaler.LevelDelta base = new LevelScaler.LevelDelta(
            new Attributes(0,0,3,0,0,1,1),
            new Resistances(0,0,0,1,1,1,0,0,0,0,0,0),
            new HealthValues(6, 0.25),
            new ManaValues(12, 0.5)
        );

        LevelScaler.LevelProgression prog = new LevelScaler.LevelProgression(base)
            .addNthModifier(new LevelScaler.NthModifier(3,
                new LevelScaler.LevelDelta(
                    new Attributes(0,0,3,0,1,0,2), 
                    null, 
                    null,
                    new ManaValues(5, 1))))
            .overrideLevel(20, new LevelScaler.LevelDelta(
                new Attributes(0,0,5,0,0,2,1),
                new Resistances(0,0,0,3,3,3,0,0,0,0,0,0),
                new HealthValues(30,1.0),
                new ManaValues(50,2.0)
            ));

        LevelScaler.register(getName(), prog);
    }



    @Override
    protected JobTypes getJobType() {
        return JobTypes.MAGE;
    }

}
