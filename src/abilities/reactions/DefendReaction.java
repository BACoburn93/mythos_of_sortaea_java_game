package abilities.reactions;

public class DefendReaction extends Reaction {

    public DefendReaction() {
        super(ReactionTypes.DEFEND.toString(), 1, "Reduce damage from physical attacks, based on defense.");
    }
}
