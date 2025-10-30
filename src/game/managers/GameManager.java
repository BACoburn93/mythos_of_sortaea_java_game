package managers;

import containers.GameContainer;
import enemies.EnemyDatabase;
import events.EnemyDeathEvent;
import events.EventBus;
import handlers.*;
import handlers.ability.AbilityHandler;
import model.navigation.GameFlowManager;
import model.navigation.regions.Forest;
import ui.MenuUIStrings;
import utils.FactoryRegistry;
import utils.GameScanner;
import utils.InputHandler;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import actors.species.SpeciesRegistrar;
import actors.types.CombatActor;
import characters.Character;
import characters.Party;
import characters.jobs.Jobs.MageJob;
import characters.jobs.Jobs.RangerJob;
import characters.jobs.Jobs.RogueJob;
import characters.jobs.Jobs.WarriorJob;
import items.equipment.Equipment;
import items.equipment.EquipmentDatabase;
import items.equipment.EquipmentKey;
import loot.LootManager;

public class GameManager {
    private final InputHandler inputHandler = new InputHandler();
    private final GameScanner gameScanner = new GameScanner();
    private boolean gameIsRunning = true;

    public void start() {
        MenuUIStrings.titleScreen();
        SpeciesRegistrar.init();
        EquipmentDatabase.init();
        EnemyDatabase.init();

        EventBus.subscribe(EnemyDeathEvent.class, evt -> {
            var drops = LootManager.generateDrops(evt.getEnemy());
            for (Object o : drops) {
                if (o instanceof Equipment) {
                    evt.getParty().getSharedEquipment().add((Equipment) o);
                    System.out.println("Loot Obtained! " + evt.getEnemy().getName() + " dropped: ");
                    System.out.println(o);
                } else if (o instanceof Integer) {
                    evt.getParty().addGold((Integer) o);
                    System.out.println("Gold Obtained! " + evt.getEnemy().getName() + " dropped: " + o + " gold.");
                }
            }
        });

        while (gameIsRunning) {
            MenuUIStrings.mainMenu();

            // Define menu options
            java.util.List<String> menuOptions = java.util.List.of(
                "Start New Game",
                "Test Combat",
                "Quit"
            );
            StringUtils.printOptionsGrid(menuOptions, s -> s, 1, 4);

            String choice = inputHandler.getInput("Choose an option: ");

            switch (choice) {
                case "1":
                    startNewGame(gameScanner);
                    break;

                case "2":
                    runTestCombat();
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    gameIsRunning = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        inputHandler.close();
    }

    private void startNewGame(GameScanner gameScanner) {
        GameContainer newGame = new GameContainer(gameScanner);
        EquipmentDatabase.init();
        System.out.println("Your party is ready:");
        System.out.println(newGame.party.printPartySummary());

        GameFlowManager flowManager = new GameFlowManager();
        flowManager.startExploration(newGame.party);
    }

    private void runTestCombat() {
        Character testMage = new Character(gameScanner, "Test Mage", new MageJob());
        Character testWarrior = new Character(gameScanner, "Test Warrior", new WarriorJob());
        Character testRanger = new Character(gameScanner, "Test Ranger", new RangerJob());
        Character testRogue = new Character(gameScanner, "Test Rogue", new RogueJob());

        List<Equipment> shared = new ArrayList<>();
        // String[] keys = {
        //     EquipmentKey.LESSER_STAFF.key(), EquipmentKey.DAGGER.key(), EquipmentKey.TOWER_SHIELD.key(),
        //     EquipmentKey.SWORD.key(), EquipmentKey.PLATE_ARMOR.key(), EquipmentKey.BUCKLER.key(),
        //     EquipmentKey.LEATHER_ARMOR.key(), EquipmentKey.RING.key(), EquipmentKey.AMULET.key(),
        //     EquipmentKey.STAFF.key(), EquipmentKey.GREAT_STAFF.key(), EquipmentKey.KNIFE.key(),
        //     EquipmentKey.LONGBOW.key(), EquipmentKey.ROUND_SHIELD.key(), EquipmentKey.JESTER_HAT.key(),
        //     EquipmentKey.LEATHER_CAP.key(), EquipmentKey.CLOAK.key(), EquipmentKey.CHAIN_LINK_MANTLE.key(),
        //     EquipmentKey.LEATHER_BELT.key(), EquipmentKey.SASH.key(), EquipmentKey.CHAIN_LEGGINGS.key(),
        //     EquipmentKey.PLATE_LEGGINGS.key(), EquipmentKey.LEATHER_BOOTS.key(), EquipmentKey.IRON_GREAVES.key()
        // };

        // for (int i=0;i<10;i++) {
        //     String key = keys[new Random().nextInt(keys.length)];
        //     shared.add(FactoryRegistry.getEquipmentFactory().createRandomByKey(key, null, null));
        // }

        EquipmentKey[] keys = EquipmentKey.values();
        Random rnd = new Random();

        for (int i = 0; i < 30; i++) {
            EquipmentKey chosen = keys[rnd.nextInt(keys.length)];
            shared.add(FactoryRegistry.getEquipmentFactory().createRandomByKey(chosen.key(), null, null));
        }
        
        ArrayList<Character> testCharacters = new ArrayList<>();
            testCharacters.add(testMage);
            testCharacters.add(testWarrior);
            testCharacters.add(testRanger);
            testCharacters.add(testRogue);

        Party party = new Party(testCharacters, shared);
        Forest currentRegion = new Forest();

        ArrayList<enemies.Enemy> enemies = currentRegion.generateEnemies();
        ArrayList<CombatActor> allActors = currentRegion.setupCombatActors(party, enemies);
        ArrayList<enemies.Enemy> testEnemies = new ArrayList<>();
        allActors.addAll(testEnemies);

        // Create handlers
        EquipmentHandler equipmentHandler = new EquipmentHandler(party);
        ActionHandler actionHandler = new ActionHandler(gameScanner, allActors, party, enemies, equipmentHandler);
        ReactionHandler reactionHandler = new ReactionHandler(gameScanner);
        AbilityHandler abilityHandler = new AbilityHandler(gameScanner, party, allActors, enemies);
        // GameScanner scanner, TargetSelector targetSelector, Party party, ArrayList<CombatActor> actors, ArrayList<Enemy> enemies

        // Start combat
        CombatManager combatManager = new CombatManager(
                party,
                enemies,
                abilityHandler,
                actionHandler,
                reactionHandler
        );

        combatManager.startCombat();
    }
}

