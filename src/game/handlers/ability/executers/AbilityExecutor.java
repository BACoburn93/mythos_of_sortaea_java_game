package handlers.ability.executers;

import java.util.Random;

import abilities.Ability;
import actors.types.CombatActor;
import characters.Character;
import handlers.ability.AbilityHandler;

public interface AbilityExecutor {
    boolean supports(Ability ability);
    void execute(Character caster, CombatActor target, Ability ability, AbilityHandler handler, Random random);
    void execute(CombatActor caster, CombatActor target, Ability ability, AbilityHandler handler, Random random);
}
