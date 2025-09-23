package utils;

import java.util.Random;

public class MathUtils {
    
    public static int safeRandomInt(double value) {
        return new Random().nextInt(Math.max(1, (int) Math.floor(value)));
    }

    public static double randomInRange(double base, double range) {
        Random rand = new Random();
        double min = base - range;
        double max = base + range;
        return min + (max - min) * rand.nextDouble();
    }

    public static double randomInRange(double base) {
        return randomInRange(base, 0.1);
    }
}
