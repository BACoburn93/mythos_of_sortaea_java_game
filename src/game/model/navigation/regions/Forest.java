package model.navigation.regions;

import java.util.Map;

import model.navigation.abstr_methods.Region;

public class Forest extends Region {
    public Forest(Map<String, Double> enemyWeights) {
        super("Forest", enemyWeights);
        // Optionally, add Forest-specific event chains or logic here
    }
}
