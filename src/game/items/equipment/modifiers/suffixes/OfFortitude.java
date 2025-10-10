package items.equipment.modifiers.suffixes;

import items.equipment.Equipment;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.modifiers.Suffix;

public class OfFortitude implements Suffix {
    @Override
    public void apply(Equipment target) {
        target.setGoldValue(target.getGoldValue() * 4);

        if (target instanceof WeaponDamageProvider wp) {
            double newDamage = wp.getDamage() * 1.5;
            if (target instanceof MutableWeaponDamage mut) {
                mut.setDamage(newDamage);
            }
        }

        target.getResistances().addBludgeoning(20);
        target.getResistances().addPiercing(20);
        target.getResistances().addSlashing(20);
    }

    @Override
    public String getName() {
        return " of Fortitude";
    }
}
