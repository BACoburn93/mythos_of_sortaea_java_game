package handlers.ability;

import java.util.Random;
import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import actors.types.CombatActor;
import characters.Character;
import enemies.Enemy;

public class TargetingAbilityExecutor implements AbilityExecutor {
    @Override
    public boolean supports(Ability ability) {
        return ability instanceof TargetingAbility && !(ability instanceof WeaponAbility);
    }

    @Override
    public void execute(Character caster, CombatActor target, Ability ability, AbilityHandler handler, Random random) {
        execute((CombatActor) caster, target, ability, handler, random);
    }

    @Override
    public void execute(CombatActor caster, CombatActor target, Ability ability, AbilityHandler handler, Random random) {
        TargetingAbility ta = (TargetingAbility) ability;
        if (caster instanceof Enemy) {
            handler.handleTargetingAbilityAgainstParty((Enemy) caster, ta, target, random);
        } else {
            handler.handleTargetingAbility((Character) caster, ta, target, random);
        }
    }
}