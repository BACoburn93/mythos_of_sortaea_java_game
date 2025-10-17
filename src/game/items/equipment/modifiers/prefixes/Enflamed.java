package items.equipment.modifiers.prefixes;

import abilities.database.AbilityDatabase;
import items.equipment.Equipment;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.modifiers.EquipmentPrefix;

public class Enflamed implements EquipmentPrefix {
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

        // to do - create a dynamic class that adds ability based on the item's tier
        // do the same for enemys based on their level
        target.getAbilities().add(AbilityDatabase.FIRE_STORM);
    }

    @Override
    public String getName() {
        return "Enflamed ";
    }
}
