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
import abilities.ChannelingInfo;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import actors.types.CombatActor;

import java.util.ArrayList;
import java.util.HashMap;

import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;

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
        executors.addAll(new ArrayList<>(List.of(new WeaponAbilityExecutor(), new TargetingAbilityExecutor())));
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
        // Old Ability Handling Logic (pre ability channeling)

        // if (chosenAbility.getActionCost() > character.getActionPoints() || !character.canUseAbility(chosenAbility)) {
        //     CombatUIStrings.printAbilityPointUsage(character, chosenAbility);
        // } else {
        //     EquipmentHandler equipmentHandler = new EquipmentHandler(character);

        //     boolean meetsWeaponReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getWeaponRequirement(), WeaponTypes.class);
        //     boolean meetsArmorReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getArmorRequirement(), ArmorTypes.class);
        //     boolean meetsShieldReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getShieldRequirement(), ShieldTypes.class);

        //     if (!meetsWeaponReq || !meetsArmorReq || !meetsShieldReq) {
        //         StringUtils.stringDivider("You do not meet the equipment requirements for this ability.", "", 0);
        //         return;
        //     }

        //     CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);

        //     if (chosenTarget != null) {
        //         Random random = new Random();

        //         character.spendMana(chosenAbility);
        //         character.setActionPoints(character.getActionPoints() - chosenAbility.getActionCost());

        //         // Use this to handle different ability types
        //         executeAbility(character, chosenTarget, chosenAbility, random);
        //     } else {
        //         GeneralUIStrings.handleInvalidAction();
        //     }
        // }

        if (!character.canUseAbility(chosenAbility)) {
            CombatUIStrings.printAbilityPointUsage(character, chosenAbility);
            return;
        }

        int cost = chosenAbility.getActionCost();
        int available = character.getActionPoints();
        if (cost > available) {
            // Start channeling: consume all available AP this turn and record remaining cost
            character.setActionPoints(0);
            int remaining = cost - available;
            // begin channeling toward chosen target (ask for a target first)
            CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);
            if (chosenTarget == null) {
                // if they didn't choose a target, refund sAP used this turn (safe fallback)
                character.setActionPoints(available);
                return;
            }
            character.startChanneling(chosenAbility, chosenTarget, remaining);
            System.out.println("You begin channeling " + chosenAbility.getName() + ". Used " + available + " AP this turn. Remaining AP required: " + remaining);
            // Do not spend mana yet; it will be spent only when channeling completes.
            return;
        } else {
            int left = chosenAbility.getActionCost();
            if (left > character.getActionPoints()) {
                CombatUIStrings.printAbilityPointUsage(character, chosenAbility);
                return;
            }
        }

        EquipmentHandler equipmentHandler = new EquipmentHandler(character);
        boolean meetsWeaponReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getWeaponRequirement(), WeaponTypes.class);
        boolean meetsArmorReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getArmorRequirement(), ArmorTypes.class);
        boolean meetsShieldReq = equipmentHandler.meetsEquipmentRequirement(chosenAbility.getShieldRequirement(), ShieldTypes.class);
        if (!meetsWeaponReq || !meetsArmorReq || !meetsShieldReq) {
            StringUtils.stringDivider("You do not meet the equipment requirements for this ability.", "", 0);
            return;
        }

        CombatActor chosenTarget = targetSelector.chooseEnemyTarget(scanner);
        if (chosenTarget == null) {
            GeneralUIStrings.handleInvalidAction();
            return;
        }

        // full activation path: spend mana, consume AP, execute immediately
        Random random = new Random();
        character.spendMana(chosenAbility);
        character.setActionPoints(character.getActionPoints() - chosenAbility.getActionCost());
        executeAbility(character, chosenTarget, chosenAbility, random);
    }

    private ArrayList<CombatActor> handleKillEnemy(Enemy enemy, Party party) {

        EventBus.publish(new EnemyDeathEvent(enemy, party));

        enemies.remove(enemy);

        actors.removeIf(a -> a == enemy);
        return actors;
    }

    public void startTurn(Character character) {
        if (character == null) return;

        character.setActionPoints(character.getMaxActionPoints());

        ChannelingInfo ci = character.getChannelingInfo();
        if (ci == null) return;

        // show status
        System.out.println("You are currently channeling: " + ci.getAbility().getName() +
                " (remaining AP to finish: " + ci.getRemainingCost() + ")");
        System.out.println("You have " + character.getActionPoints() + " action points available this turn.");
        System.out.println("[C]ontinue channeling (use AP), [X] Cancel channeling, [S]kip (do nothing now)");

        String choice = null;
        try {
            choice = scanner.nextLine().trim().toLowerCase();
        } catch (Throwable t) {
            // fallback: assume skip
            choice = "s";
        }

        if ("c".equals(choice) || "continue".equals(choice) || "y".equals(choice) || "yes".equals(choice)) {
            int ap = character.getActionPoints();
            if (ap > 0) {
                if(ap > ci.getRemainingCost()) {
                    character.setActionPoints(ap - ci.getRemainingCost());
                } else {
                    character.setActionPoints(0); // consume all AP this turn to continue channeling
                }
                
                character.reduceChannelingBy(ap);
                System.out.println("You spend " + ap + " AP channeling " + ci.getAbility().getName() +
                        ". Remaining to finish: " + ci.getRemainingCost());
            } else {
                System.out.println("No action points available to continue channeling this turn.");
            }
            // If channeling completed, trigger ability
            if (ci.isComplete()) {
                // spend resources now and execute
                try { character.spendMana(ci.getAbility()); } catch (Throwable ignored) {}
                System.out.println("Channeling complete — " + ci.getAbility().getName() + " triggers!");
                executeAbility(character, ci.getTarget(), ci.getAbility(), new Random());
                character.completeAndClearChanneling();
            }
            // else actor may continue using leftover (none) AP this turn
        } else if ("x".equals(choice) || "cancel".equals(choice) || "n".equals(choice) || "no".equals(choice)) {
            character.cancelChanneling();
            System.out.println("Channeling cancelled.");
        } else {
            // skip: do nothing, actor proceeds normally (keeps full AP)
            System.out.println("Channeling left in progress. You may continue later.");
        }
        // } else {
        //     // Enemy / AI: auto-continue using all available AP
        //     int ap = actor.getActionPoints();
        //     if (ap > 0) {
        //         actor.setActionPoints(0);
        //         actor.reduceChannelingBy(ap);
        //     }
        //     if (actor.getChannelingInfo().isComplete()) {
        //         // execute for enemies: spend resources and fire
        //         ChannelingInfo ci2 = actor.getChannelingInfo();
        //         try {
        //             // enemies may not have mana; only spend if API exists
        //             actor.getClass().getMethod("spendMana", Ability.class).invoke(actor, ci2.getAbility());
        //         } catch (Throwable ignored) {}
        //         System.out.println(actor.getName() + " finishes channeling " + ci2.getAbility().getName() + "!");
        //         executeAbility(actor, ci2.getTarget(), ci2.getAbility(), new Random());
        //         actor.completeAndClearChanneling();
        //     }
        // }
    }
}