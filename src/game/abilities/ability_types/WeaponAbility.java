package abilities.ability_types;

import abilities.damages.Damage;
import items.equipment.item_types.WeaponTypes;

public class WeaponAbility extends TargetingAbility {
    private double multiplier;
    private boolean offhand;

    public double getMultiplier() {
        return multiplier;
    }

    // constructor
    public WeaponAbility(String name,
                         int levelRequirement,
                         int manaCost,
                         int actionCost,
                         Damage[] damages,
                         double multiplier,
                         WeaponTypes[] weaponTypes,
                         int leftRange,
                         int rightRange,
                         String description) {
        // armorTypes and shieldTypes are null for weapon abilities, tier defaults to 0
        super(name, levelRequirement, manaCost, actionCost, damages, null, null, weaponTypes, leftRange, rightRange, 0, description);
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
        // required
        private final String name;
        private final Damage[] damages;

        // optional / defaults
        private int levelRequirement = 1;
        private int manaCost = 0;
        private int actionCost = 1;
        private double multiplier = 1.0;
        private items.equipment.item_types.WeaponTypes[] weaponTypes = null;
        private int leftRange = 0;
        private int rightRange = 0;
        private String description = "";
        private boolean offhand = false;

        public Builder(String name, Damage[] damages) {
            this.name = name;
            this.damages = damages;
        }

        public Builder levelRequirement(int v) { this.levelRequirement = v; return this; }
        public Builder manaCost(int v) { this.manaCost = v; return this; }
        public Builder actionCost(int v) { this.actionCost = v; return this; }
        public Builder multiplier(double v) { this.multiplier = v; return this; }
        public Builder weaponTypes(items.equipment.item_types.WeaponTypes[] v) { this.weaponTypes = v; return this; }
        public Builder leftRange(int v) { this.leftRange = v; return this; }
        public Builder rightRange(int v) { this.rightRange = v; return this; }
        public Builder description(String v) { this.description = v; return this; }
        public Builder withOffhand() { this.offhand = true; return this; }

        public WeaponAbility build() {
            WeaponAbility wa = new WeaponAbility(
                name,
                levelRequirement,
                manaCost,
                actionCost,
                damages,
                multiplier,
                weaponTypes,
                leftRange,
                rightRange,
                description
            );
            if (offhand) wa.withOffhand();
            return wa;
        }
    }
}
