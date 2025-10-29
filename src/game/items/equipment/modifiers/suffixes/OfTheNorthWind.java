package items.equipment.modifiers.suffixes;

import java.util.ArrayList;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import items.equipment.Equipment;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.modifiers.EquipmentSuffix;
import utils.ChooseAbilities;

public class OfTheNorthWind implements EquipmentSuffix, ChooseAbilities {
    private static final ArrayList<Ability> possibleAbilities = new ArrayList<>();
    
    static {
        possibleAbilities.add(AbilityDatabase.FROST_WIND_SLASH); // tier 2
        possibleAbilities.add(AbilityDatabase.ICE_SPIKE); // tier 3
        possibleAbilities.add(AbilityDatabase.BLIZZARD); // tier 7
    }

    @Override
    public void apply(Equipment target) {
        target.setGoldValue(target.getGoldValue() * 3);

        if (target instanceof WeaponDamageProvider wp) {
            double newDamage = wp.getDamage() * 1.5;
            if (target instanceof MutableWeaponDamage mut) {
                mut.setDamage(newDamage);
            }
        }

        target.getResistances().addIce(1.0);
        target.getResistances().addWind(2.0);

        target.getResistances().multiplyIce(3.0);
        target.getResistances().multiplyWind(3.0);

        chooseAbilities(target, possibleAbilities);

    }

    @Override
    public String getName() {
        return " of the North Wind";
    }

}
