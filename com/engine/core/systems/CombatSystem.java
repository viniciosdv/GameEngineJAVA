package com.engine.core.systems;

import com.engine.core.EntityManager;
import com.engine.core.EventBus;
import com.engine.core.components.HealthComponent;
import com.engine.core.components.StatsComponent;

public class CombatSystem {
    private final EntityManager entityManager;
    private final EventBus eventBus;

    public CombatSystem(EntityManager entityManager, EventBus eventBus) {
        this.entityManager = entityManager;
        this.eventBus = eventBus;
    }

    public void attack(long attackerId, long defenderId) {
        StatsComponent attackerStats = entityManager.getComponent(attackerId, StatsComponent.class);
        HealthComponent defenderHealth = entityManager.getComponent(defenderId, HealthComponent.class);
        StatsComponent defenderStats = entityManager.getComponent(defenderId, StatsComponent.class);

        if (attackerStats == null || defenderHealth == null) return;

        int damage = attackerStats.attackPower;
        defenderHealth.currentHp -= damage;
        if (defenderHealth.currentHp < 0) defenderHealth.currentHp = 0;

        System.out.println("⚔️ [" + attackerStats.name + "] causou " + damage + " de dano em [" + defenderStats.name + "]. HP Restante: " + defenderHealth.currentHp);

        if (defenderHealth.currentHp == 0) {
            eventBus.publish(new EntityDiedEvent(defenderId, defenderStats.name));
        }
    }
}

class EntityDiedEvent {
    public final long entityId;
    public final String entityName;

    public EntityDiedEvent(long entityId, String entityName) {
        this.entityId = entityId;
        this.entityName = entityName;
    }
}