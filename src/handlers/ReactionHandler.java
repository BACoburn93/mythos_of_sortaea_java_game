package handlers;

import abilities.reactions.Reaction;
import actors.types.CombatActor;
import characters.Character;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.ListUtils;

import java.util.ArrayList;

public class ReactionHandler {
    private final GameScanner scanner;

    public ReactionHandler(GameScanner scanner) {
        this.scanner = scanner;
    }

    public void handleReaction(ArrayList<Character> partyCharacters, ArrayList<CombatActor> turnOrder) {
        boolean validCharacter = false;
        boolean validReaction = false;
        Character chosenCharacter = null;

        while (!validReaction && !validCharacter) {
            while (!validCharacter) {
                // System.out.println("Party Members:");
                CombatUIStrings.formatPartyStat(partyCharacters);
                
                System.out.println("");
                System.out.println("Choose a character to use a reaction (by name or number), or hit ENTER to pass.");
                String characterToChoose = scanner.nextLine();
                if (characterToChoose.isEmpty()) {
                    return;
                }

                chosenCharacter = ListUtils.getByInput(characterToChoose, partyCharacters, Character::getName);
                if (chosenCharacter != null && chosenCharacter.getActionPoints() > 0) {
                    validCharacter = true;
                } else if (chosenCharacter != null) {
                    System.out.println(chosenCharacter.getName() + " is out of Action Points and will pass by default.");
                    return;
                }
            }

            if (validCharacter) {
                System.out.println("Defend, Parry, Item, Observe, or Pass?");
                String reactionInput = scanner.nextLine();

                Reaction chosenReaction = chosenCharacter.chooseReaction(reactionInput);
                System.out.println("=".repeat(50));

                if (chosenCharacter.isValidReaction(reactionInput)) {
                    if (reactionInput.equalsIgnoreCase("help")) {
                        System.out.println("Optional actions are: Defend, Parry, Item, Observe, or Pass.");
                    } else {
                        chosenCharacter.setActionPoints(chosenCharacter.getActionPoints() - chosenReaction.getActionCost());
                        chosenCharacter.handleReaction(reactionInput.toLowerCase());
                        validReaction = true;
                    }
                } else {
                    System.out.println("Invalid reaction, please try again. If you need help, type HELP.");
                }

                CombatUIStrings.printActionPoints(chosenCharacter);
            }
        }
    }
}
