package interfaces;

import actors.ActorTypes;

public interface ActorInterface {
    String getName();
    ActorTypes getActorType();

    @Override
    String toString();
}

