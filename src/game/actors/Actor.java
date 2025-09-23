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