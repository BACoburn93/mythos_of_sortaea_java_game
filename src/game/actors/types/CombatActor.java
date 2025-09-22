package actors.types;

import java.util.Random;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import actors.Actor;
import actors.ActorTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.stances.Stances;
import interfaces.ActorInterface;
import status_conditions.DamageOverTime;
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

    public CombatActor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                    Resistances resistances, StatusConditions statusConditions) {
        super(name, ActorTypes.COMBAT); 
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = statusConditions;
    }

    public CombatActor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                       Resistances resistances) {
        this(name, healthValues, manaValues, attributes, resistances, new StatusConditions());
    }

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

    public int getHealth() {
        return healthValues.getValue();
    }

    public int getInitiative() {
        return new Random().nextInt(this.attributes.getAgility().getValue());
    }

    public boolean canUseAbility(Ability ability) {
        return ability.getManaCost() <= this.getManaValues().getValue();
    }

    public void spendMana(Ability ability) {
        if (canUseAbility(ability)) {
            manaValues.setValue(manaValues.getValue() - ability.getManaCost());
            System.out.println(manaValues.getValue() + " / " + manaValues.getMaxValue() + " Mana Remaining");
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

        // if (!(attacker instanceof CombatActor ca)) return 0;

        if (damage.getDamageClassification() == DamageClassificationTypes.PHYSICAL) {
            damageToAdd += attacker.attributes.getStrength().getValue() / 2;
        }
        if (damage.getDamageClassification() == DamageClassificationTypes.MAGICAL) {
            damageToAdd += attacker.attributes.getKnowledge().getValue() / 2;
        }
        if (damage.getDamageClassification() == DamageClassificationTypes.SPIRITUAL) {
            damageToAdd += attacker.attributes.getSpirit().getValue() / 2;
        }

        return damageToAdd;
    }

    private int calculateMitigation(ActorInterface attacker, Ability ability, Damage damage, int resistance) {
        int damageToMitigate = resistance;

        if (stance != null && stance == Stances.DEFENDING &&
                damage.getDamageClassification() == DamageClassificationTypes.PHYSICAL) {
            damageToMitigate += attributes.getDefense().getValue() / 2 + resistance;
        }

        if (stance == Stances.PARRYING &&
                damage.getDamageClassification() == DamageClassificationTypes.PHYSICAL) {
            Random random = new Random();
            int parryRoll = random.nextInt(attributes.getAgility().getValue());
            int attackRoll = random.nextInt(this.attributes.getLuck().getValue()) + this.attributes.getStrength().getValue() * 2;

            if (parryRoll > attackRoll) {
                damageToMitigate += 99999;
            } else {
                System.out.println(super.getName() + " failed to parry the " + attacker.getName() + "'s " + ability.getName() + ".");
            }
        }

        return damageToMitigate;
    }

    // public void attack(CombatActor attacker, CombatActor target, Ability ability) {
    //         target.takeDamage(attacker, ability);

    // }

    public void takeDamage(CombatActor attacker, Ability ability) {

        StringBuilder damageMessage = new StringBuilder();

        for (Damage damage : ability.getDamages()) {
            int damageToAdd = getDamageToAdd(attacker, damage);
            boolean isFirstDamage = damage == ability.getDamages()[0];

            int resistanceValue = resistances.getResistance(damage.getDamageType());
            int damageToMitigate = calculateMitigation(attacker, ability, damage, resistanceValue);

            int baseDamage = (int) (Math.floor(Math.random() * (damage.getMaxDamage() - damage.getMinDamage()))
                    + damage.getMinDamage());

            int totalDamage = Math.max(baseDamage - damageToMitigate + damageToAdd, 0);
            healthValues.setValue(healthValues.getValue() - totalDamage);

            CombatUIStrings.appendDamageMessage(damageMessage, attacker, this, ability, damage, totalDamage, isFirstDamage);
            applyStatusCondition(attacker, damage);
        }

        StringUtils.stringDividerTop(damageMessage.toString(), "=", 50);
    }

    public void applyStatusCondition(ActorInterface attacker, Damage damage) {
        if (!(attacker instanceof CombatActor a)) {
            throw new IllegalArgumentException("Attacker must be a CombatActor.");
        }

        for (StatusCondition status : damage.getStatusConditions()) {
            Random random = new Random();
            int roll = random.nextInt(1, 101);
            boolean trigger = status.getChanceToTrigger() >= roll;

            StatusCondition currStatus = statusConditions.getStatus(status.getName());

            int attackerRoll = random.nextInt(Math.max(1, a.attributes.getLuck().getValue()));
            int targetRoll = random.nextInt(
                    Math.max(1, attributes.getResilience().getValue() * 2 + currStatus.getResistance())
            );

            if (trigger && attackerRoll > targetRoll) {
                System.out.println(super.getName() + statusConditions.getStatusAffectedText(status.getName()));
                currStatus.setValue(status.getValue());
                currStatus.setDuration(status.getDuration());
            }
        }
    }

    
    public void attack(CombatActor attacker, CombatActor target, Ability ability) {
        target.takeDamage(attacker, ability);
    }

    public void takeDamage(StatusCondition statusCondition) {
        int mitigation = switch (statusCondition.getName()) {
            case BURN -> resistances.getFire().getValue();
            case ENVENOM -> resistances.getVenom().getValue();
            case POISON -> resistances.getEarth().getValue();
            case ROT -> resistances.getDarkness().getValue();
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

    public void handleStatusConditions() {
        for (StatusCondition condition : statusConditions.getAll()) {
            if (condition.getDuration() > 0) {
                condition.setDuration(condition.getDuration() - 1);
                if (condition instanceof DamageOverTime dot) {
                    dot.applyDamage(this);
                } else {
                    condition.applyEffect(this);
                }
            } else {
                condition.endEffect(this);
            }
        }
    }

    public void handleStartTurn() {
        System.out.println("=".repeat(50));
        System.out.println(super.getName() + "'s turn has started.");
        System.out.println("=".repeat(50));

        if (healthValues.getRegenValue() > 0) {
            healthValues.setValue(healthValues.getValue() + healthValues.getRegenValue());
        }

        if (manaValues.getRegenValue() > 0) {
            manaValues.setValue(manaValues.getValue() + manaValues.getRegenValue());
        }

        preventOverloadingResourceValues();
    }

    public void handleEndTurn() {
        System.out.println("=".repeat(50));
        handleStatusConditions();
        System.out.println(super.getName() + "'s turn has ended.");
        System.out.println("=".repeat(50));
    }

    public StatusConditions getStatusConditions() {
        return statusConditions;
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
