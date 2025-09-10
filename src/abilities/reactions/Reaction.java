package abilities.reactions;

import abilities.Ability;

public class Reaction extends Ability {
    public Reaction(String name, int actionCost, String description) {
        super(name, 0, actionCost,null,  description);
    }
}
