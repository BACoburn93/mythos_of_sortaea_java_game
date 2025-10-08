package handlers.ability;

import java.util.Random;

import abilities.Ability;
import actors.types.CombatActor;
import characters.Character;

public interface AbilityExecutor {
    boolean supports(Ability ability);
    void execute(Character caster, CombatActor target, Ability ability, AbilityHandler handler, Random random);
}
