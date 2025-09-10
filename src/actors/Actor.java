package actors;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.StatusConditions;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.stances.Stances;
import utils.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class Actor {
//    public int actionPoints;
    private String name;
    private ActorTypes actorType;
    private HealthValues healthValues;
    private ManaValues manaValues;
    private Attributes attributes;
    private Resistances resistances;
    private StatusConditions statusConditions;
    private Stances stance;
    private boolean isWet;

    public Actor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                 Resistances resistances, StatusConditions statusConditions) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = statusConditions;
        new ArrayList<>();
        this.isWet = false;
    }

    public Actor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
                 Resistances resistances) {
        this.name = name;
        this.healthValues = healthValues;
        this.manaValues = manaValues;
        this.attributes = attributes;
        this.resistances = resistances;
        this.statusConditions = new StatusConditions();
        this.isWet = false;
    }

    public ActorTypes getActorType() {
        return actorType;
    }

    public HealthValues getHealthValues() {
        return healthValues;
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

    public String getName() {
        return this.name;
    }

    public void setHealthValues(HealthValues healthValues) {
        this.healthValues = healthValues;
    }

    public void setActorType(ActorTypes actorType) {
        this.actorType = actorType;
    }

    public void setStance(Stances stance) {
        this.stance = stance;
    }

    public void attack(Actor attacker, Actor target, Ability ability) {
        target.takeDamage(attacker, ability);
    }

    private boolean startsWithVowel(String name) {
        char firstChar = Character.toUpperCase(name.charAt(0));
        return firstChar == 'A' || firstChar == 'E' || firstChar == 'I' || firstChar == 'O' || firstChar == 'U';
    }

    private void appendDamageMessage(StringBuilder damageMessage, Actor attacker, Ability ability, Damage damage, int totalDamage, boolean isFirstDamage) {
        String article = startsWithVowel(ability.getName()) ? "an " : "a ";

        if (isFirstDamage) {
            damageMessage.append(this.getName())
                    .append(" was hit by ")
                    .append(article)
                    .append(ability.getName())
                    .append(" from ")
                    .append(attacker.getName())
                    .append(" and received: | ")
                    .append(totalDamage)
                    .append(" ")
                    .append(damage.getDamageClassification())
                    .append(" ")
                    .append(damage.getDamageType())
                    .append(" damage | ");
        } else {
            damageMessage.append(totalDamage)
                    .append(" ")
                    .append(damage.getDamageClassification())
                    .append(" ")
                    .append(damage.getDamageType())
                    .append(" damage | ");
        }
    }


    private int calculateMitigation(Actor attacker, Ability ability, Damage damage, int resistance) {
        int damageToMitigate = resistance;
        if (stance != null) {
            if (stance.equals(Stances.DEFENDING) &&
                    damage.getDamageClassification().equals(DamageClassificationTypes.PHYSICAL)) {
                damageToMitigate += (int) Math.floor((double) this.attributes.getDefense().getValue() / 2) + resistance;
            }

            if (stance.equals(Stances.PARRYING) &&
                    damage.getDamageClassification().equals(DamageClassificationTypes.PHYSICAL)) {
                Random random = new Random();
                int parryRoll = random.nextInt(this.attributes.getAgility().getValue());
                int attackToParryAgainstRoll = random.nextInt(attacker.attributes.getLuck().getValue()) +
                        attacker.attributes.getStrength().getValue() * 2;
                boolean rollToParry = parryRoll > attackToParryAgainstRoll;

                if (rollToParry) damageToMitigate += 99999;
                else System.out.println(this.name + " failed to parry the " +
                        attacker.name + "'s " + ability.getName() + ".");
            }
        }

        return damageToMitigate;
    }

    public boolean canUseAbility(Ability ability) {
        return ability.getManaCost() <= this.getManaValues().getValue();
    }

    private int getDamageToAdd(Actor attacker, Damage damage) {
        int damageToAdd = 0;

//        abilities.reactions.Reaction[] charReactions = {
//                new abilities.reactions.Reaction("Defend", 1, "Reduce damage from physical attacks, based on defense."),
//                new abilities.reactions.Reaction("Parry", 1, "Chance to prevent physical damage, based on defense."),
//                new abilities.reactions.Reaction("items.Item", 1, "Utilize an item to restore, buff, or remove conditions."),
//                new abilities.reactions.Reaction("Observe", 1, "Observe your surroundings to determine everything is relative to you."),
//                new abilities.reactions.Reaction("Pass", 0, "Conserve or recuperate."),
//        };

        if (Objects.equals(damage.getDamageClassification(), DamageClassificationTypes.PHYSICAL)) {
            damageToAdd += (int) Math.floor((double) attacker.attributes.getStrength().getValue() / 2);
        }

        if (Objects.equals(damage.getDamageClassification(), DamageClassificationTypes.MAGICAL)) {
            damageToAdd += (int) Math.floor((double) attacker.attributes.getKnowledge().getValue() / 2);
        }

        if (Objects.equals(damage.getDamageClassification(), DamageClassificationTypes.SPIRITUAL)) {
            damageToAdd += (int) Math.floor((double) attacker.attributes.getSpirit().getValue() / 2);
        }
        return damageToAdd;
    }

    public int getHealth() {
        return healthValues.getValue();
    }

    public int getInitiative() {
        Random random = new Random();
        return random.nextInt(this.attributes.getAgility().getValue());
    }

    public void preventOverloadingResourceValues() {
        HealthValues healthValues = this.getHealthValues();
        ManaValues manaValues = this.getManaValues();

        if(healthValues.getValue() > healthValues.getMaxValue()) {
            healthValues.setValue(healthValues.getMaxValue());
        }
        if(manaValues.getValue() > manaValues.getMaxValue()) {
            manaValues.setValue(manaValues.getMaxValue());
        }
    }

    public void spendMana(Ability ability) {
        if(canUseAbility(ability)) {
            this.getManaValues().setValue(
                    this.getManaValues().getValue() -
                    ability.getManaCost()
            );
            System.out.println(this.getManaValues().getValue() + " / " +
                    this.getManaValues().getMaxValue() + " Mana Remaining");
        }
    }

    public void takeDamage(StatusCondition statusCondition) {
        int damageToMitigate = 0;

        switch(statusCondition.getName()) {
            case BURN -> damageToMitigate = this.resistances.getFire().getValue();
            case ENVENOM -> damageToMitigate = this.resistances.getVenom().getValue();
            case POISON -> damageToMitigate = this.resistances.getEarth().getValue();
            case ROT -> damageToMitigate = this.resistances.getDarkness().getValue();
            default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());

        }

        int baseDamage = statusCondition.getValue();
        int totalDamage = Math.max(baseDamage - damageToMitigate, 0);
        healthValues.setValue(healthValues.getValue() - totalDamage);

        switch(statusCondition.getName()) {
            case BLEED -> System.out.println(name + " is bleeding for " + totalDamage + " unmitigated damage due to the bleed condition.");
            case BURN -> System.out.println(name + " is burning for " + totalDamage + " fire damage due to the burn condition.");
            case ENVENOM -> System.out.println(name + " is agonizing " + totalDamage + " venom damage due to the envenom condition.");
            case POISON -> System.out.println(name + " is suffering " + totalDamage + " poison damage due to the poison condition.");
            case ROT -> System.out.println(name + " is deteriorating for " + totalDamage + " darkness damage due to the rot condition.");
            default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
        }


    }

    public void takeDamage(Actor attacker, Ability ability) {
        StringBuilder damageMessage = new StringBuilder();

        for (Damage damage : ability.getDamages()) {
            int damageToAdd = getDamageToAdd(attacker, damage);
            boolean isFirstDamage = damage == ability.getDamages()[0];

            int resistanceValue = this.resistances.getResistance(damage.getDamageType());
            int damageToMitigate = calculateMitigation(attacker, ability, damage, resistanceValue);

            int baseDamage = (int) (Math.floor(Math.random() * (damage.getMaxDamage() - damage.getMinDamage()))
                    + damage.getMinDamage());

            int totalDamage = Math.max(baseDamage - damageToMitigate + damageToAdd, 0);
            healthValues.setValue(healthValues.getValue() - totalDamage);

            appendDamageMessage(damageMessage, attacker, ability, damage, totalDamage, isFirstDamage);
            applyStatusCondition(attacker, damage);
        }

        StringUtils.stringDividerTop(damageMessage.toString(), "=", 50);
    }

    public void applyStatusCondition(Actor attacker, Damage damage) {
        for(StatusCondition status : damage.getStatusConditions()) {
            Random random = new Random();
            boolean triggerStatusCondition = status.getChanceToTrigger() > random.nextInt(1, 100);

            StatusCondition currStatus = this.getStatusConditions().getStatus(status.getName());

            int attackerRoll = random.nextInt(Math.max(1, attacker.getAttributes().getLuck().getValue()));
            int targetRoll = random.nextInt(
                    Math.max(1, this.getAttributes().getResilience().getValue() * 2 +
                            currStatus.getResistance())
            );

            if(triggerStatusCondition && attackerRoll > targetRoll) {
                System.out.println(this.getName() + statusConditions.getStatusAffectedText(status.getName()));
                currStatus.setValue(status.getValue());
                currStatus.setDuration(status.getDuration());
            }
        }
    }

    public void handleStatusConditions() {
        // List of status condition getters
        String[] allStatusMethods = {
                "getBleed", "getBlind", "getBurn", "getConfused", "getDry", "getPoison", "getEnvenom",
                "getFear", "getFreeze", "getManipulate", "getParalyze", "getRot", "getSick", "getSlow",
                "getWeak", "getWet",
        };

        String[] dotMethods = {
                "getBleed", "getBurn", "getEnvenom", "getPoison", "getRot"
        };

        String[] debuffMethods = {
                "getEnvenom", "getPoison", "getRot", "getSick", "getSlow", "getWeak",
        };

        // Work on wet/dry logic and conditionally set relatable conditions to only trigger when wet or dry

        for (String methodName : allStatusMethods) {
            try {
                Method method = StatusConditions.class.getMethod(methodName);
                StatusCondition statusCondition = (StatusCondition) method.invoke(statusConditions);

                if (statusCondition.getDuration() > 0) {
                    statusCondition.setDuration(statusCondition.getDuration() - 1);
                } else {
                    statusCondition.setValue(0);
                }
            } catch (Exception e) {
                System.out.println("Error with all methods");
            }
        }

        for (String methodName : dotMethods) {
            try {
                Method method = StatusConditions.class.getMethod(methodName);
                StatusCondition statusCondition = (StatusCondition) method.invoke(statusConditions);

                if (statusCondition.getDuration() > 0) {
                    takeDamage(statusCondition);
                }
            } catch (Exception e) {
                System.out.println("Error with DoT Method");
            }
        }

        for (String methodName : debuffMethods) {
            try {
                Method method = StatusConditions.class.getMethod(methodName);
                StatusCondition statusCondition = (StatusCondition) method.invoke(statusConditions);

                if (statusCondition.getDuration() > 0) {
                    switch(statusCondition.getName()) {
                        case ENVENOM, WEAK -> {
                            attributes.getStrength().setValue(
                                    attributes.getStrength().getValue() - statusCondition.getValue()
                            );
                            System.out.println(this.getName() + " is weakened.");
                        }
                        case POISON, SLOW -> {
                            attributes.getAgility().setValue(
                                    attributes.getAgility().getValue() - statusCondition.getValue()
                            );
                            System.out.println(this.getName() + " is feeling slow.");
                        }
                        case ROT -> {
                            resistances.getBludgeoning().setValue(
                                    resistances.getBludgeoning().getValue() - statusCondition.getValue()
                            );
                            resistances.getPiercing().setValue(
                                    resistances.getPiercing().getValue() - statusCondition.getValue()
                            );
                            resistances.getSlashing().setValue(
                                    resistances.getSlashing().getValue() - statusCondition.getValue()
                            );
                            System.out.println(this.getName() + " is deteriorating.");
                        }
                        case SICK -> {
                            attributes.getStrength().setValue(
                                    attributes.getStrength().getValue() - statusCondition.getValue()
                            );
                            attributes.getAgility().setValue(
                                    attributes.getAgility().getValue() - statusCondition.getValue()
                            );
                            System.out.println(this.getName() + " is feeling ill.");
                        }
                        default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
                    }

                    statusCondition.setTotalAdjustment(
                            statusCondition.getTotalAdjustment() + statusCondition.getValue()
                    );
                } else if (statusCondition.getTotalAdjustment() > 0 && statusCondition.getDuration() <= 0) {
                    switch(statusCondition.getName()) {
                        case ENVENOM, WEAK -> {
                            attributes.getStrength().setValue(
                                    attributes.getStrength().getValue() + statusCondition.getTotalAdjustment()
                            );
                            System.out.println(this.getName() + " has regained their strength.");
                        }
                        case POISON, SLOW -> {
                            attributes.getAgility().setValue(
                                    attributes.getAgility().getValue() + statusCondition.getTotalAdjustment()
                            );
                            System.out.println(this.getName() + " can maneuver normally.");
                        }
                        case ROT -> {
                            resistances.getBludgeoning().setValue(
                                    resistances.getBludgeoning().getValue() + statusCondition.getTotalAdjustment()
                            );
                            resistances.getPiercing().setValue(
                                    resistances.getPiercing().getValue() + statusCondition.getTotalAdjustment()
                            );
                            resistances.getSlashing().setValue(
                                    resistances.getSlashing().getValue() + statusCondition.getTotalAdjustment()
                            );
                            System.out.println(this.getName() + " is no longer deteriorating.");
                        }
                        case SICK -> {
                            attributes.getStrength().setValue(
                                    attributes.getStrength().getValue() + statusCondition.getTotalAdjustment()
                            );
                            attributes.getAgility().setValue(
                                    attributes.getAgility().getValue() + statusCondition.getTotalAdjustment()
                            );
                            System.out.println(this.getName() + " has recovered from their sickness.");
                        }
                        default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
                    }
                    statusCondition.setTotalAdjustment(0);
                }
            } catch (Exception e) {
                System.out.println("Error with Debuff Method");
            }
        }

//        if(statusConditions.getBlind().getDuration() > 0) {
//            statusConditions.getBlind().setDuration(statusConditions.getBlind().getDuration() - 1);
//            System.out.println(this.getName() + " has their vision obscured and is struggling to see.");
//        } else {
//            statusConditions.getBlind().setValue(0);
//        }

//        if(statusConditions.getRot().getDuration() > 0) {
//            statusConditions.getRot().setDuration(statusConditions.getRot().getDuration() - 1);
//            resistances.getBludgeoning().setValue(
//                    resistances.getBludgeoning().getValue() - statusConditions.getRot().getValue()
//            );
//            statusConditions.getRot().setTotalAdjustment(
//                    statusConditions.getRot().getTotalAdjustment() + statusConditions.getRot().getValue()
//            );
//            System.out.println(this.getName() + " is slowly deteriorating.");
//        } else {
//            statusConditions.getRot().setValue(0);
//            resistances.getBludgeoning().setValue(
//                    resistances.getBludgeoning().getValue() + statusConditions.getRot().getTotalAdjustment()
//            );
//            statusConditions.getRot().setTotalAdjustment(0);
//        }
    }

    public StatusConditions getStatusConditions() {
        return statusConditions;
    }

    public boolean isWet() {
        return isWet;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", actorType='" + actorType + '\'' +
                ", healthValues=" + healthValues +
                ", manaValues=" + manaValues +
                ", attributes=" + attributes +
                ", resistances=" + resistances +
                ", statusConditions=" + statusConditions +
                ", stance='" + stance + '\'' +
                '}';
    }
}

