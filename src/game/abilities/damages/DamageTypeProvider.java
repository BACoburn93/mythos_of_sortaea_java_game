package abilities.damages;

import java.util.function.BiFunction;

public interface DamageTypeProvider {

    BiFunction<Integer, Integer, Damage> getBaseDamageType();
}
