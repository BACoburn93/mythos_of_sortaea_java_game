package handlers.ability;

import java.util.function.BiFunction;
import actors.attributes.AttributeTypes;
import characters.Character;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import items.equipment.interfaces.WeaponDamageProvider;

public final class WeaponStatsResolver {
    private WeaponStatsResolver() {}

    public static record WeaponStats(double baseDamage, AttributeTypes attrToAttWith, BiFunction<Integer,Integer,Damage> damageFactory) {}

    public static WeaponStats resolve(Character caster, WeaponAbility ability) {
        boolean offhand = ability != null && ability.isOffhand();
        String slotKey = offhand ? "Offhand" : "Mainhand";
        var slot = caster.getEquipmentSlots().get(slotKey);

        if (slot != null && slot.getEquippedItem() instanceof WeaponDamageProvider wp) {
            return new WeaponStats(wp.getDamage(), wp.getWeaponDamageAttr(), wp.getBaseDamageType());
        }

        return new WeaponStats(
            caster.getJobObj().getUnarmedDamage(),
            caster.getJobObj().getUnarmedDamageAttr(),
            caster.getJobObj().getBaseDamageType()
        );
    }
}