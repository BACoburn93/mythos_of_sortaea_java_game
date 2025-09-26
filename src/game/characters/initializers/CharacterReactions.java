package characters.initializers;

import abilities.reactions.*;

public class CharacterReactions {
    public static Reaction[] getReactions() {
        return new Reaction[] {
            new DefendReaction(),
            new ParryReaction(),
            new ItemReaction(),
            new ObserveReaction(),
            new PassReaction()
        };
    }
}
