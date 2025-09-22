package actors;

import interfaces.ActorInterface;
import interfaces.Nameable;

public abstract class Actor implements ActorInterface, Nameable {
    private String name;
    private ActorTypes actorType;

    public Actor(String name, ActorTypes actorType) {
        this.name = name;
        this.actorType = actorType;
    }

    public String getName() {
        return name;
    }

    public ActorTypes getActorType() {
        return actorType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActorType(ActorTypes actorType) {
        this.actorType = actorType;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", actorType=" + actorType +
                '}';
    }
}




// package actors;

// import abilities.Ability;
// import abilities.damages.Damage;
// import abilities.damages.DamageClassificationTypes;
// import actors.attributes.Attributes;
// import actors.resistances.Resistances;
// import actors.resources.HealthValues;
// import actors.resources.ManaValues;
// import actors.stances.Stances;
// import interfaces.ActorInterface;
// import interfaces.Nameable;
// import status_conditions.DamageOverTime;
// import status_conditions.StatusCondition;
// import status_conditions.StatusConditions;
// import utils.StringUtils;
// import ui.CombatUIStrings;

// import java.util.ArrayList;
// import java.util.Objects;
// import java.util.Random;


// public abstract class Actor implements ActorInterface, Nameable {
//     private String name;
//     private ActorTypes actorType;
//     private HealthValues healthValues;
//     private ManaValues manaValues;
//     private Attributes attributes;
//     private Resistances resistances;
//     private StatusConditions statusConditions;
//     private Stances stance;

//     public Actor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
//                  Resistances resistances, StatusConditions statusConditions) {
//         this.name = name;
//         this.healthValues = healthValues;
//         this.manaValues = manaValues;
//         this.attributes = attributes;
//         this.resistances = resistances;
//         this.statusConditions = statusConditions;
//         new ArrayList<>();
//     }

//     public Actor(String name, HealthValues healthValues, ManaValues manaValues, Attributes attributes,
//                  Resistances resistances) {
//         this.name = name;
//         this.healthValues = healthValues;
//         this.manaValues = manaValues;
//         this.attributes = attributes;
//         this.resistances = resistances;
//         this.statusConditions = new StatusConditions();
//     }

//     public ActorTypes getActorType() {
//         return actorType;
//     }

//     public HealthValues getHealthValues() {
//         return healthValues;
//     }

//     public ManaValues getManaValues() {
//         return manaValues;
//     }

//     public Attributes getAttributes() {
//         return attributes;
//     }

//     public Resistances getResistances() {
//         return resistances;
//     }

//     public Stances getStance() {
//         return stance;
//     }

//     public String getName() {
//         return this.name;
//     }

//     public void setHealthValues(HealthValues healthValues) {
//         this.healthValues = healthValues;
//     }

//     public void setActorType(ActorTypes actorType) {
//         this.actorType = actorType;
//     }

//     public void setStance(Stances stance) {
//         this.stance = stance;
//     }

//     public void attack(Actor attacker, Actor target, Ability ability) {
//         target.takeDamage(attacker, ability);
//     }

//     private int calculateMitigation(Actor attacker, Ability ability, Damage damage, int resistance) {
//         int damageToMitigate = resistance;
//         if (stance != null) {
//             if (stance.equals(Stances.DEFENDING) &&
//                     damage.getDamageClassification().equals(DamageClassificationTypes.PHYSICAL)) {
//                 damageToMitigate += (int) Math.floor((double) this.attributes.getDefense().getValue() / 2) + resistance;
//             }

//             if (stance.equals(Stances.PARRYING) &&
//                     damage.getDamageClassification().equals(DamageClassificationTypes.PHYSICAL)) {
//                 Random random = new Random();
//                 int parryRoll = random.nextInt(this.attributes.getAgility().getValue());
//                 int attackToParryAgainstRoll = random.nextInt(attacker.attributes.getLuck().getValue()) +
//                         attacker.attributes.getStrength().getValue() * 2;
//                 boolean rollToParry = parryRoll > attackToParryAgainstRoll;

//                 if (rollToParry) damageToMitigate += 99999;
//                 else System.out.println(this.name + " failed to parry the " +
//                         attacker.name + "'s " + ability.getName() + ".");
//             }
//         }

//         return damageToMitigate;
//     }

//     public boolean canUseAbility(Ability ability) {
//         return ability.getManaCost() <= this.getManaValues().getValue();
//     }

//     private int getDamageToAdd(Actor attacker, Damage damage) {
//         int damageToAdd = 0;

//         if (Objects.equals(damage.getDamageClassification(), DamageClassificationTypes.PHYSICAL)) {
//             damageToAdd += (int) Math.floor((double) attacker.attributes.getStrength().getValue() / 2);
//         }

//         if (Objects.equals(damage.getDamageClassification(), DamageClassificationTypes.MAGICAL)) {
//             damageToAdd += (int) Math.floor((double) attacker.attributes.getKnowledge().getValue() / 2);
//         }

//         if (Objects.equals(damage.getDamageClassification(), DamageClassificationTypes.SPIRITUAL)) {
//             damageToAdd += (int) Math.floor((double) attacker.attributes.getSpirit().getValue() / 2);
//         }
//         return damageToAdd;
//     }

//     public int getHealth() {
//         return healthValues.getValue();
//     }

//     public int getInitiative() {
//         Random random = new Random();
//         return random.nextInt(this.attributes.getAgility().getValue());
//     }

//     public void preventOverloadingResourceValues() {
//         HealthValues healthValues = this.getHealthValues();
//         ManaValues manaValues = this.getManaValues();

//         if(healthValues.getValue() > healthValues.getMaxValue()) {
//             healthValues.setValue(healthValues.getMaxValue());
//         }
//         if(manaValues.getValue() > manaValues.getMaxValue()) {
//             manaValues.setValue(manaValues.getMaxValue());
//         }
//     }

//     public void spendMana(Ability ability) {
//         if(canUseAbility(ability)) {
//             this.getManaValues().setValue(
//                     this.getManaValues().getValue() -
//                     ability.getManaCost()
//             );
//             System.out.println(this.getManaValues().getValue() + " / " +
//                     this.getManaValues().getMaxValue() + " Mana Remaining");
//         }
//     }

//     public void takeDamage(StatusCondition statusCondition) {
//         int damageToMitigate = 0;

//         switch(statusCondition.getName()) {
//             case BURN -> damageToMitigate = this.resistances.getFire().getValue();
//             case ENVENOM -> damageToMitigate = this.resistances.getVenom().getValue();
//             case POISON -> damageToMitigate = this.resistances.getEarth().getValue();
//             case ROT -> damageToMitigate = this.resistances.getDarkness().getValue();
//             default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());

//         }

//         int baseDamage = statusCondition.getValue();
//         int totalDamage = Math.max(baseDamage - damageToMitigate, 0);
//         healthValues.setValue(healthValues.getValue() - totalDamage);

//         switch(statusCondition.getName()) {
//             case BLEED -> System.out.println(name + " is bleeding for " + totalDamage + " unmitigated damage due to the bleed condition.");
//             case BURN -> System.out.println(name + " is burning for " + totalDamage + " fire damage due to the burn condition.");
//             case ENVENOM -> System.out.println(name + " is agonizing " + totalDamage + " venom damage due to the envenom condition.");
//             case POISON -> System.out.println(name + " is suffering " + totalDamage + " poison damage due to the poison condition.");
//             case ROT -> System.out.println(name + " is deteriorating for " + totalDamage + " darkness damage due to the rot condition.");
//             default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
//         }

//     }

//     public void takeDamage(Actor attacker, Ability ability) {
//         StringBuilder damageMessage = new StringBuilder();

//         for (Damage damage : ability.getDamages()) {
//             int damageToAdd = getDamageToAdd(attacker, damage);
//             boolean isFirstDamage = damage == ability.getDamages()[0];

//             int resistanceValue = this.resistances.getResistance(damage.getDamageType());
//             int damageToMitigate = calculateMitigation(attacker, ability, damage, resistanceValue);

//             int baseDamage = (int) (Math.floor(Math.random() * (damage.getMaxDamage() - damage.getMinDamage()))
//                     + damage.getMinDamage());

//             int totalDamage = Math.max(baseDamage - damageToMitigate + damageToAdd, 0);
//             healthValues.setValue(healthValues.getValue() - totalDamage);

//             CombatUIStrings.appendDamageMessage(damageMessage, attacker, this, ability, damage, totalDamage, isFirstDamage);
//             applyStatusCondition(attacker, damage);
//         }

//         StringUtils.stringDividerTop(damageMessage.toString(), "=", 50);
//     }

//     public void applyStatusCondition(Actor attacker, Damage damage) {
//         for(StatusCondition status : damage.getStatusConditions()) {
//             Random random = new Random();
//             int roll = random.nextInt(1, 101);
//             boolean triggerStatusCondition = status.getChanceToTrigger() >= roll;

//             StatusCondition currStatus = this.getStatusConditions().getStatus(status.getName());

//             int attackerRoll = random.nextInt(Math.max(1, attacker.getAttributes().getLuck().getValue()));
//             int targetRoll = random.nextInt(
//                     Math.max(1, this.getAttributes().getResilience().getValue() * 2 +
//                             currStatus.getResistance())
//             );

//             if(triggerStatusCondition && attackerRoll > targetRoll) {
//                 System.out.println(this.getName() + statusConditions.getStatusAffectedText(status.getName()));
//                 currStatus.setValue(status.getValue());
//                 currStatus.setDuration(status.getDuration());
//             }
//         }
//     }

//     // public void handleStatusConditions() {
//     //     // List of status condition getters
//     //     String[] allStatusMethods = {
//     //             "getBleed", "getBlind", "getBurn", "getConfused", "getDry", "getPoison", "getEnvenom",
//     //             "getFear", "getFreeze", "getManipulate", "getParalyze", "getRot", "getSick", "getSlow",
//     //             "getWeak", "getWet",
//     //     };

//     //     String[] dotMethods = {
//     //             "getBleed", "getBurn", "getEnvenom", "getPoison", "getRot"
//     //     };

//     //     String[] debuffMethods = {
//     //             "getEnvenom", "getPoison", "getRot", "getSick", "getSlow", "getWeak",
//     //     };

//     //     // Work on wet/dry logic and conditionally set relatable conditions to only trigger when wet or dry

//     //     for (String methodName : allStatusMethods) {
//     //         try {
//     //             Method method = StatusConditions.class.getMethod(methodName);
//     //             StatusCondition statusCondition = (StatusCondition) method.invoke(statusConditions);

//     //             if (statusCondition.getDuration() > 0) {
//     //                 statusCondition.setDuration(statusCondition.getDuration() - 1);
//     //             } else {
//     //                 statusCondition.setValue(0);
//     //             }
//     //         } catch (Exception e) {
//     //             System.out.println("Error with all methods");
//     //         }
//     //     }

//     //     for (String methodName : dotMethods) {
//     //         try {
//     //             Method method = StatusConditions.class.getMethod(methodName);
//     //             StatusCondition statusCondition = (StatusCondition) method.invoke(statusConditions);

//     //             if (statusCondition.getDuration() > 0) {
//     //                 takeDamage(statusCondition);
//     //             }
//     //         } catch (Exception e) {
//     //             System.out.println("Error with DoT Method");
//     //         }
//     //     }

//     //     for (String methodName : debuffMethods) {
//     //         try {
//     //             Method method = StatusConditions.class.getMethod(methodName);
//     //             StatusCondition statusCondition = (StatusCondition) method.invoke(statusConditions);

//     //             if (statusCondition.getDuration() > 0) {
//     //                 switch(statusCondition.getName()) {
//     //                     case ENVENOM, WEAK -> {
//     //                         attributes.getStrength().setValue(
//     //                                 attributes.getStrength().getValue() - statusCondition.getValue()
//     //                         );
//     //                         System.out.println(this.getName() + " is weakened.");
//     //                     }
//     //                     case POISON, SLOW -> {
//     //                         attributes.getAgility().setValue(
//     //                                 attributes.getAgility().getValue() - statusCondition.getValue()
//     //                         );
//     //                         System.out.println(this.getName() + " is feeling slow.");
//     //                     }
//     //                     case ROT -> {
//     //                         resistances.getBludgeoning().setValue(
//     //                                 resistances.getBludgeoning().getValue() - statusCondition.getValue()
//     //                         );
//     //                         resistances.getPiercing().setValue(
//     //                                 resistances.getPiercing().getValue() - statusCondition.getValue()
//     //                         );
//     //                         resistances.getSlashing().setValue(
//     //                                 resistances.getSlashing().getValue() - statusCondition.getValue()
//     //                         );
//     //                         System.out.println(this.getName() + " is deteriorating.");
//     //                     }
//     //                     case SICK -> {
//     //                         attributes.getStrength().setValue(
//     //                                 attributes.getStrength().getValue() - statusCondition.getValue()
//     //                         );
//     //                         attributes.getAgility().setValue(
//     //                                 attributes.getAgility().getValue() - statusCondition.getValue()
//     //                         );
//     //                         System.out.println(this.getName() + " is feeling ill.");
//     //                     }
//     //                     default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
//     //                 }

//     //                 statusCondition.setTotalAdjustment(
//     //                         statusCondition.getTotalAdjustment() + statusCondition.getValue()
//     //                 );
//     //             } else if (statusCondition.getTotalAdjustment() > 0 && statusCondition.getDuration() <= 0) {
//     //                 switch(statusCondition.getName()) {
//     //                     case ENVENOM, WEAK -> {
//     //                         attributes.getStrength().setValue(
//     //                                 attributes.getStrength().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         System.out.println(this.getName() + " has regained their strength.");
//     //                     }
//     //                     case POISON, SLOW -> {
//     //                         attributes.getAgility().setValue(
//     //                                 attributes.getAgility().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         System.out.println(this.getName() + " can maneuver normally.");
//     //                     }
//     //                     case ROT -> {
//     //                         resistances.getBludgeoning().setValue(
//     //                                 resistances.getBludgeoning().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         resistances.getPiercing().setValue(
//     //                                 resistances.getPiercing().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         resistances.getSlashing().setValue(
//     //                                 resistances.getSlashing().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         System.out.println(this.getName() + " is no longer deteriorating.");
//     //                     }
//     //                     case SICK -> {
//     //                         attributes.getStrength().setValue(
//     //                                 attributes.getStrength().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         attributes.getAgility().setValue(
//     //                                 attributes.getAgility().getValue() + statusCondition.getTotalAdjustment()
//     //                         );
//     //                         System.out.println(this.getName() + " has recovered from their sickness.");
//     //                     }
//     //                     default -> throw new IllegalArgumentException("Unexpected value: " + statusCondition.getName());
//     //                 }
//     //                 statusCondition.setTotalAdjustment(0);
//     //             }
//     //         } catch (Exception e) {
//     //             System.out.println("Error with Debuff Method");
//     //         }
//     //     }

//     // }

//     public void handleEndTurn() {
//         System.out.println("=".repeat(50));
//         this.handleStatusConditions();
//         System.out.println(this.getName() + "'s turn has ended.");
//         System.out.println("=".repeat(50));
//     }

//     public void handleStartTurn() {
//         System.out.println("=".repeat(50));
//         System.out.println(this.getName() + "'s turn has started.");
//         System.out.println("=".repeat(50));

//         if (this.getHealthValues().getRegenValue() > 0) {
//             this.getHealthValues().setValue(
//                     this.getHealthValues().getValue() +
//                             this.getHealthValues().getRegenValue()
//             );
//         }
//         if (this.getManaValues().getRegenValue() > 0) {
//             this.getManaValues().setValue(
//                     this.getManaValues().getValue() +
//                             this.getManaValues().getRegenValue()
//             );
//         }
//         this.preventOverloadingResourceValues();
//     }

//     public void handleStatusConditions() {
//         for (StatusCondition condition : statusConditions.getAll()) {

//             if (condition.getDuration() > 0) {
//                 condition.setDuration(condition.getDuration() - 1);

//                 if (condition instanceof DamageOverTime dot) {
//                     dot.applyDamage(this);
//                 } else {
//                     condition.applyEffect(this); 
//                 }

//             } else {
//                 condition.endEffect(this); 
//             }
//         }
//     }


//     public StatusConditions getStatusConditions() {
//         return statusConditions;
//     }

//     @Override
//     public String toString() {
//         return "Actor{" +
//                 "name='" + name + '\'' +
//                 ", actorType='" + actorType + '\'' +
//                 ", healthValues=" + healthValues +
//                 ", manaValues=" + manaValues +
//                 ", attributes=" + attributes +
//                 ", resistances=" + resistances +
//                 ", statusConditions=" + statusConditions +
//                 ", stance='" + stance + '\'' +
//                 '}';
//     }
// }

