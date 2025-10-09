package handlers.ability;

import java.util.Random;
import java.util.function.BiFunction;

import ui.CombatUIStrings;
import ui.GeneralUIStrings;
import utils.GameScanner;
import utils.SelectionUtils;
import utils.StringUtils;
import characters.Character;
import enemies.Enemy;
import handlers.EquipmentHandler;
import handlers.TargetSelector;
import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import actors.attributes.AttributeTypes;
import actors.types.CombatActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import items.equipment.item_types.*;
import items.equipment.interfaces.WeaponDamageProvider;

public class AbilityHandler {
    private GameScanner scanner;
    private TargetSelector targetSelector;
    private ArrayList<CombatActor> actors;
    private ArrayList<Enemy> enemies;

    private final ArrayList<AbilityExecutor> executors = new ArrayList<>();
    private final Map<Class<? extends Ability>, AbilityExecutor> executorMap = new HashMap<>();

    public AbilityHandler(GameScanner scanner, TargetSelector targetSelector, ArrayList<CombatActor> actors, ArrayList<Enemy> enemies) {
        this.scanner = scanner;
        this.targetSelector = targetSelector;
        this.actors = actors;
        this.enemies = enemies;

        // Used to register different ability executors
        executors.add(new WeaponAbilityExecutor());
        executors.add(new TargetingAbilityExecutor());

        // register map entries for specificity -- Prevents parent class from overriding child class
        registerExecutor(WeaponAbility.class, new WeaponAbilityExecutor());
        registerExecutor(TargetingAbility.class, new TargetingAbilityExecutor());
    }

    // - Weapon Handling -
    // Helper class to hold weapon stats
    private static class WeaponStats {
        final double baseDamage;
        final AttributeTypes attrToAttWith;
        final BiFunction<Integer, Integer, Damage> damageFactory;

        WeaponStats(double baseDamage, AttributeTypes attrToAttWith, BiFunction<Integer, Integer, Damage> damageFactory) {
            this.baseDamage = baseDamage;
            this.attrToAttWith = attrToAttWith;
            this.damageFactory = damageFactory;
        }
    }

    // Resolves the weapon stats based on whether the ability is offhand or not
    private WeaponStats resolveWeaponStats(Character caster, WeaponAbility ability) {
        boolean useOffhand = ability != null && ability.isOffhand();
        String slotKey = useOffhand ? "Offhand" : "Mainhand";
        items.equipment.equipment_slots.EquipmentSlot slot = caster.getEquipmentSlots().get(slotKey);

        if (slot != null && slot.getEquippedItem() instanceof WeaponDamageProvider wp) {
            return new WeaponStats(wp.getDamage(), wp.getWeaponDamageAttr(), wp.getBaseDamageType());
        }

        return new WeaponStats(
            caster.getJobObj().getUnarmedDamage(),
            caster.getJobObj().getUnarmedDamageAttr(),
            caster.getJobObj().getBaseDamageType()
        );
    }

    // Handles a weapon attack, optionally with a weapon ability
    public void weaponAttack(Character caster, WeaponAbility ability, CombatActor chosenTarget, Random random) {
        if (chosenTarget == null) return;

        WeaponStats stats = resolveWeaponStats(caster, ability);
        double baseDamage = stats.baseDamage;
        AttributeTypes attrToAttWith = stats.attrToAttWith;
        BiFunction<Integer,Integer,Damage> damageFactory = stats.damageFactory;

        final double finalDamage;

        if (ability != null) {
            double abilityMultiplier = ability.getMultiplier();
            finalDamage = baseDamage * abilityMultiplier;
        } else {
            finalDamage = baseDamage;
        }
        // Damage dealt from the weapon itself, modified by ability multiplier
        if (ability == null || ability.getMultiplier() > 0) {
            caster.attack(
                chosenTarget,
                () -> damageFactory.apply((int) finalDamage / 2, (int) finalDamage),
                attrToAttWith
            );
        }

        // Damage dealt from the ability itself
        if(ability != null) {
            attackTarget(caster, chosenTarget, ability, random);
        }
    }

    // - Ability Handling -
    // Handles the attack logic and logging
    public void handleAttackAction(Character character) {
        CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

        if(chosenTarget == null) return;

        weaponAttack(character, null, chosenTarget, null);

        character.setActionPoints(character.getActionPoints() - 1);

        CombatUIStrings.printHitPointsRemaining(chosenTarget);

        if (chosenTarget.getHealthValues().getValue() < 0) {
            actors = handleKillEnemy(chosenTarget);
        }
    }

    public void handleAbilityAction(Character character) {
        Ability chosenAbility = SelectionUtils.selectFromList(
            character.getAbilities(),
            scanner,
            Ability::getName,
            Ability::toString,
            "Type the ability name to use, [L]ist to see abilities, or [Q]uit: ",
            "l",
            3
        );

        if (chosenAbility != null) {
            handleUseAbility(character, chosenAbility);
        }
    }

    private void attackTarget(Character character, CombatActor target, Ability ability, Random random) {
        boolean missed = random.nextInt(100) < character.getStatusConditions().getBlind().getValue();
        if (!missed) {
            character.attack(target, ability);
        } else {
            CombatUIStrings.printMissedAttack(character, target, ability);
        }
        CombatUIStrings.printHitPointsRemaining(target);

        if (target.getHealthValues().getValue() < 0) {
            actors = handleKillEnemy(target);
        }
    }

    private void checkLeftRange(int start, Enemy mainTarget, int leftRange, ArrayList<CombatActor> targetsToHit) {
        int leftSpaces = mainTarget.getSpawnWeight() - 1;
        int idx = start - 1;

        while (idx >= 0 && leftSpaces < leftRange) {
            Enemy e = enemies.get(idx);
            int eWeight = e.getSpawnWeight();

            if (leftSpaces + (eWeight / 2) <= leftRange) {
                if (!targetsToHit.contains(e)) {
                    targetsToHit.add(e);
                }
                leftSpaces += eWeight;
                idx--;
            } else {
                break;
            }
        }
    }

    private void checkRightRange(int end, Enemy mainTarget, int rightRange, ArrayList<CombatActor> targetsToHit) {
        int rightSpaces = mainTarget.getSpawnWeight() - 1;
        int idx = end + 1;

        while (idx < enemies.size() && rightSpaces < rightRange) {
            Enemy e = enemies.get(idx);
            int eWeight = e.getSpawnWeight();

            if (rightSpaces + (eWeight / 2) <= rightRange) {
                if (!targetsToHit.contains(e)) {
                    targetsToHit.add(e);
                }
                rightSpaces += eWeight;
                idx++;
            } else {
                break;
            }
        }
    }

    public void handleTargetingAbility(Character character, TargetingAbility targetingAbility, CombatActor chosenTarget, Random random) {
        int leftRange = targetingAbility.getLeftRange();
        int rightRange = targetingAbility.getRightRange();

        int targetIndex = enemies.indexOf(chosenTarget);
        if (targetIndex == -1) return;

        Enemy mainTarget = enemies.get(targetIndex);

        ArrayList<CombatActor> targetsToHit = new ArrayList<>();
        targetsToHit.add(mainTarget);

        checkLeftRange(targetIndex, mainTarget, leftRange, targetsToHit);
        checkRightRange(targetIndex, mainTarget, rightRange, targetsToHit);

        for (CombatActor target : targetsToHit) {
            attackTarget(character, target, targetingAbility, random);
        }
    }

    public void handleWeaponAbility(Character caster, WeaponAbility chosenAbility, CombatActor chosenTarget, Random random) {
        weaponAttack(caster, chosenAbility, chosenTarget, random);
    }

    private void registerExecutor(Class<? extends Ability> abilityClass, AbilityExecutor executor) {
        executorMap.put(abilityClass, executor);
    }

    private int classDistance(Class<?> ancestor, Class<?> descendant) {
        if (!ancestor.isAssignableFrom(descendant)) return Integer.MAX_VALUE;
        int dist = 0;
        Class<?> cur = descendant;
        while (cur != null && cur != ancestor) {
            cur = cur.getSuperclass();
            dist++;
        }
        return dist;
    }

    private void executeAbility(Character caster, CombatActor target, Ability chosenAbility, Random random) {
        AbilityExecutor ex = executorMap.get(chosenAbility.getClass());

        if (ex == null) {
            int best = Integer.MAX_VALUE;
            for (Map.Entry<Class<? extends Ability>, AbilityExecutor> entry : executorMap.entrySet()) {
                Class<? extends Ability> key = entry.getKey();
                int d = classDistance(key, chosenAbility.getClass());
                if (d < best) {
                    best = d;
                    ex = entry.getValue();
                }
            }
        }

        if (ex == null) {
            for (AbilityExecutor e : executors) {
                if (e.supports(chosenAbility)) {
                    ex = e;
                    break;
                }
            }
        }

        if (ex != null) {
            ex.execute(caster, target, chosenAbility, this, random);
        } else {
            System.out.println("Ability type not recognized.");
        }
    }

    public void handleUseAbility(Character character, Ability chosenAbility) {
        if (chosenAbility.getActionCost() > character.getActionPoints() || !character.canUseAbility(chosenAbility)) {
            CombatUIStrings.printAbilityPointUsage(character, chosenAbility);
        } else {
            EquipmentHandler equipmentHandler = new EquipmentHandler(character);

            boolean meetsWeaponReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getWeaponRequirement(), WeaponTypes.class);
            boolean meetsArmorReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getArmorRequirement(), ArmorTypes.class);
            boolean meetsShieldReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getShieldRequirement(), ShieldTypes.class);

            if (!meetsWeaponReq || !meetsArmorReq || !meetsShieldReq) {
                StringUtils.stringDivider("You do not meet the equipment requirements for this ability.", "", 0);
                return;
            }

            CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

            if (chosenTarget != null) {
                Random random = new Random();

                character.spendMana(chosenAbility);
                character.setActionPoints(character.getActionPoints() - chosenAbility.getActionCost());

                // Use this to handle different ability types
                executeAbility(character, chosenTarget, chosenAbility, random);
            } else {
                GeneralUIStrings.handleInvalidAction();
            }
        }
    }

    private ArrayList<CombatActor> handleKillEnemy(CombatActor enemy) {
        System.out.println(enemy.getName() + " has been slain.");
        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }
}