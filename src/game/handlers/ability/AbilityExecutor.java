package handlers.ability;

import java.util.Random;

import abilities.Ability;
import actors.types.CombatActor;
import characters.Character;
import enemies.Enemy;

public interface AbilityExecutor {
    boolean supports(Ability ability);
    void execute(Character caster, CombatActor target, Ability ability, AbilityHandler handler, Random random);
    void execute(Enemy caster, CombatActor target, Ability ability, AbilityHandler handler, Random random);
}
