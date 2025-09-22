package abilities.reactions;

public class ParryReaction extends Reaction {

    public ParryReaction() {
        super(ReactionTypes.PARRY.toString(), 1, "Chance to prevent physical damage, based on defense.");
    }
}
