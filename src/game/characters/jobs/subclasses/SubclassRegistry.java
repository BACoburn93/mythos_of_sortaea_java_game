package characters.jobs.subclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class SubclassRegistry {
    private static final Map<String, Subclass> registry = new ConcurrentHashMap<>();

    private SubclassRegistry() {}

    public static void register(String id, Subclass s) {
        if (id == null || s == null) return;
        registry.put(id.toLowerCase(), s);
    }

    public static Subclass get(String id) {
        if (id == null) return null;
        return registry.get(id.toLowerCase());
    }

    public static List<Subclass> all() {
        return new ArrayList<>(registry.values());
    }


    public static List<Subclass> eligibleFor(Object character) {
        if (character == null) return Collections.emptyList();
        List<Subclass> out = new ArrayList<>();
        for (Subclass s : registry.values()) {
            try {
                if (s.canIncreaseTo((characters.Character) character, 1)) out.add(s);
            } catch (Throwable t) {
                // fallback: try via reflection (if character type is different)
                try {
                    var m = s.getClass().getMethod("canIncreaseTo", Class.forName("characters.Character"), int.class);
                    Object res = m.invoke(s, character, 1);
                    if (res instanceof Boolean && (Boolean) res) out.add(s);
                } catch (Throwable ignored) {}
            }
        }
        return out;
    }
}