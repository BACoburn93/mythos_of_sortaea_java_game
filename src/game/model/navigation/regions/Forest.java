package model.navigation.regions;

import java.util.Map;

import model.navigation.abstr_methods.Region;
import model.navigation.regions.initializers.*;

public class Forest extends Region {
    public Forest() {
        super("Forest");
    }

    @Override
    protected Map<String, Double> initEnemyPool() {
        return ForestInit.forestEnemies;
    }
}
