package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import abilities.Ability;
import enemies.Enemy;
import items.equipment.Equipment;

public interface ChooseAbilities {

    static void chooseAbilitiesImpl(int maxTier, List<Ability> pool, Consumer<Ability> adder) {
        for (Ability ability : pool) {
            if (ability.getTier() <= maxTier) {
                adder.accept(ability);
            }
        }
    }

    public default void chooseAbilities(Equipment target, ArrayList<Ability> abilityPool) {
        int maxTier = target.getTier();

        List<Ability> candidates = abilityPool.stream()
        .filter(a -> a.isApplicableTo(target))
        .collect(Collectors.toList());

        chooseAbilitiesImpl(maxTier, candidates, ability -> target.getAbilities().add(ability));
    }

    public default void chooseAbilities(Enemy target, ArrayList<Ability> abilityPool) {
        int maxTier = target.getLevel() / 9;
        chooseAbilitiesImpl(maxTier, abilityPool, ability -> target.getAbilities().add(ability));
    }
    
}
