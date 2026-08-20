package com.engine.core;

import com.engine.core.components.Component;
import java.util.*;

public class EntityManager {
    private long nextId = 1;
    private final Map<Long, Map<Class<? extends Component>, Component>> registry = new HashMap<>();

    public long createEntity() {
        long id = nextId++;
        registry.put(id, new HashMap<>());
        return id;
    }

    public void addComponent(long entityId, Component component) {
        registry.get(entityId).put(component.getClass(), component);
    }

    public <T extends Component> T getComponent(long entityId, Class<T> componentClass) {
        Map<Class<? extends Component>, Component> components = registry.get(entityId);
        if (components == null) return null;
        return componentClass.cast(components.get(componentClass));
    }

    public Set<Long> getEntitiesWithComponent(Class<? extends Component> componentClass) {
        Set<Long> entities = new HashSet<>();
        for (Map.Entry<Long, Map<Class<? extends Component>, Component>> entry : registry.entrySet()) {
            if (entry.getValue().containsKey(componentClass)) {
                entities.add(entry.getKey());
            }
        }
        return entities;
    }
}