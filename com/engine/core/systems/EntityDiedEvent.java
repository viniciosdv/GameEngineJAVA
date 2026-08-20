package com.engine.core.systems;

public class EntityDiedEvent {
    public final long entityId;
    public final String entityName;

    public EntityDiedEvent(long entityId, String entityName) {
        this.entityId = entityId;
        this.entityName = entityName;
    }
}