package events;

import characters.Party;
import enemies.Enemy;

public final class EnemyDeathEvent {
    private final Enemy enemy;
    private final Party party;

    public EnemyDeathEvent(Enemy enemy, Party party) {
        this.enemy = enemy;
        this.party = party;
    }

    public Enemy getEnemy() { return enemy; }
    public Party getParty() { return party; }
}
