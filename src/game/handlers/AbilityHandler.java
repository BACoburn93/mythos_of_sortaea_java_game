package handlers;

import java.util.Random;
import java.util.function.BiFunction;

import ui.CombatUIStrings;
import ui.GeneralUIStrings;
import utils.GameScanner;
import utils.SelectionUtils;
import utils.StringUtils;
import characters.Character;
import enemies.Enemy;
import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import actors.types.CombatActor;

import java.util.ArrayList;

import items.equipment.equipment_slots.EquipmentSlot;
import items.equipment.item_types.*;

public class AbilityHandler {
    private GameScanner scanner;
    private TargetSelector targetSelector;
    private ArrayList<CombatActor> actors;
    private ArrayList<Enemy> enemies;

    public AbilityHandler(GameScanner scanner, TargetSelector targetSelector, ArrayList<CombatActor> actors, ArrayList<Enemy> enemies) {
        this.scanner = scanner;
        this.targetSelector = targetSelector;
        this.actors = actors;
        this.enemies = enemies;
    }

public void handleAttackAction(Character character) {
    CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

    if (chosenTarget == null) return;

    EquipmentSlot mainhandSlot = character.getEquipmentSlots().get("Mainhand");
    String attrToAttWith;
    double damage;
    BiFunction<Integer, Integer, Damage> damageFactory;

    if (mainhandSlot != null && mainhandSlot.getEquippedItem() instanceof items.equipment.item_types.mainhand.Mainhand weapon) {
        // Weapon attack
        damage = weapon.getDamage();
        attrToAttWith = weapon.getWeaponDamageAttr().toString().toLowerCase();
        damageFactory = weapon.getBaseDamageType();
    } else {
        // Unarmed attack
        damage = character.getJobObj().getUnarmedDamage();
        // damage = Math.max(1, damage);
        attrToAttWith = character.getJobObj().getUnarmedDamageAttr().toLowerCase();
        damageFactory = character.getJobObj().getBaseDamageType();
    }

    // Apply the attack using the correct damage type
    character.attack(
        chosenTarget,
        () -> damageFactory.apply(1, (int) damage), // or use min/max as needed
        attrToAttWith
    );
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

    private void checkLeftRange(int start, Enemy mainTarget, int leftRange, ArrayList<CombatActor> targetsToHit) {
        int leftSpaces = mainTarget.getSpawnWeight() - 1;
        int idx = start - 1;

        while (idx >= 0 && leftSpaces < leftRange) {
            Enemy e = enemies.get(idx);
            int eWeight = e.getSpawnWeight();

            System.out.println("Checking enemy to the left: " + e.getName() + " with weight " + eWeight + " at index " + idx);
            System.out.println("Remaining left spaces: " + (leftRange - leftSpaces));

            if (leftSpaces + eWeight <= leftRange) {
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

            System.out.println("Checking enemy to the right: " + e.getName() + " with weight " + eWeight + " at index " + idx);
            System.out.println("Remaining right spaces: " + (rightRange - rightSpaces));

            if (rightSpaces + eWeight <= rightRange) {
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

    private void handleTargetingAbility(Character character, TargetingAbility targetingAbility, CombatActor chosenTarget, Random random) {
        int leftRange = targetingAbility.getLeftRange();
        int rightRange = targetingAbility.getRightRange();

        int targetIndex = enemies.indexOf(chosenTarget);
        if (targetIndex == -1) return;

        Enemy mainTarget = enemies.get(targetIndex);
        // int mainSpawnWeight = mainTarget.getSpawnWeight();

        // int mainStart = targetIndex;
        // int mainEnd = targetIndex + mainSpawnWeight - 1;

        ArrayList<CombatActor> targetsToHit = new ArrayList<>();
        targetsToHit.add(mainTarget);

        checkLeftRange(targetIndex, mainTarget, leftRange, targetsToHit);
        checkRightRange(targetIndex, mainTarget, rightRange, targetsToHit);

        for (CombatActor target : targetsToHit) {
            boolean missedNewTarget = random.nextInt(100) < character.getStatusConditions().getBlind().getValue();
            if (!missedNewTarget) {
                character.attack(target, targetingAbility);
            } else {
                CombatUIStrings.printMissedAttack(character, target, targetingAbility);
            }
            CombatUIStrings.printHitPointsRemaining(target);

            if (target.getHealthValues().getValue() < 0) {
                actors = handleKillEnemy(target);
            }
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
                boolean missedTarget = random.nextInt(100) < character.getStatusConditions().getBlind().getValue();

                character.spendMana(chosenAbility);
                character.setActionPoints(character.getActionPoints() - chosenAbility.getActionCost());

                if (chosenAbility instanceof TargetingAbility targetingAbility) {
                    handleTargetingAbility(character, targetingAbility, chosenTarget, random);
                } else {
                    // TODO - Work on Weapon Ability handling
                    if (!missedTarget) {
                        character.attack(chosenTarget, chosenAbility);
                    } else {
                        CombatUIStrings.printMissedAttack(character, chosenTarget, chosenAbility);
                    }
                    CombatUIStrings.printHitPointsRemaining(chosenTarget);

                    if (chosenTarget.getHealthValues().getValue() < 0) {
                        actors = handleKillEnemy(chosenTarget);
                    }
                }
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