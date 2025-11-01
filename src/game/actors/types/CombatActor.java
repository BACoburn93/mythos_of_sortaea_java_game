package actors.types;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import actors.Actor;
import actors.ActorTypes;
import actors.attributes.Attributes;
import actors.managers.StatusManager;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.species.SpeciesBuffApplier;
import actors.species.SpeciesCategory;
import actors.species.SpeciesType;
import actors.stances.Stances;
import interfaces.ActorInterface;
import status_conditions.StatusCondition;
import status_conditions.StatusConditions;
import ui.CombatUIStrings;
import utils.StringUtils;

public class CombatActor extends Actor {
    private ActorTypes actorType;
    
    private HealthValues healthValues;
    private ManaValues manaValues;
    private Attributes attributes;
    private Resistances resistances;
    private StatusConditions statusConditions;
    private Stances stance;
    protected int level;

    // allow multiple species/subspecies per actor
    private final Set<SpeciesType> speciesTypes = new HashSet<>();

    public CombatActor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                    Resistances resistances, StatusConditions statusConditions, int level) {
        super(name, ActorTypes.COMBAT); 
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = statusConditions;
        this.level = level;
    }

    public CombatActor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                    Resistances resistances, StatusConditions statusConditions) {
        super(name, ActorTypes.COMBAT); 
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = statusConditions;
        this.level = 1;
    }

    public CombatActor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                       Resistances resistances, int level) {
        this(name, healthValues, manaValues, attributes, resistances, new StatusConditions(), level);
    }

    public CombatActor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
            Resistances resistances) {
        super(name, ActorTypes.COMBAT);
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = new StatusConditions();
        this.level = 1;
    }

    // Getters and Setters
    public String getName() {
        return super.getName();
    }

    public ActorTypes getActorType() {
        return actorType;
    }

    public void setActorType(ActorTypes actorType) {
        this.actorType = actorType;
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

    public Attributes getAttributes() {
        return attributes;
    }

    public Resistances getResistances() {
        return resistances;
    }

    public Stances getStance() {
        return stance;
    }

    public void setStance(Stances stance) {
        this.stance = stance;
    }

    public StatusConditions getStatusConditions() {
        return statusConditions;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHealth() {
        return healthValues.getValue();
    }

    public int getInitiative() {
        return new Random().nextInt(Math.max(1, (int) attributes.getAgility().getValue()));
    }

    public boolean canUseAbility(Ability ability) {
        return ability.getManaCost() <= this.getManaValues().getValue();
    }

    public void spendMana(Ability ability) {
        if (canUseAbility(ability)) {
            manaValues.setValue(manaValues.getValue() - ability.getManaCost());
            System.out.println(StringUtils.formatInt(manaValues.getValue()) + " / " + StringUtils.formatInt(manaValues.getMaxValue()) + " Mana Remaining");
        }
    }

    public void preventOverloadingResourceValues() {
        if (healthValues.getValue() > healthValues.getMaxValue()) {
            healthValues.setValue(healthValues.getMaxValue());
        }
        if (manaValues.getValue() > manaValues.getMaxValue()) {
            manaValues.setValue(manaValues.getMaxValue());
        }
    }

    private int getDamageToAdd(CombatActor attacker, Damage damage) {
        int damageToAdd = 0;

        if (damage.getDamageClassification() == DamageClassificationTypes.PHYSICAL) {
            damageToAdd += (int) (attacker.attributes.getStrength().getValue() / 2.0);
        }
        if (damage.getDamageClassification() == DamageClassificationTypes.MAGICAL) {
            damageToAdd += (int) (attacker.attributes.getKnowledge().getValue() / 2.0);
        }
        if (damage.getDamageClassification() == DamageClassificationTypes.SPIRITUAL) {
            damageToAdd += (int) (attacker.attributes.getSpirit().getValue() / 2.0);
        }

        return damageToAdd;
    }

    private int calculateMitigation(ActorInterface attacker, Ability ability, Damage damage, int resistance) {
        int damageToMitigate = resistance;

        if (stance != null && stance == Stances.DEFENDING &&
                damage.getDamageClassification() == DamageClassificationTypes.PHYSICAL) {
            damageToMitigate += (int) (attributes.getDefense().getValue() / 2.0) + resistance;
        }

        if (stance == Stances.PARRYING &&
                damage.getDamageClassification() == DamageClassificationTypes.PHYSICAL) {
            Random random = new Random();
            int parryRoll = random.nextInt(Math.max(1, (int) attributes.getAgility().getValue()));
            int attackRoll = random.nextInt(Math.max(1, (int) attributes.getLuck().getValue())) + (int) attributes.getStrength().getValue() * 2;

            if (parryRoll > attackRoll) {
                damageToMitigate += 99999;
            } else {
                System.out.println(super.getName() + " failed to parry the " + attacker.getName() + "'s " + ability.getName() + ".");
            }
        }

        return damageToMitigate;
    }

    public void attack(CombatActor target, Ability ability) {
        target.takeDamage(this, ability);
    }

    public void takeDamage(CombatActor attacker, Ability ability) {
        if(this.getHealthValues().getValue() <= 0) {
            System.out.println(super.getName() + " is already dead and cannot take more damage.");
            return;
        }
        
        StringBuilder damageMessage = new StringBuilder();

        for (Damage damage : ability.getDamages()) {
            int damageToAdd = getDamageToAdd(attacker, damage);
            boolean isFirstDamage = damage == ability.getDamages()[0];

            int resistanceValue = resistances.getResistance(damage.getDamageType()).intValue();
            int damageToMitigate = calculateMitigation(attacker, ability, damage, resistanceValue);

            int baseDamage = (int) (Math.floor(Math.random() * (damage.getMaxDamage() - damage.getMinDamage()))
                    + damage.getMinDamage());

            double speciesDamageBonus = ability.getSpeciesMultiplierFor(this.getSpeciesTypes());
            int totalDamage = (int) Math.max((baseDamage - damageToMitigate + damageToAdd) * speciesDamageBonus, 0);
            healthValues.setValue(Math.max(healthValues.getValue() - totalDamage, 0));

            CombatUIStrings.appendDamageMessage(damageMessage, attacker, this, ability, damage, totalDamage, isFirstDamage);
            applyStatusCondition(attacker, damage);
        }

        StringUtils.stringDividerTop(damageMessage.toString(), "", 50);
    }

    public void applyStatusCondition(ActorInterface attacker, Damage damage) {
        if (!(attacker instanceof CombatActor a)) {
            throw new IllegalArgumentException("Attacker must be a CombatActor.");
        }

        StatusManager.getInstance().applyOnHit(a, this, damage);
    }

    public void takeDamage(StatusCondition statusCondition) {
        int mitigation = switch (statusCondition.getName()) {
            case BURN -> (int) resistances.getFire().getValue();
            case ENVENOM -> (int) resistances.getVenom().getValue();
            case POISON -> (int) resistances.getEarth().getValue();
            case ROT -> (int) resistances.getDarkness().getValue();
            default -> 0;
        };

        int base = statusCondition.getValue();
        int total = Math.max(base - mitigation, 0);
        healthValues.setValue(healthValues.getValue() - total);

        switch (statusCondition.getName()) {
            case BLEED -> System.out.println(super.getName() + " is bleeding for " + total + " unmitigated damage.");
            case BURN -> System.out.println(super.getName() + " is burning for " + total + " fire damage.");
            case ENVENOM -> System.out.println(super.getName() + " takes " + total + " venom damage.");
            case POISON -> System.out.println(super.getName() + " takes " + total + " poison damage.");
            case ROT -> System.out.println(super.getName() + " is rotting for " + total + " darkness damage.");
            default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
        }
    }

    public void handleStartTurn() {
        StringUtils.stringDivider(super.getName() + "'s turn has started.", "=", 50);

        if (healthValues.getRegenValue() > 0) {
            healthValues.setValue(healthValues.getValue() + healthValues.getRegenValue());
        }

        if (manaValues.getRegenValue() > 0) {
            manaValues.setValue(manaValues.getValue() + manaValues.getRegenValue());
        }

        StatusManager.getInstance().handleStartTurn(this);

        preventOverloadingResourceValues();
    }

    public void handleEndTurn() {
        StatusManager.getInstance().handleEndTurn(this);
        StringUtils.stringDivider(super.getName() + "'s turn has ended.", "=", 50);
    }

    // convenience: ensure at least a HUMANOID entry if you want a default
    public void ensureDefaultSpecies() {
        if (speciesTypes.isEmpty()) {
            speciesTypes.add(new SpeciesType(SpeciesCategory.HUMANOID));
        }
    }

    public Set<SpeciesType> getSpeciesTypes() {
        return Collections.unmodifiableSet(speciesTypes);
    }

    public boolean addSpecies(SpeciesType st) {
        if (st == null) return false;
        SpeciesBuffApplier.applySpeciesBuff(this, st);
        return speciesTypes.add(st);
    }

    public boolean removeSpecies(SpeciesType st) {
        if (st == null) return false;
        return speciesTypes.remove(st);
    }

    public boolean hasSpecies(SpeciesCategory cat) {
        return speciesTypes.stream().anyMatch(s -> s.getSpecies() == cat);
    }

    public boolean hasSubspecies(String sub) {
        if (sub == null || sub.isBlank()) return false;
        String subU = sub.trim();
        return speciesTypes.stream().anyMatch(s -> subU.equalsIgnoreCase(s.getSubSpecies()));
    }

    @Override
    public String toString() {
        return "CombatActor{" +
                "name='" + super.getName() + '\'' +
                ", actorType=" + actorType +
                ", healthValues=" + healthValues +
                ", manaValues=" + manaValues +
                ", attributes=" + attributes +
                ", resistances=" + resistances +
                ", statusConditions=" + statusConditions +
                ", stance=" + stance +
                '}';
    }
}
