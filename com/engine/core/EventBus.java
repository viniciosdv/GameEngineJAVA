package com.engine.core;

import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<Object>>> listeners = new HashMap<>();

    public <T> void subscribe(Class<T> eventType, Consumer<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add((Consumer<Object>) listener);
    }

    public void publish(Object event) {
        List<Consumer<Object>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (Consumer<Object> listener : eventListeners) {
                listener.accept(event);
            }
        }
    }
}