package abilities.reactions;

public class ItemReaction extends Reaction {

    public ItemReaction() {
        super(ReactionTypes.ITEM.toString(), 1, "Utilize an item to restore, buff, or remove conditions.");
    }
}
