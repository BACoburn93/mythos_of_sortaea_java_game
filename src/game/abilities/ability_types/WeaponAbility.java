package abilities.ability_types;

import abilities.AbilityCategory;
import abilities.damages.Damage;
import items.equipment.item_types.enums.WeaponTypes;

public class WeaponAbility extends TargetingAbility {
    private double multiplier;
    private boolean offhand;

    public double getMultiplier() {
        return multiplier;
    }

    // constructor
    public WeaponAbility(String name,
                        AbilityCategory primaryAttribute,
                         int levelRequirement,
                         int manaCost,
                         int actionCost,
                         Damage[] damages,
                         double multiplier,
                         WeaponTypes[] weaponTypes,
                         int leftRange,
                         int rightRange,
                         int tier,
                         String description) {
        // armorTypes and shieldTypes are null for weapon abilities, tier defaults to 0
        super(name, primaryAttribute, levelRequirement, manaCost, actionCost, damages, null, null, weaponTypes, leftRange, rightRange, 0, description);
        this.multiplier = multiplier;
    }

    public WeaponAbility withOffhand() {
        this.offhand = true;
        return this;
    }

    public boolean isOffhand() {
        return offhand;
    }

    // Builder for WeaponAbility
    public static class Builder {
        // Required
        private final String name;
        private final Damage[] damages;

        // Optional/Defaults
        private AbilityCategory primaryAttribute = AbilityCategory.BODY;
        private int levelRequirement = Math.max(this.tier * 2 - 1, 1);
        private int manaCost = 0;
        private int actionCost = 1;
        private double multiplier = 1.0;
        private items.equipment.item_types.enums.WeaponTypes[] weaponTypes = null;
        private int leftRange = 0;
        private int rightRange = 0;
        private int tier = 0;
        private String description = "";
        private boolean offhand = false;

        public Builder(String name, Damage[] damages) {
            this.name = name;
            this.damages = damages;
        }

        public Builder primaryAttribute(AbilityCategory v) { this.primaryAttribute = v; return this; }
        public Builder levelRequirement(int v) { this.levelRequirement = v; return this; }
        public Builder manaCost(int v) { this.manaCost = v; return this; }
        public Builder actionCost(int v) { this.actionCost = v; return this; }
        public Builder multiplier(double v) { this.multiplier = v; return this; }
        public Builder weaponTypes(items.equipment.item_types.enums.WeaponTypes[] v) { this.weaponTypes = v; return this; }
        public Builder leftRange(int v) { this.leftRange = v; return this; }
        public Builder rightRange(int v) { this.rightRange = v; return this; }
        public Builder tier(int v) { this.tier = v; return this; }
        public Builder description(String v) { this.description = v; return this; }
        public Builder withOffhand() { this.offhand = true; return this; }

        public WeaponAbility build() {
            WeaponAbility wa = new WeaponAbility(
                name,
                primaryAttribute,
                levelRequirement,
                manaCost,
                actionCost,
                damages,
                multiplier,
                weaponTypes,
                leftRange,
                rightRange,
                tier,
                description
            );
            if (offhand) wa.withOffhand();

            wa.determinePrereqPrimaryAttribute();
            return wa;
        }
    }
}
