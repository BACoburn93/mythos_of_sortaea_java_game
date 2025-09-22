package enemies.modifiers;

import enemies.Enemy;

public interface Prefix {
    void apply(Enemy enemy);
    String getName();
}
