package enemies.modifiers;

import enemies.Enemy;
import utils.Modifier;

public interface EnemyPrefix extends Modifier<Enemy> {
    enum Position { PRE, POST }

    String getName();
    void apply(Enemy e);

    default Position getPosition() { return Position.PRE; }
}
