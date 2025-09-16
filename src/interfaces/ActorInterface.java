package interfaces;

import abilities.Ability;
import abilities.damages.Damage;
import actors.ActorTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.stances.Stances;
import status_conditions.StatusCondition;
import status_conditions.StatusConditions;

public interface ActorInterface {
    String getName();
    ActorTypes getActorType();
    void setActorType(ActorTypes actorType);

    HealthValues getHealthValues();
    void setHealthValues(HealthValues healthValues);
    int getHealth();

    ManaValues getManaValues();
    Attributes getAttributes();
    Resistances getResistances();
    StatusConditions getStatusConditions();
    Stances getStance();
    void setStance(Stances stance);

    boolean isWet();
    boolean canUseAbility(Ability ability);
    void spendMana(Ability ability);

    void attack(ActorInterface attacker, ActorInterface target, Ability ability);
    void takeDamage(ActorInterface attacker, Ability ability);
    void takeDamage(StatusCondition statusCondition);
    void applyStatusCondition(ActorInterface attacker, Damage damage);
    
    void handleStartTurn();
    void handleEndTurn();

    void preventOverloadingResourceValues();
    void handleStatusConditions();
    int getInitiative();

    @Override
    String toString();
}

