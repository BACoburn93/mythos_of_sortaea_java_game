package abilities.interfaces;

import abilities.Ability;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.StatusConditions;
import abilities.damages.Damage;
import actors.ActorTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.stances.Stances;

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

    void preventOverloadingResourceValues();
    void handleStatusConditions();
    int getInitiative();

    @Override
    String toString();
}

