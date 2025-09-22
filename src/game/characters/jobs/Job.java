package characters.jobs;

import abilities.Ability;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import status_conditions.StatusConditions;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Job {
    private String name;
    private HealthValues healthValues;
    private ManaValues manaValues;
    private Attributes attributes;
    private Resistances resistances;
    private StatusConditions statusConditions;
    // Need to bind status conditions to jobs so that they affect character statistics
    private ArrayList<Ability> jobAbilities;

    public Job(String name, HealthValues healthValues, ManaValues manaValues,
               Attributes attributes, Resistances resistances, ArrayList<Ability> jobAbilities) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = new StatusConditions();
        this.jobAbilities = jobAbilities;
    }

    public Job(String name, HealthValues healthValues, ManaValues manaValues,
               Attributes attributes, Resistances resistances, StatusConditions statusConditions, ArrayList<Ability> jobAbilities) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = statusConditions;
        this.jobAbilities = jobAbilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HealthValues getHealthValues() {
        return healthValues;
    }

    public void setHealthValues(HealthValues healthValues) {
        this.healthValues = healthValues;
    }

    public ManaValues getManaValues() {
        return manaValues;
    }

    public void setManaValues(ManaValues manaValues) {
        this.manaValues = manaValues;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Resistances getResistances() {
        return resistances;
    }

    public void setResistances(Resistances resistances) {
        this.resistances = resistances;
    }

    public StatusConditions getStatusConditions() {
        return statusConditions;
    }

    public void setStatusConditions(StatusConditions statusConditions) {
        this.statusConditions = statusConditions;
    }

    public Collection<? extends Ability> getJobAbilities() {
        return jobAbilities;
    }

    public void setJobAbilities(ArrayList<Ability> jobAbilities) {
        this.jobAbilities = jobAbilities;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", healthValues=" + healthValues +
                ", manaValues=" + manaValues +
                ", attributes=" + attributes +
                ", resistances=" + resistances +
                '}';
    }
}

