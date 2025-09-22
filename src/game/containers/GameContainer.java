package containers;

import characters.CharacterCreator;
import characters.Party;
import utils.GameScanner;

public class GameContainer {
    public Party party;
    GameScanner gameLoop = new GameScanner();

    public GameContainer() {
        CharacterCreator characterCreator = new CharacterCreator();
        party = characterCreator.createParty();
    }

}
