package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class EventBus {
    private static final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    private EventBus() {}

    public static synchronized <T> void subscribe(Class<T> eventType, Consumer<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public static synchronized <T> void unsubscribe(Class<T> eventType, Consumer<T> listener) {
        List<Consumer<?>> list = listeners.get(eventType);
        if (list == null) return;
        list.remove(listener);
        if (list.isEmpty()) listeners.remove(eventType);
    }

    public static synchronized <T> Consumer<T> subscribeOnce(Class<T> eventType, Consumer<T> listener) {
        Consumer<T> wrapper = new Consumer<>() {
            @Override
            public void accept(T event) {
                try {
                    listener.accept(event);
                } finally {
                    unsubscribe(eventType, this);
                }
            }
        };
        subscribe(eventType, wrapper);
        return wrapper;
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> void publish(T event) {
        List<Consumer<?>> list = listeners.get(event.getClass());
        if (list == null) return;
        for (Consumer<?> c : new ArrayList<>(list)) {
            ((Consumer<T>) c).accept(event);
        }
    }
}
