package utils;

import abilities.Ability;
import enemies.Enemy;
import characters.Character;

public class ActionRequest {
    public final Enemy enemy;
    public final Ability ability;
    public final Character target;

    public ActionRequest(Enemy enemy, Ability ability, Character target2) {
        this.enemy = enemy;
        this.ability = ability;
        this.target = target2;
    }
}
