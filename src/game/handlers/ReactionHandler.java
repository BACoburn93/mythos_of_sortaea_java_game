package handlers;

import abilities.reactions.Reaction;
import actors.types.CombatActor;
import characters.Character;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.ListUtils;
import utils.StringUtils;
import utils.InputHandler;

import java.util.ArrayList;

public class ReactionHandler {
    private final GameScanner scanner;

    public ReactionHandler(GameScanner scanner) {
        this.scanner = scanner;
    }

    public void handleReaction(ArrayList<CombatActor> combatActors, ArrayList<Character> partyCharacters) {
        boolean validCharacter = false;
        boolean validReaction = false;
        Character chosenCharacter = null;

        while (!validCharacter) {
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

            
        while (!validReaction) {
            java.util.List<String> reactions = java.util.List.of("Defend", "Parry", "Item", "Observe", "Pass");
            
            StringUtils.printOptionsGrid(reactions, s -> s, 3, 4);

            System.out.println("Choose a reaction (by name or number):");
            String reactionInput = scanner.nextLine();

            String selectedReaction = InputHandler.getItemByInput(reactionInput, reactions, s -> s);

            String reactionToUse = selectedReaction != null ? selectedReaction : reactionInput;

            Reaction chosenReaction = chosenCharacter.chooseReaction(reactionToUse);
            System.out.println("");

            if (chosenCharacter.isValidReaction(reactionToUse)) {
                chosenCharacter.setActionPoints(chosenCharacter.getActionPoints() - chosenReaction.getActionCost());
                chosenCharacter.handleReaction(combatActors, partyCharacters, reactionToUse.toLowerCase());
                validReaction = true;
            } else {
                System.out.println("Invalid reaction, please try again.");
            }
        }
    }
}
