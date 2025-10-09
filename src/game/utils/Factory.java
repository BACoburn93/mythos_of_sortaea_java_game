package utils;

import java.util.List;
import java.util.function.Supplier;

public interface Factory<T> {
    T create(Supplier<T> ctor, Object prefix, Object suffix);

    default T createRandom(Supplier<T> ctor, List<?> prefixes, List<?> suffixes, java.util.Random rng) {
        Object p = (prefixes == null || prefixes.isEmpty()) ? null : prefixes.get(rng.nextInt(prefixes.size()));
        Object s = (suffixes == null || suffixes.isEmpty()) ? null : suffixes.get(rng.nextInt(suffixes.size()));
        return create(ctor, p, s);
    }
}