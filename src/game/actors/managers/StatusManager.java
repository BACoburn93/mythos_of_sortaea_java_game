package actors.managers;

import actors.types.CombatActor;
import status_conditions.StatusCondition;
import status_conditions.DamageOverTime;
import abilities.damages.Damage;

import java.util.ArrayList;
import java.util.Random;


public final class StatusManager {
    private static final StatusManager INSTANCE = new StatusManager();
    private final Random rnd = new Random();

    private StatusManager() {}

    public static StatusManager getInstance() { return INSTANCE; }

    // Called when an attacker deals Damage to target; evaluates status procs and applies effects.
    public void applyOnHit(CombatActor attacker, CombatActor target, Damage damage) {
        if (attacker == null || target == null || damage == null) return;
        for (StatusCondition status : damage.getStatusConditions()) {
            int roll = rnd.nextInt(1, 101);
            if (status.getChanceToTrigger() < roll) continue;

            StatusCondition curr = target.getStatusConditions().getStatus(status.getName());
            
            // Chance to hit is based on luck vs. resilience + resistance
            int attackerRoll = rnd.nextInt(Math.max(1, (int) attacker.getAttributes().getLuck().getValue()));
            int targetRoll = rnd.nextInt(Math.max(1, (int) (target.getAttributes().getResilience().getValue() * 2.0 + curr.getResistance())));

            if (attackerRoll > targetRoll) {
                System.out.println(target.getName() + target.getStatusConditions().getStatusAffectedText(status.getName()));
                curr.setValue(status.getValue());
                curr.setDuration(status.getDuration());
            }
        }
    }

    public void applyEffects(CombatActor target) {
        for (StatusCondition c : target.getStatusConditions().getAll()) {
            if (c.getDuration() > 0) {
                if (c instanceof DamageOverTime dot) dot.applyDamage(target);
                else c.applyEffect(target);
            }
        }
    }

    public void advanceDurations(CombatActor target) {
        for (StatusCondition c : new ArrayList<>(target.getStatusConditions().getAll())) {
            c.setDuration(Math.max(0, c.getDuration() - 1));
            if (c.getDuration() == 0) c.endEffect(target);
        }
    }

    // Tick/resolve all active status conditions on an actor (for when both apply and advance are needed simultaneously)
    public void tick(CombatActor target) {
        if (target == null) return;
        applyEffects(target);
        advanceDurations(target);
    }

    // Convenience wrappers for start/end-turn hooks
    public void handleStartTurn(CombatActor target) {
        // e.g. handle passive regen or other start-of-turn status behavior
        applyEffects(target);
    }

    public void handleEndTurn(CombatActor target) {
        advanceDurations(target);
    }
}
