package abilities.ability_types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import abilities.Ability;
import abilities.AbilityCategory;
import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.AccessoryTypes;
import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;

public class TargetingAbility extends Ability {
    private int leftRange;
    private int rightRange;

    // Primary constructor
    public TargetingAbility(
        String name,
        AbilityCategory primaryAttribute,
        int levelRequirement,
        int manaCost,
        int actionCost,
        Damage[] damages,
        ArmorTypes[] armorTypes,
        ShieldTypes[] shieldTypes,
        WeaponTypes[] weaponTypes,
        int leftRange,
        int rightRange,
        int tier,
        String description
    ) {
        super(name, primaryAttribute, levelRequirement, manaCost, actionCost, damages, armorTypes, shieldTypes, weaponTypes, tier, description);
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    // Builder
    public static class Builder {
        // Required
        private final String name;
        private Damage[] damages;
        private Map<String, Double> speciesDamageModifiers = new HashMap<>();

        // Optional/Defaults
        private AbilityCategory primaryAttribute = AbilityCategory.BODY;
        private int levelRequirement = -1;
        private int manaCost = 0;
        private int actionCost = 1;
        private ArmorTypes[] armorTypes = null;
        private ShieldTypes[] shieldTypes = null;
        private WeaponTypes[] weaponTypes = null;
        private int leftRange = 0;
        private int rightRange = 0;
        private int tier = 0;
        private String description = "";

        // optional explicit overrides
        private EnumSet<DamageTypes> allowedDamageTypes = null;
        private Set<ItemType> allowedEquipmentTypes = null;
        private Set<String> allowedActorTypes = null;

        public Builder(String name, Damage[] damages) {
            this.name = name;
            this.damages = damages;
        }

        public Builder allowedDamageTypes(DamageTypes... types) {
            if (types == null || types.length == 0) { this.allowedDamageTypes = null; return this; }
            this.allowedDamageTypes = EnumSet.noneOf(DamageTypes.class);
            for (DamageTypes t : types) this.allowedDamageTypes.add(t);
            return this;
        }

        public Builder allowedEquipmentTypes(ItemType... types) {
            if (types == null || types.length == 0) { this.allowedEquipmentTypes = null; return this; }
            if (this.allowedEquipmentTypes == null) this.allowedEquipmentTypes = new HashSet<>();
            for (ItemType t : types) this.allowedEquipmentTypes.add(t);
            return this;
        }

        public Builder allowedActorTypes(String... keys) {
            if (keys == null || keys.length == 0) { this.allowedActorTypes = null; return this; }
            if (this.allowedActorTypes == null) this.allowedActorTypes = new HashSet<>();
            for (String k : keys) if (k != null) this.allowedActorTypes.add(k.toLowerCase());
            return this;
        }

        public Builder allowedEquipmentTypes(WeaponTypes... types)   { return addEnumsToAllowed(types); }
        public Builder allowedEquipmentTypes(ArmorTypes... types)    { return addEnumsToAllowed(types); }
        public Builder allowedEquipmentTypes(ShieldTypes... types)   { return addEnumsToAllowed(types); }
        public Builder allowedEquipmentTypes(AccessoryTypes... types){ return addEnumsToAllowed(types); }

        private <E extends Enum<?>> Builder addEnumsToAllowed(E[] types) {
            if (types == null || types.length == 0) { this.allowedEquipmentTypes = null; return this; }
            if (this.allowedEquipmentTypes == null) this.allowedEquipmentTypes = new HashSet<>();
            for (E e : types) if (e instanceof ItemType) this.allowedEquipmentTypes.add((ItemType)e);
            return this;
        }

        public Builder speciesDamageModifier(String speciesKey, double fractionalBonus) {
            if (speciesKey == null || speciesKey.isBlank()) return this;
            this.speciesDamageModifiers.put(speciesKey.trim().toUpperCase(), fractionalBonus);
            return this;
        }

        public Builder levelRequirement(int v) { this.levelRequirement = v; return this; }
        public Builder primaryAttribute(AbilityCategory v) { this.primaryAttribute = v; return this; }
        public Builder manaCost(int v) { this.manaCost = v; return this; }
        public Builder actionCost(int v) { this.actionCost = v; return this; }
        public Builder armorTypes(ArmorTypes[] v) { this.armorTypes = v; return this; }
        public Builder shieldTypes(ShieldTypes[] v) { this.shieldTypes = v; return this; }
        public Builder weaponTypes(WeaponTypes[] v) { this.weaponTypes = v; return this; }
        public Builder leftRange(int v) { this.leftRange = v; return this; }
        public Builder rightRange(int v) { this.rightRange = v; return this; }
        public Builder tier(int v) { this.tier = v; return this; }
        public Builder description(String v) { this.description = v; return this; }

        public TargetingAbility build() {
            int resolvedLevelReq = (levelRequirement >= 0) ? levelRequirement : abilities.Ability.defaultLevelForTier(tier);

            TargetingAbility ta = new TargetingAbility(
                name,
                primaryAttribute,
                resolvedLevelReq,
                manaCost,
                actionCost,
                damages,
                armorTypes,
                shieldTypes,
                weaponTypes,
                leftRange,
                rightRange,
                tier,
                description
            );

            // apply explicit overrides (if any) — otherwise Ability's constructor defaults remain
            if (this.allowedDamageTypes != null && !this.allowedDamageTypes.isEmpty()) {
                ta.setAllowedDamageTypes(this.allowedDamageTypes);
            }
            if (this.allowedEquipmentTypes != null && !this.allowedEquipmentTypes.isEmpty()) {
                ta.setAllowedEquipmentTypes(this.allowedEquipmentTypes);
            }
            if (this.allowedActorTypes != null && !this.allowedActorTypes.isEmpty()) {
                ta.setAllowedActorTypes(this.allowedActorTypes);
            }

            if (!this.speciesDamageModifiers.isEmpty()) {
                this.speciesDamageModifiers.forEach(ta::addSpeciesDamageModifier);
            }

            ta.determinePrereqPrimaryAttribute();

            return ta;
        }
    }

    // getters/setters
    public int getLeftRange() { return leftRange; }
    public void setLeftRange(int leftRange) { this.leftRange = leftRange; }
    public int getRightRange() { return rightRange; }
    public void setRightRange(int rightRange) { this.rightRange = rightRange; }

    public void setPrimaryAttribute(AbilityCategory category) {
        try {
            java.lang.reflect.Field f = this.getClass().getSuperclass().getDeclaredField("primaryAttribute");
            f.setAccessible(true);
            f.set(this, category);
        } catch (Throwable ignored) {}
    }

    public void setAllowedDamageTypes(EnumSet<DamageTypes> types) {
        this.allowedDamageTypes = (types == null) ? null : EnumSet.copyOf(types);
    }

    public void setAllowedEquipmentTypes(Set<ItemType> types) {
        this.allowedEquipmentTypes = (types == null) ? null : new HashSet<ItemType>(types);
    }

    public void determinePrereqPrimaryAttribute() {
        Damage[] baseDamages = null;
        try {
            java.lang.reflect.Method m = this.getClass().getMethod("getDamages");
            Object res = m.invoke(this);
            if (res instanceof Damage[] arr) baseDamages = arr;
        } catch (Throwable ignored) {}

        if (baseDamages == null) {
            try {
                java.lang.reflect.Field f = this.getClass().getSuperclass().getDeclaredField("damages");
                f.setAccessible(true);
                Object res = f.get(this);
                if (res instanceof Damage[] arr) baseDamages = arr;
            } catch (Throwable ignored) {}
        }

        // Default to BODY if we can't determine classification
        AbilityCategory result = AbilityCategory.BODY;

        if (baseDamages != null && baseDamages.length > 0 && baseDamages[0] != null) {
            var cls = baseDamages[0].getDamageClassification();
            if (cls == DamageClassificationTypes.PHYSICAL) {
                result = AbilityCategory.BODY;
            } else if (cls == DamageClassificationTypes.MAGICAL) {
                result = AbilityCategory.MIND;
            } else if (cls == DamageClassificationTypes.SPIRITUAL) {
                result = AbilityCategory.SPIRIT;
            }
        }

        this.setPrimaryAttribute(result);
    }
}
