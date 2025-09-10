package enemies;

import java.util.Arrays;

public class Enemies {
    Enemy[] enemies;

    Enemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    private static Enemy[] removeDefeatedEnemy(Enemy[] actors, int index) {
        Enemy[] newEnemies = new Enemy[actors.length - 1];
        for (int i = 0, j = 0; i < actors.length; i++) {
            if (i != index) {
                newEnemies[j++] = actors[i];
            }
        }
        System.out.println(Arrays.toString(actors) + " " + Arrays.toString(newEnemies));
        return newEnemies;
    }
}
