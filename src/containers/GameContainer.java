package containers;

import characters.Character;
import characters.Party;
import characters.jobs.*;
import utils.GameScanner;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameContainer {
    public Party party;
    GameScanner gameLoop = new GameScanner();
    public CombatContainer combatContainer;


    public GameContainer() {
        party = getPartyDetails();
    }

    private Party getPartyDetails() {
        // Create a number of characters for your party
        System.out.println("Welcome to Mythos of Sortaea!");
        System.out.println("After choosing your characters, you may type GUIDE at any time " +
                "in order to get a general list of instructions in different situations to help" +
                " you know what to type in different circumstances.");

        int numCharacters = getNumCharacters();

        ArrayList<Character> characterParty = new ArrayList<>();
        Set<String> characterNames = new HashSet<>();

        // Get Characters Info
        for (int i = 1; i <= numCharacters; i++) {
            boolean validName = false;
            String name = null;

            while (!validName) {
                System.out.println("What is Character Number " + i + "'s name?");
                name = StringUtils.capitalize(this.gameLoop.nextLine());

                if (!characterNames.contains(name.toLowerCase()) && !name.isEmpty()) {
                    characterNames.add(name.toLowerCase());
                    validName = true;
                } else {
                    if (characterNames.contains(name.toLowerCase())) {
                        System.out.println("This name is already taken. Please choose a different name.");
                    } else {
                        System.out.println("This name must contain characters.");
                    }
                }
            }

            System.out.println("What is " + name + "'s job? Mage, Warrior, Rogue, or Ranger?");
            boolean validJob = false;
            Job job = null;

            var validJobs = new ArrayList<Job>();
            validJobs.add(new MageJob());
            validJobs.add(new WarriorJob());
            validJobs.add(new RogueJob());
            validJobs.add(new RangerJob());

            while (!validJob) {
                String jobInput = StringUtils.capitalize(this.gameLoop.nextLine());

                for(Job jobThatIsValid : validJobs) {
                    if(jobThatIsValid.getName().equalsIgnoreCase(jobInput)) {
                        job = jobThatIsValid;
                        validJob = true;
                        break;
                    }
                }

                if(!validJob) {
                    StringUtils.stringDivider(jobInput + " is not a valid class you silly goose, " +
                            "please try again.", "=", 50);
                    StringUtils.stringDivider("Remember, the valid jobs are Mage, Warrior, Rogue, and Ranger.",
                            "=", 50);
                }
            }
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

}
