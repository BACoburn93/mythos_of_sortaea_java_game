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
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import actors.attributes.AttributeTypes;
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

    public void weaponAttack(Character character, WeaponAbility ability, CombatActor chosenTarget, Random random) {
        if (chosenTarget == null) return;

        EquipmentSlot mainhandSlot = character.getEquipmentSlots().get("Mainhand");
        AttributeTypes attrToAttWith;
        double baseDamage;
        BiFunction<Integer, Integer, Damage> damageFactory;

        if (mainhandSlot != null && mainhandSlot.getEquippedItem() instanceof items.equipment.item_types.mainhand.Mainhand weapon) {
            baseDamage = weapon.getDamage();
            attrToAttWith = weapon.getWeaponDamageAttr();
            damageFactory = weapon.getBaseDamageType();
        } else {
            baseDamage = character.getJobObj().getUnarmedDamage();
            attrToAttWith = character.getJobObj().getUnarmedDamageAttr();
            damageFactory = character.getJobObj().getBaseDamageType();
        }

        final double finalDamage;
        if (ability != null) {
            double abilityMultiplier = ability.getMultiplier();
            finalDamage = baseDamage * abilityMultiplier;
        } else {
            finalDamage = baseDamage;
        }

        // TODO - Consider modifying this so that the attack doesn't log the ability as an Attack
        // Also find a way to not log the hits separately when the attack hits and then after 
        // when the ability damage array hits
        // Basic Weapon Attack with multiplier from ability if applicable
        if (ability == null || ability.getMultiplier() > 0) {
            character.attack(
                chosenTarget,
                () -> damageFactory.apply((int) finalDamage / 2, (int) finalDamage),
                attrToAttWith
            );
        }

        // Additional Attack from ability bonus
        if(ability != null) {
            attackTarget(character, chosenTarget, ability, random);
        }
    }

    public void handleAttackAction(Character character) {
        CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

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

    // TODO
    // For left and right range, I currently check for eWeight / 2 to see if it fits in the range
    // The logic should target adjacent enemies in instances whether they are within range at all
    // In order to require that the entire spawnWeight is within range, change eWeight / 2 to eWeight
    // Further testing may still need to be done to ensure this works as intended
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

    private void handleTargetingAbility(Character character, TargetingAbility targetingAbility, CombatActor chosenTarget, Random random) {
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

    public void handleWeaponAbility(Character character, WeaponAbility chosenAbility, CombatActor chosenTarget, Random random) {
        weaponAttack(character, chosenAbility, chosenTarget, random);
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

                if (chosenAbility instanceof WeaponAbility weaponAbility) {
                    handleWeaponAbility(character, weaponAbility, chosenTarget, random);
                } else if (chosenAbility instanceof TargetingAbility targetingAbility) {
                    handleTargetingAbility(character, targetingAbility, chosenTarget, random);
                } else {
                    // TODO - Work on Weapon Ability handling
                    // if (!missedTarget) {
                    //     character.attack(chosenTarget, chosenAbility);
                    // } else {
                    //     CombatUIStrings.printMissedAttack(character, chosenTarget, chosenAbility);
                    // }
                    // CombatUIStrings.printHitPointsRemaining(chosenTarget);

                    // if (chosenTarget.getHealthValues().getValue() < 0) {
                    //     actors = handleKillEnemy(chosenTarget);
                    // }
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