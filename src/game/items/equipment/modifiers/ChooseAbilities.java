package items.equipment.modifiers;

import java.util.ArrayList;

import abilities.Ability;
import items.equipment.Equipment;

public interface ChooseAbilities {

    public default void chooseAbilities(Equipment target, ArrayList<Ability> abilities) {

        for (Ability ability : abilities) {
            if (ability.getTier() <= target.getTier()) {
                target.getAbilities().add(ability);
            }
        }
        
    }
}
