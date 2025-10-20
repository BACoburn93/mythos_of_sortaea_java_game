package items.equipment.item_types.mainhand;

import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;
import items.equipment.item_types.offhand.Offhand;
import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageTypeProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class Mainhand extends Equipment implements DamageTypeProvider, WeaponDamageProvider, MutableWeaponDamage {
    private boolean twoHanded;
    double damage;
    private List<Ability> abilities;

    public Mainhand(String name, int tier, double value, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        this(name, tier, value, itemType, twoHanded, attributes, resistances, abilities, 0.0);
    }

    public Mainhand(String name, int tier, double value, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(name, tier, value, EquipmentTypes.MAINHAND, itemType, attributes, resistances, abilities);
        this.twoHanded = twoHanded;
        this.damage = damage;
        this.abilities = abilities;
    }

    public double getDamage() {
        return damage;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.STRENGTH; 
    }

    @Override
    public abstract BiFunction<Integer, Integer, Damage> getBaseDamageType();

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    public static abstract class MainhandBuilder<T extends MainhandBuilder<T>> extends Builder<T> {
        protected double damage = 0.0;
        protected boolean twoHanded = false;

        public MainhandBuilder() {
            // defaults
            this.name = "Wooden Sword";
            this.tier = 0;
            this.value = 1.0;
            this.damage = 2.0;
            this.itemType = WeaponTypes.SWORD;
            this.abilities = new ArrayList<>();
        }

        @Override
        protected abstract T self();

        public T twoHanded(boolean v) { this.twoHanded = v; return self(); }
        public T damage(double d) { this.damage = d; return self(); }

        // public Mainhand build() {
        //     return new Mainhand(name, tier, value, itemType, twoHanded, attributes, resistances, abilities, damage) {
        //         @Override
        //         public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        //             return (min, max) -> null;
        //         }
        //     };
        // }
    }

    // Might switch to trying to use Template later on when I need to support more complex constructors.
    
    // public static abstract class Builder<T extends Builder<T>> extends Equipment.Builder<T> {
    //     protected boolean twoHanded = false;
    //     protected double damage = 0.0;
    //     protected ItemType itemType;

    //     protected abstract T self();

    //     public T twoHanded(boolean v) { this.twoHanded = v; return self(); }
    //     public T damage(double d) { this.damage = d; return self(); }
    //     public T itemType(ItemType it) { this.itemType = it; return self(); }

    //     // Attempt to instantiate a concrete Mainhand subclass using common signatures.
    //     public <U extends Mainhand> U buildInto(Class<U> clazz) {
    //         try {
    //             String n = (name != null) ? name : "Mainhand Item";
    //             int t = Math.max(0, tier);
    //             double v = Math.max(1.0, value);
    //             ItemType it = (itemType != null) ? itemType : this.itemType;
    //             boolean tw = twoHanded;
    //             Attributes a = attributes;
    //             Resistances r = resistances;
    //             java.util.List<Ability> abs = (abilities == null) ? new java.util.ArrayList<>() :
    //                 (abilities instanceof java.util.ArrayList ? abilities : new java.util.ArrayList<>(abilities));
    //             double dmg = damage;

    //             // try the most specific constructors first
    //             try {
    //                 var ctor = clazz.getConstructor(String.class, int.class, double.class, ItemType.class, boolean.class, Attributes.class, Resistances.class, java.util.List.class, double.class);
    //                 return ctor.newInstance(n, t, v, it, tw, a, r, abs, dmg);
    //             } catch (NoSuchMethodException ignored) {}

    //             try {
    //                 var ctor = clazz.getConstructor(String.class, int.class, double.class, ItemType.class, boolean.class, Attributes.class, Resistances.class, java.util.List.class);
    //                 return ctor.newInstance(n, t, v, it, tw, a, r, abs);
    //             } catch (NoSuchMethodException ignored) {}

    //             try {
    //                 var ctor = clazz.getConstructor(String.class, int.class, double.class, boolean.class, Attributes.class, Resistances.class, java.util.List.class, double.class);
    //                 return ctor.newInstance(n, t, v, tw, a, r, abs, dmg);
    //             } catch (NoSuchMethodException ignored) {}

    //             try {
    //                 var ctor = clazz.getConstructor(String.class, int.class, double.class, boolean.class, Attributes.class, Resistances.class, java.util.List.class);
    //                 return ctor.newInstance(n, t, v, tw, a, r, abs);
    //             } catch (NoSuchMethodException ignored) {}

    //             throw new IllegalStateException("No supported constructor found for " + clazz.getName());
    //         } catch (ReflectiveOperationException e) {
    //             throw new IllegalStateException("Failed to build " + clazz.getName(), e);
    //         }
    //     }
    // }

    // // concrete TemplateBuilder satisfies the self-type bound safely
    // private static class TemplateBuilder extends Builder<TemplateBuilder> {
    //     @Override protected TemplateBuilder self() { return this; }
    //     @Override public Equipment build() { throw new UnsupportedOperationException("Use buildInto(Class<? extends Mainhand>) for concrete instances"); }
    // }

    // public static Mainhand.Builder<?> template() {
    //     return new TemplateBuilder();
    // }
}
