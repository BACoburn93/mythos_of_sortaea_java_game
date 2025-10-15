package handlers.ability;

import java.util.Random;
import java.util.Map;
import java.util.List;

import ui.CombatUIStrings;
import ui.GeneralUIStrings;
import utils.GameScanner;
import utils.SelectionUtils;
import utils.StringUtils;
import characters.Character;
import characters.Party;
import enemies.Enemy;
import events.EnemyDeathEvent;
import events.EventBus;
import handlers.EquipmentHandler;
import handlers.TargetSelector;
import handlers.ability.executers.AbilityExecutor;
import handlers.ability.executers.TargetingAbilityExecutor;
import handlers.ability.executers.WeaponAbilityExecutor;
import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import actors.types.CombatActor;

import java.util.ArrayList;
import java.util.HashMap;

import items.equipment.item_types.*;

public class AbilityHandler {
    private GameScanner scanner;
    private TargetSelector targetSelector;
    private Party party;
    private ArrayList<CombatActor> actors;
    private ArrayList<Enemy> enemies;

    private final ArrayList<AbilityExecutor> executors = new ArrayList<>();
    private final Map<Class<? extends Ability>, AbilityExecutor> executorMap = new HashMap<>();

    public AbilityHandler(GameScanner scanner, TargetSelector targetSelector, Party party, ArrayList<CombatActor> actors, ArrayList<Enemy> enemies) {
        this.scanner = scanner;
        this.targetSelector = targetSelector;
        this.party = party;
        this.actors = actors;
        this.enemies = enemies;

        // register executors once
        registerExecutor(WeaponAbility.class, new WeaponAbilityExecutor());
        registerExecutor(TargetingAbility.class, new TargetingAbilityExecutor());
        // keep executors list for fallback supports()
        executors.clear();
        executors.addAll(List.of(new WeaponAbilityExecutor(), new TargetingAbilityExecutor()));
    }

    public AbilityHandler(GameScanner scanner, Party party, ArrayList<CombatActor> actors, ArrayList<Enemy> enemies) {
        this.scanner = scanner;
        this.targetSelector = null;
        this.party = party;
        this.actors = actors;
        this.enemies = enemies;

        // Used to register different ability executors
        executors.add(new WeaponAbilityExecutor());
        executors.add(new TargetingAbilityExecutor());

        // register map entries for specificity -- Prevents parent class from overriding child class
        registerExecutor(WeaponAbility.class, new WeaponAbilityExecutor());
        registerExecutor(TargetingAbility.class, new TargetingAbilityExecutor());
    }

    // small, high-level weapon attack delegator
    public void weaponAttack(Character caster, WeaponAbility ability, CombatActor chosenTarget, Random random) {
        var stats = WeaponStatsResolver.resolve(caster, ability);
        double baseDamage = stats.baseDamage();
        if (ability == null || ability.getMultiplier() > 0) {
            caster.attack(
                chosenTarget,
                () -> stats.damageFactory().apply((int)baseDamage/2, (int)baseDamage),
                stats.attrToAttWith()
            );
        }
        if (ability != null) attackTarget(caster, chosenTarget, ability, random);
    }

    // - Ability Handling -
    // Handles the attack logic and logging
    public void handleAttackAction(Character character) {
        CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

        if(chosenTarget == null) return;

        weaponAttack(character, null, chosenTarget, null);

        character.setActionPoints(character.getActionPoints() - 1);

        CombatUIStrings.printHitPointsRemaining(chosenTarget);

        if (chosenTarget.getHealthValues().getValue() <= 0 && chosenTarget instanceof Enemy) {
            actors = handleKillEnemy((Enemy) chosenTarget, party);
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

        if (target.getHealthValues().getValue() <= 0 && target instanceof Enemy) {
            actors = handleKillEnemy((Enemy) target, party);
        }
    }

    public void handleTargetingAbility(Character character, TargetingAbility ta, CombatActor chosenTarget, Random random) {
        int left = ta.getLeftRange(), right = ta.getRightRange();
        int idx = enemies.indexOf(chosenTarget);
        if (idx == -1) return;
        Enemy center = enemies.get(idx);
        var targets = TargetingHelper.expandEnemyTargets(enemies, center, left, right);
        for (CombatActor t : targets) attackTarget(character, t, ta, random);
    }

    public void handleTargetingAbilityAgainstParty(Enemy caster, TargetingAbility ta, CombatActor chosenTarget, Random random) {
        Character center = (chosenTarget instanceof Character) ? (Character) chosenTarget : null;
        if (center == null) return;
        var targets = TargetingHelper.expandPartyTargets(party, center, ta.getLeftRange(), ta.getRightRange());
        for (Character ch : targets) attackTargetByEnemy(caster, ch, ta, random);
    }

    private void attackTargetByEnemy(Enemy enemy, CombatActor target, Ability ability, Random random) {
        if (target == null) return;
        boolean missed = random.nextInt(100) < enemy.getStatusConditions().getBlind().getValue();
        if (!missed) {
            enemy.attack(target, ability);
        } else {
            CombatUIStrings.printMissedAttack(enemy, target, ability);
        }
        CombatUIStrings.printHitPointsRemaining(target);

        // Optional: handle character death if needed (maybe hook into party/manager)
        if (target.getHealthValues().getValue() < 0 && target instanceof Character) {
            // might notify CombatManager / EventBus here if I want centralized removal
            System.out.println(target.getName() + " has been slain.");
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

    // executeAbility stays but delegates to executors (no heavy logic here)
    public void executeAbility(CombatActor caster, CombatActor target, Ability chosenAbility, Random random) {
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
            if (caster instanceof Character) {
                ex.execute((Character) caster, target, chosenAbility, this, random);
            } else {
                ex.execute(caster, target, chosenAbility, this, random);
            }
        } else {
            // fallback
            if (caster != null) caster.attack(target, chosenAbility);
        }
    }

    public void handleUseEnemyAbility(Enemy enemy, Ability chosenAbility) {
        if (chosenAbility == null) return;

        ArrayList<Character> validTargets = party.validTargetsInParty();
        if (validTargets.isEmpty()) return;

        Random random = new Random();
        Character target = validTargets.get(random.nextInt(validTargets.size()));

        executeAbility(enemy, target, chosenAbility, random);
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

    private ArrayList<CombatActor> handleKillEnemy(Enemy enemy, Party party) {

        EventBus.publish(new EnemyDeathEvent(enemy, party));

        // List<Object> drops = LootManager.generateDrops(enemy);
        // for (Object o : drops) {
        //     if (o instanceof Equipment) {
        //         party.getSharedEquipment().add((Equipment) o);
        //     } else if (o instanceof Integer) {
        //         party.addGold((Integer) o);
        //     }
        // }

        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }
}