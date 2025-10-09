package items.equipment.interfaces;

import abilities.damages.Damage;
import actors.attributes.AttributeTypes;
import java.util.function.BiFunction;

public interface WeaponDamageProvider {
    double getDamage();
    AttributeTypes getWeaponDamageAttr();
    BiFunction<Integer, Integer, Damage> getBaseDamageType();
}