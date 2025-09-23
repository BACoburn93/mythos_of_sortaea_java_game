package utils;

import java.util.Random;

public class MathUtils {
    
    public static int safeRandomInt(double value) {
        return new Random().nextInt(Math.max(1, (int) Math.floor(value)));
    }
    
}
