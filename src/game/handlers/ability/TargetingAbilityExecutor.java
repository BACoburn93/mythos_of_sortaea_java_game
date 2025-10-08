package handlers.ability;

import java.util.Random;

import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import characters.Character;
import actors.types.CombatActor;

public class TargetingAbilityExecutor implements AbilityExecutor {
    @Override
    public boolean supports(Ability ability) {
        return ability instanceof TargetingAbility && !(ability instanceof WeaponAbility);
    }

    @Override
    public void execute(Character caster, CombatActor target, Ability ability, AbilityHandler handler, Random random) {
        handler.handleTargetingAbility(caster, (TargetingAbility) ability, target, random);
    }
}