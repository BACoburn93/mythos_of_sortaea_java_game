package enemies.modifiers;

import enemies.Enemy;

public interface Suffix {
    void apply(Enemy enemy);
    String getName();
}
