package com.engine.core;

import com.engine.core.components.HealthComponent;
import com.engine.core.components.StatsComponent;
import com.engine.core.systems.CombatSystem;
import com.engine.core.systems.EntityDiedEvent;

public class GameEngine {
    private final EntityManager entityManager = new EntityManager();
    private final EventBus eventBus = new EventBus();
    private final CombatSystem combatSystem = new CombatSystem(entityManager, eventBus);

    public void init() {
        eventBus.subscribe(EntityDiedEvent.class, event -> {
            System.out.println("🚨 [EVENT BUS] Alerta crítico: A entidade '" + event.entityName + "' (ID: " + event.entityId + ") foi aniquilada!");
        });

        long player = entityManager.createEntity();
        entityManager.addComponent(player, new StatsComponent("Guerreiro", 50));
        entityManager.addComponent(player, new HealthComponent(100));

        long goblin = entityManager.createEntity();
        entityManager.addComponent(goblin, new StatsComponent("Goblin", 15));
        entityManager.addComponent(goblin, new HealthComponent(40));

        combatSystem.attack(player, goblin);
        combatSystem.attack(player, goblin);
        combatSystem.attack(player, goblin);
    }
}