package items.equipment.modifiers.suffixes;

import items.equipment.Equipment;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.modifiers.Suffix;

public class OfTheNorth implements Suffix {
    
    @Override
    public void apply(Equipment target) {
        target.setGoldValue(target.getGoldValue() * 2);

        if (target instanceof WeaponDamageProvider wp) {
            double newDamage = wp.getDamage() * 1.5;
            if (target instanceof MutableWeaponDamage mut) {
                mut.setDamage(newDamage);
            }
        }

        target.getResistances().addIce(1.0);
        target.getResistances().addFire(1.0);

        target.getResistances().multiplyIce(5.0);
        target.getResistances().multiplyFire(5.0);
    }

    @Override
    public String getName() {
        return " of the North";
    }

}
