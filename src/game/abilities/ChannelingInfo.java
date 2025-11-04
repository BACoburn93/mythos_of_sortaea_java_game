package abilities;

import actors.types.CombatActor;


public final class ChannelingInfo {
    private final Ability ability;
    private final CombatActor target;
    private final int originalCost;
    private int remainingCost;

    public ChannelingInfo(Ability ability, CombatActor target, int originalCost) {
        this.ability = ability;
        this.target = target;
        this.originalCost = originalCost;
        this.remainingCost = originalCost;
    }

    public Ability getAbility() { return ability; }
    public CombatActor getTarget() { return target; }
    public int getOriginalCost() { return originalCost; }
    public int getRemainingCost() { return remainingCost; }

    public void reduceRemainingCost(int amount) {
        if (amount <= 0) return;
        remainingCost = Math.max(0, remainingCost - amount);
    }

    public boolean isComplete() { return remainingCost <= 0; }
}