package handlers.ability.executers;

import java.util.Random;

import abilities.Ability;
import abilities.ability_types.WeaponAbility;
import characters.Character;
import enemies.Enemy;
import handlers.ability.AbilityHandler;
import actors.types.CombatActor;

public class WeaponAbilityExecutor implements AbilityExecutor {
    @Override
    public boolean supports(Ability ability) {
        return ability instanceof WeaponAbility;
    }

    @Override
    public void execute(Character caster, CombatActor target, Ability ability, AbilityHandler handler, Random random) {
        handler.handleWeaponAbility(caster, (WeaponAbility) ability, target, random);
    }

    @Override
    public void execute(CombatActor caster, CombatActor target, Ability ability, AbilityHandler handler, Random random) {
        // Null for now, as only Characters can use WeaponAbilities
    }


}
