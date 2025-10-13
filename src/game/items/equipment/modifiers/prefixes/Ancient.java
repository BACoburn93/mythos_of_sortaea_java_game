package items.equipment.modifiers.prefixes;
import items.equipment.Equipment;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.modifiers.EquipmentPrefix;

public class Ancient implements EquipmentPrefix {
   
    @Override
    public void apply(Equipment target) {
        target.setGoldValue(target.getGoldValue() * 5);

        if (target instanceof WeaponDamageProvider wp) {
            double newDamage = wp.getDamage() * 1.5;
            if (target instanceof MutableWeaponDamage mut) {
                mut.setDamage(newDamage);
            }
        }

        target.getAttributes().multiplyStrength(10);

    }

    @Override
    public String getName() {
        return "Ancient ";
    }
}
