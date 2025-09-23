package characters;

import characters.jobs.Job;
import characters.jobs.Jobs.MageJob;
import characters.jobs.Jobs.RangerJob;
import characters.jobs.Jobs.RogueJob;
import characters.jobs.Jobs.WarriorJob;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.InputHandler;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterCreator {

    private final GameScanner gameLoop = new GameScanner();

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

        for (int i = 1; i <= numCharacters; i++) {
            String name = getUniqueCharacterName(i, characterNames);
            Job job = chooseJobForCharacter(name);
            characterParty.add(new Character(name, job));
        }

        return new Party(characterParty);
    }

    private int getNumCharacters() {
        int numCharacters = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("How many characters would you like to have in your party? (Maximum 6)");

            try {
                numCharacters = Integer.parseInt(this.gameLoop.nextLine());
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
            name = StringUtils.capitalize(this.gameLoop.nextLine());

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
        System.out.println(CombatUIStrings.formatJobList(validJobs));

        Job job = null;
        boolean validJob = false;

        while (!validJob) {
            String jobInput = gameLoop.nextLine();
            job = InputHandler.getItemByInput(jobInput, validJobs, Job::getName);

            if (job != null) {
                validJob = true;
            } else {
                StringUtils.stringDivider(jobInput + " is not a valid class, please try again.", "", 50);
                System.out.println(CombatUIStrings.formatJobList(validJobs));
            }
        }

        return job;
    }
}
