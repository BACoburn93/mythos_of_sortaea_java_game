package handlers;

import java.util.Random;

import ui.CombatUIStrings;
import utils.GameScanner;
import utils.SelectionUtils;
import utils.StringUtils;
import characters.Character;
import enemies.Enemy;
import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import actors.types.CombatActor;

import java.util.ArrayList;
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

                // Begin multi-target logic
                if (chosenAbility instanceof TargetingAbility targetingAbility) {
                    int leftRange = targetingAbility.getLeftRange();
                    int rightRange = targetingAbility.getRightRange();

                    int targetIndex = enemies.indexOf(chosenTarget);
                    if (targetIndex == -1) return;

                    Enemy mainTarget = enemies.get(targetIndex);
                    int mainSpawnWeight = mainTarget.getSpawnWeight();

                    // The main target occupies indices- [mainStart, mainEnd]
                    int mainStart = targetIndex;
                    int mainEnd = targetIndex + mainSpawnWeight - 1;

                    ArrayList<CombatActor> targetsToHit = new ArrayList<>();
                    targetsToHit.add(mainTarget);

                    // Left targets - accumulate spawnWeight as you move left
                    int leftSpaces = 0;
                    int idx = mainStart - 1;
                    while (idx >= 0 && leftSpaces < leftRange) {
                        Enemy e = enemies.get(idx);
                        int eWeight = e.getSpawnWeight();

                        // If adding this enemy would exceed the leftRange, break (can't reach past)
                        if (leftSpaces + eWeight > leftRange) {
                            if(!targetsToHit.contains(e)) {
                                targetsToHit.add(e);
                            }
                            break;
                        }

                        if (!targetsToHit.contains(e)) {
                            targetsToHit.add(e);
                        }
                        // Debugging print to visualize the spacing
                        leftSpaces += eWeight;
                        idx -= eWeight;
                    }

                    // Right targets - accumulate spawnWeight as you move right
                    int rightSpaces = 0;
                    idx = mainEnd + 1;
                    while (idx < enemies.size() && rightSpaces < rightRange) {
                        Enemy e = enemies.get(idx);
                        int eWeight = e.getSpawnWeight();

                        if (rightSpaces + eWeight > rightRange) {
                            if (!targetsToHit.contains(e)) {
                                targetsToHit.add(e);
                            }
                            break;
                        } 

                        if (!targetsToHit.contains(e)) {
                            targetsToHit.add(e);
                        }

                        rightSpaces += eWeight;
                        idx += 1; // Move one index at a time
                    }

                    for (CombatActor target : targetsToHit) {
                        boolean missedNewTarget = random.nextInt(100) < character.getStatusConditions().getBlind().getValue();
                        if (!missedNewTarget) {
                            character.attack(character, target, chosenAbility);
                        } else {
                            CombatUIStrings.printMissedAttack(character, target, chosenAbility);
                        }
                        CombatUIStrings.printHitPointsRemaining(target);

                        if (target.getHealthValues().getValue() < 0) {
                            actors = handleKillEnemy(target);
                        }
                    }
                } else {
                    // Fallback: single target logic
                    if (!missedTarget) {
                        character.attack(character, chosenTarget, chosenAbility);
                    } else {
                        CombatUIStrings.printMissedAttack(character, chosenTarget, chosenAbility);
                    }
                    CombatUIStrings.printHitPointsRemaining(chosenTarget);

                    if (chosenTarget.getHealthValues().getValue() < 0) {
                        actors = handleKillEnemy(chosenTarget);
                    }
                }
                // --- End multi-target logic ---
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