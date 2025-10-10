package utils;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public interface Factory<T, P, S> {
    T create(Supplier<T> ctor, P prefix, S suffix);

    default T createRandom(Supplier<T> ctor, List<P> prefixes, List<S> suffixes, Random rng) {
        P p = (prefixes == null || prefixes.isEmpty()) ? null : prefixes.get(rng.nextInt(prefixes.size()));
        S s = (suffixes == null || suffixes.isEmpty()) ? null : suffixes.get(rng.nextInt(suffixes.size()));
        return create(ctor, p, s);
    }
}