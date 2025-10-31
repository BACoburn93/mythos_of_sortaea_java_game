package characters;

import characters.jobs.Job;
import characters.jobs.Jobs.MageJob;
import characters.jobs.Jobs.RangerJob;
import characters.jobs.Jobs.RogueJob;
import characters.jobs.Jobs.WarriorJob;
import items.equipment.Equipment;
import items.equipment.EquipmentKey;
import utils.FactoryRegistry;
import utils.GameScanner;
import utils.InputHandler;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import actors.species.SubspeciesRegistry;
import actors.species.SpeciesCategory;
import actors.species.SpeciesType;

public class CharacterCreator {

    private final GameScanner gameScanner;

    public CharacterCreator(GameScanner gameScanner) {
        this.gameScanner = gameScanner;
    }

    private static final List<Job> validJobs = List.of(
            new MageJob(),
            new WarriorJob(),
            new RogueJob(),
            new RangerJob()
    );

    public Party createParty() {
        System.out.println("After choosing your characters, you may type GUIDE at any time " +
                "in order to get a general list of instructions in different situations to help" +
                " you know what to type in different circumstances.");
        System.out.println("");

        int numCharacters = getNumCharacters();
        ArrayList<Character> characterParty = new ArrayList<>();
        Set<String> characterNames = new HashSet<>();

        List<Equipment> startingEq = new ArrayList<>();

        String[] keys = {
            EquipmentKey.GREAT_STAFF.key(), 
            EquipmentKey.DAGGER.key(), EquipmentKey.TOWER_SHIELD.key(),
            EquipmentKey.SWORD.key(), 
            EquipmentKey.BOW.key(), EquipmentKey.PLATE_ARMOR.key(),
            EquipmentKey.LEATHER_ARMOR.key(), EquipmentKey.RING.key(), EquipmentKey.AMULET.key()
        };

        for (int i=0;i<10;i++) {
            String key = keys[new Random().nextInt(keys.length)];
            startingEq.add(FactoryRegistry.getEquipmentFactory().createRandomByKey(key, null, null));
        }

        for (int i = 1; i <= numCharacters; i++) {
            String name = getUniqueCharacterName(i, characterNames);
            // choose species first (must be Humanoid)
            SpeciesType species = chooseSpeciesForCharacter(name);

            // then choose job/class
            Job job = chooseJobForCharacter(name);

            // create character, attach species and apply species buffs
            Character created = new Character(gameScanner, name, job);
            created.addSpecies(species);

            characterParty.add(created);
        }

        return new Party(characterParty, startingEq);
    }

    private int getNumCharacters() {
        int numCharacters = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("How many characters would you like to have in your party? (Maximum 6)");

            try {
                numCharacters = Integer.parseInt(this.gameScanner.nextLine());
                if (numCharacters <= 6 && numCharacters > 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number from 1 to 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return numCharacters;
    }

    private String getUniqueCharacterName(int index, Set<String> usedNames) {
        boolean validName = false;
        String name = null;

        while (!validName) {
            System.out.println("What is Character Number " + index + "'s name?");
            name = StringUtils.capitalize(this.gameScanner.nextLine());

            boolean isNumericIndex = false;
            try {
                int num = Integer.parseInt(name);
                if (num >= 1 && num <= 6) {
                    isNumericIndex = true;
                }
            } catch (NumberFormatException e) {
               // Not a number, so it's fine
            }

            if (!usedNames.contains(name.toLowerCase()) && !name.isEmpty() && !isNumericIndex) {
                usedNames.add(name.toLowerCase());
                validName = true;
            } else {
                if (usedNames.contains(name.toLowerCase())) {
                    System.out.println("This name is already taken. Please choose a different name.");
                } else if (isNumericIndex) {
                    System.out.println("Names cannot be a number from 1 to 6. Please choose a different name.");
                } else {
                    System.out.println("This name must contain characters.");
                }
            }
        }

        return name;
    }

    private Job chooseJobForCharacter(String name) {
        System.out.println("What is " + name + "'s job?");
        StringUtils.printOptionsGrid(validJobs, Job::getName, 4, 5);

        Job job = null;
        boolean validJob = false;

        while (!validJob) {
            String jobInput = gameScanner.nextLine();
            job = InputHandler.getItemByInput(jobInput, validJobs, Job::getName);

            if (job != null) {
                validJob = true;
            } else {
                StringUtils.stringDivider(jobInput + " is not a valid class, please try again.", "", 1);
                StringUtils.printOptionsGrid(validJobs, Job::getName, 4, 5);
            }
        }

        return job;
    }

    private SpeciesType chooseSpeciesForCharacter(String name) {
        // force Humanoid subspecies selection
        List<String> subs = new ArrayList<>(SubspeciesRegistry.getInstance().getForCategory(SpeciesCategory.HUMANOID));
        if (subs.isEmpty()) {
            // fallback to generic Humanoid if registry empty
            return new SpeciesType(SpeciesCategory.HUMANOID, "Human");
        }

        System.out.println("Choose " + name + "'s heritage (humanoid subspecies):");
        StringUtils.printOptionsGrid(subs, s -> s, 4, 5);

        String choice = null;
        boolean ok = false;
        while (!ok) {
            choice = gameScanner.nextLine();
            String selected = InputHandler.getItemByInput(choice, subs, s -> s);
            if (selected != null) {
                ok = true;
                return new SpeciesType(SpeciesCategory.HUMANOID, selected);
            } else {
                System.out.println(choice + " is not a valid selection. Please try again.");
                StringUtils.printOptionsGrid(subs, s -> s, 4, 5);
            }
        }
        // unreachable but required by compiler
        return new SpeciesType(SpeciesCategory.HUMANOID, subs.get(0));
    }
}
