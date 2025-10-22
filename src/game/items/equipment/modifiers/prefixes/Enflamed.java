package items.equipment.modifiers.prefixes;

import java.util.ArrayList;

import abilities.Ability;
import abilities.database.AbilityDatabase;
import items.equipment.Equipment;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.modifiers.EquipmentPrefix;
import utils.ChooseAbilities;

public class Enflamed implements EquipmentPrefix, ChooseAbilities {
    private static final ArrayList<Ability> possibleAbilities = new ArrayList<>();

    static {
        possibleAbilities.add(AbilityDatabase.FIREBALL);
        possibleAbilities.add(AbilityDatabase.FIRE_STORM);
        possibleAbilities.add(AbilityDatabase.FLAME_SLASH);
        possibleAbilities.add(AbilityDatabase.METEOR_SWARM);
    }
    
    public void apply(Equipment target) {
        target.setGoldValue(target.getGoldValue() * 3);

        if (target instanceof WeaponDamageProvider wp) {
            double newDamage = wp.getDamage() * 1.5;
            if (target instanceof MutableWeaponDamage mut) {
                mut.setDamage(newDamage);
            }
        }

        target.getResistances().addIce(3.0);
        target.getResistances().addFire(1.0);

        target.getResistances().multiplyIce(4.0);
        target.getResistances().multiplyFire(6.0);

        // Choose abilities based on item type as well as tier
        // An enflamed sword should not get fireball, for example.

        chooseAbilities(target, possibleAbilities);
        
    }

    @Override
    public String getName() {
        return "Enflamed ";
    }
}
