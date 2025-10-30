package characters.jobs;

import abilities.Ability;
import abilities.AbilityPool;
import abilities.AbilityPoolRegistry;
import abilities.damages.*;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.AccessoryTypes;
import status_conditions.StatusConditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public abstract class Job implements DamageTypeProvider {
    private String name;
    private HealthValues healthValues;
    private ManaValues manaValues;
    private Attributes attributes;
    private Resistances resistances;

    // Need to bind status conditions to jobs so that they affect character statistics
    // private StatusConditions statusConditions;
    
    private ArrayList<Ability> jobAbilities;
    private Set<ItemType> equippableItemTypes;
    private double unarmedDamage;
    private AttributeTypes unarmedDamageAttr;
    private int hits;

    private static Set<ItemType> defaultEquipmentProficiencies = Set.of(
            AccessoryTypes.RING,
            AccessoryTypes.NECK,
            AccessoryTypes.WRIST
    );

    protected abstract JobTypes getJobType();


    public Job(String name, HealthValues healthValues, ManaValues manaValues,
               Attributes attributes, Resistances resistances, ArrayList<Ability> jobAbilities, 
               Set<ItemType> equippableItemTypes, double unarmedDamage) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        // this.statusConditions = new StatusConditions();
        this.jobAbilities = jobAbilities;
        this.equippableItemTypes = new java.util.HashSet<>(defaultEquipmentProficiencies);
        if (equippableItemTypes != null) this.equippableItemTypes.addAll(equippableItemTypes);
        this.unarmedDamage = unarmedDamage;
        this.unarmedDamageAttr = AttributeTypes.STRENGTH;
        this.hits = 1;
    }
 
    public Job(String name, HealthValues healthValues, ManaValues manaValues,
               Attributes attributes, Resistances resistances, 
               StatusConditions statusConditions, ArrayList<Ability> jobAbilities,
               Set<ItemType> equippableItemTypes, double unarmedDamage) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        // this.statusConditions = statusConditions;
        this.jobAbilities = jobAbilities;
        this.equippableItemTypes = new java.util.HashSet<>(defaultEquipmentProficiencies);
        if (equippableItemTypes != null) this.equippableItemTypes.addAll(equippableItemTypes);
        this.unarmedDamage = unarmedDamage;
        this.unarmedDamageAttr = AttributeTypes.STRENGTH;
        this.hits = 1;
    }
 
    public Job(String name, HealthValues healthValues, ManaValues manaValues,
               Attributes attributes, Resistances resistances, 
               StatusConditions statusConditions, ArrayList<Ability> jobAbilities,
               Set<ItemType> equippableItemTypes, double unarmedDamage, String unarmedDamageAttr) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        // this.statusConditions = statusConditions;
        this.jobAbilities = jobAbilities;
        this.equippableItemTypes = new java.util.HashSet<>(defaultEquipmentProficiencies);
        if (equippableItemTypes != null) this.equippableItemTypes.addAll(equippableItemTypes);
        this.unarmedDamage = unarmedDamage;
        this.unarmedDamageAttr = AttributeTypes.STRENGTH;
        this.hits = 1;
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

    // public StatusConditions getStatusConditions() {
    //     return statusConditions;
    // }

    // public void setStatusConditions(StatusConditions statusConditions) {
    //     this.statusConditions = statusConditions;
    // }

    public double getUnarmedDamage() {
        return unarmedDamage;
    }

    public AttributeTypes getUnarmedDamageAttr() {
        return unarmedDamageAttr;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public Collection<? extends Ability> getJobAbilities() {
        return jobAbilities;
    }

    public void setJobAbilities(ArrayList<Ability> jobAbilities) {
        this.jobAbilities = jobAbilities;
    }

    public Set<ItemType> getEquippableItemTypes() {
        return equippableItemTypes;
    }

    public void setEquippableItemTypes(Set<ItemType> equippableItemTypes) {
        this.equippableItemTypes = equippableItemTypes;
    }

    public AbilityPool getAbilityPool() {
        return AbilityPoolRegistry.getPool(this.getJobType());
    }

    public List<Ability> getPoolAbilities() {
        AbilityPool pool = getAbilityPool();
        return pool != null ? pool.getAbilities() : new ArrayList<>(List.of());
    }

    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
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

